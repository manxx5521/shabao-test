package com.xiaoshabao.shabaotest.plugins.mzhuatu.service;

import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.able.ZhuatuWaitAble;

/**
 * 需要等待的服务类
 * <p>返回结果循环时，需要等待线程池的活跃值小于一定值，才会进行下一循环</p>
 */
public abstract class ZhuatuWaitService implements ZhuatuService,ZhuatuWaitAble{

}