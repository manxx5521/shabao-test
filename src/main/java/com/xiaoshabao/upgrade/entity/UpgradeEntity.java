package com.xiaoshabao.upgrade.entity;

import java.util.Date;

public class UpgradeEntity {

	private Integer upgradeId;

	private String upgradeName;

	private String upgradeFileName;

	private String upgardeDate;

	private String serverUser;

	private String serverPassword;

	private String serverHost;

	private Integer serverPort;

	private String serverPath;

	private String applicationPath;

	private Date updateTime;

	private Integer updateUser;

	public Integer getUpgradeId() {
		return upgradeId;
	}

	public void setUpgradeId(Integer upgradeId) {
		this.upgradeId = upgradeId;
	}

	public String getUpgradeName() {
		return upgradeName;
	}

	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}

	public String getUpgardeDate() {
		return upgardeDate;
	}

	public void setUpgardeDate(String upgardeDate) {
		this.upgardeDate = upgardeDate;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public String getServerUser() {
		return serverUser;
	}

	public void setServerUser(String serverUser) {
		this.serverUser = serverUser;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getApplicationPath() {
		return applicationPath;
	}

	public void setApplicationPath(String applicationPath) {
		this.applicationPath = applicationPath;
	}

	public String getUpgradeFileName() {
		return upgradeFileName;
	}

	public void setUpgradeFileName(String upgradeFileName) {
		this.upgradeFileName = upgradeFileName;
	}

}
