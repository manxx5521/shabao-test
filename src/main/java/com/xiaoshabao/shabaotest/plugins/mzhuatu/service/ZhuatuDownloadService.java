package com.xiaoshabao.shabaotest.plugins.mzhuatu.service;

import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.able.ZhuatuDownloadAble;
import com.xiaoshabao.shabaotest.plugins.mzhuatu.service.able.loadFileAble;

/**
 * 下载服务
 * <p>
 * 返回结果循环时，会把结果加入到下载连接池，进行下载文件
 * </p>
 */
public abstract class ZhuatuDownloadService implements ZhuatuService, ZhuatuDownloadAble, loadFileAble {

}
