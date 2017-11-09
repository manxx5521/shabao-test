package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重试操作，如果成功就跳出
 * 
 * @param <R>
 *            返回值类型
 */
public class RetryFactory<T, R> {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private int count = 5;

	/**
	 * 描述信息
	 */
	private String detailMsg;

	private T t;

	public RetryFactory(T t, String detailMsg) {
		this.t = t;
		this.detailMsg = detailMsg;
	}
	public RetryFactory(T t, String detailMsg, int count) {
		this.t = t;
		this.detailMsg = detailMsg;
		this.count = count;
	}

	/**
	 * 执行
	 * 
	 * @param function
	 * @return 错误返回null
	 */
	public R execute(Function<T, R> function){
		R result=null;
		int i = 1;
		do {
			try {
				result=function.apply(t);
				if(result instanceof Boolean&&(Boolean)result) {
					return result;
				}else if(result!=null) {
					return result;
				}
			} catch (Exception e) {
				log.warn("{}未能执行成功，进行重试。开始重试第{}次", this.detailMsg, i, e);
			}
			if (i > count) {
				log.error("{}执行失败", this.detailMsg);
				return null;
			}
			try {
				Thread.sleep(1000 * 3);
			} catch (InterruptedException e1) {
			}
		} while (true);
	}

	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
