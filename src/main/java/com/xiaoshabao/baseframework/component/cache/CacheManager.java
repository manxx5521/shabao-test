package com.xiaoshabao.baseframework.component.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存实现
 */
@SuppressWarnings({ "unchecked", "rawtypes"})
public class CacheManager {
  public static final Object NULL_OBJECT = new Object();

  private boolean readOnly;

  private boolean serialize;

  private static long lastFlush = 0L;// 更新缓存时记录的时间

  private static long flushInterval;

  private static final long NO_FLUSH_INTERVAL = -99999;

  private volatile static CacheManager mapCacheObject;// 缓存实例对象  

 
  private static Map cacheMap = Collections.synchronizedMap(new HashMap());

  /**
   * @Title: getInstance     
   * @Description: 采用单例模式获取缓存实例对象    
   * @return 缓存实例对象      
   */
  public static CacheManager getInstance() {
    if (null == mapCacheObject) {
      synchronized (CacheManager.class) {
        if (null == mapCacheObject) {
          mapCacheObject = new CacheManager();
          flushInterval = NO_FLUSH_INTERVAL;
          lastFlush = System.currentTimeMillis();
        }
      }
    }
    return mapCacheObject;
  }

  public boolean isReadOnly() {
    return readOnly;
  }

  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

  public boolean isSerialize() {
    return serialize;
  }

  public void setSerialize(boolean serialize) {
    this.serialize = serialize;
  }

  public long getFlushInterval() {
    return flushInterval;
  }

  public void setFlushInterval(long flushInterval) {
    CacheManager.flushInterval = flushInterval;
  }

  /**
   * @Title: clearCache     
   * @Description: 清除缓存           
   */
  public void clearCache() {
    synchronized (this) {
      cacheMap.clear();
      lastFlush = System.currentTimeMillis();
    }
  }

  /**
   * 添加对象到缓存
   *
   * @param key   要缓存对象的键
   * @param value 要缓存的对象
   */
  public void putObject(Object key, Object value) {
    if (null == value) {
    	value = NULL_OBJECT;
    }
      
    //关于缓存的操作，需要互斥
    synchronized (this) {
      if (serialize && !readOnly && value != NULL_OBJECT) {
        //需要序列化，并且非只读，则需要将缓存对象序列化到内存，以供后续检索使用
        //readOnly为false时，不能直接将对象引用直接返回个客户程序
        try {
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          ObjectOutputStream oos = new ObjectOutputStream(bos);
          oos.writeObject(value);
          oos.flush();
          oos.close();
          value = bos.toByteArray();
        } catch (IOException e) {
          throw new RuntimeException("Error caching serializable object.  Cause: " + e, e);
        }
      }
      if (cacheMap.containsKey(key)) {
        System.out.println("warning :缓存key值= [" + key.toString() + "] 在缓存中重复！");
      }
      //如果执行了内存序列化，则保存的是它的字节数组
      cacheMap.put(key, value);
    }
  }

  /**
   * 从缓存中获取对象.
   * @param key 要返回对象的键
   * @return 缓存对象 (或者为空)
   */
  public Object getObject(Object key) {
    Object value = null;
    //互斥访问缓冲区
    synchronized (this) {
      if (flushInterval != NO_FLUSH_INTERVAL && System.currentTimeMillis() - lastFlush > flushInterval) {
        //如果到了定期刷新缓冲区时，则执行刷新
        clearCache();
      }
      //根据key来从CacheController中取得数据对象
      value = cacheMap.get(key);
      if (serialize && !readOnly && (value != NULL_OBJECT && value != null)) {
        //如果需要序列化，并且非只读，则从内存中序列化出一个数据对象的副本
        try {
          ByteArrayInputStream bis = new ByteArrayInputStream((byte[]) value);
          ObjectInputStream ois = new ObjectInputStream(bis);
          value = ois.readObject();
          ois.close();
        } catch (Exception e) {
          throw new RuntimeException("Error caching serializable object.  Be sure you're not attempting to use "
            + "a serialized cache for an object that may be taking advantage of lazy loading.  Cause: " + e, e);
        }
      }
    }
    return value;
  }

  /**
   * @Title: containsKey     
   * @Description: 校验是否存在指定键    
   * @param key
   * @return       
   */
  public boolean containsKey(Object key) {
    synchronized (this) {
      return cacheMap.containsKey(key);
    }
  }

  /**
   * @Title: isEmpty     
   * @Description: 判断缓存是否为空 
   * @return boolean      
   */
  public boolean isEmpty() {
    synchronized (this) {
      return cacheMap.isEmpty();
    }
  }
}
