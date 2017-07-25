package com.xiaoshabao.upgrade.service;

import com.xiaoshabao.webframework.dto.AjaxResult;

public interface UpgradeService {

	/**
	 * 更新程序
	 * @param upgradeId
	 * @param path
	 * @return
	 */
	public AjaxResult upgradeApplication(Integer upgradeId);

	/**
	 * 获得未更新列表
	 * @param upgradeId
	 * @param path
	 * @return
	 */
	public AjaxResult getUpgradeList(Integer upgradeId);

	/**
	 * 获得未更新列表
	 * @param upgradeId
	 * @param path
	 * @return
	 */
	public AjaxResult getLogList(Integer upgradeId);
}
