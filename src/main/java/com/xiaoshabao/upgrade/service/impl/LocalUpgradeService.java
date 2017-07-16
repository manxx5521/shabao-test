package com.xiaoshabao.upgrade.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.upgrade.entity.UpgradeEntity;
import com.xiaoshabao.upgrade.util.UpgradeConstants;

/**
 * 本地直接升级服务端代码
 */
@Service("localUpgradeService")
@Scope("prototype")
public class LocalUpgradeService extends UpgradeServiceImpl {
	/** 远程链接session */
	private Session session;

	@Override
	protected void existsDir(UpgradeEntity upgradeEntity) {
		ChannelSftp channel = null;
		try {
			this.getSSHConnect(upgradeEntity.getServerUser(),
					upgradeEntity.getServerPassword(),
					upgradeEntity.getServerHost(),
					upgradeEntity.getServerPort());
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			//验证目录是否存在
			@SuppressWarnings("unchecked")
			Vector<LsEntry> serverPaths = channel.ls(upgradeEntity
					.getServerPath());
			if (!hasLsEntry(serverPaths, UpgradeConstants.DIR_PATH)) {
				channel.mkdir(UpgradeConstants.getUpgradeDirPath(upgradeEntity
						.getServerPath()));
			}
			
			@SuppressWarnings("unchecked")
			Vector<LsEntry> filePaths = channel.ls(UpgradeConstants.getUpgradeDirPath(upgradeEntity
					.getServerPath()));
			if (!hasLsEntry(filePaths, UpgradeConstants.FILE_PATH)) {
				channel.mkdir(UpgradeConstants.getFilePath(upgradeEntity
						.getServerPath()));
			}
			if (!hasLsEntry(filePaths, UpgradeConstants.LOG_PATH)) {
				channel.mkdir(UpgradeConstants.getLogPath(upgradeEntity
						.getServerPath()));
			}
			if (!hasLsEntry(filePaths, UpgradeConstants.BACKUP_PATH)) {
				channel.mkdir(UpgradeConstants.getBackupPath(upgradeEntity
						.getServerPath()));
			}
			
			channel.quit();
			channel.disconnect();
		} catch (Exception e) {
			if (channel != null && !channel.isClosed()) {
				channel.disconnect();
			}
			throw new MsgErrorException("检查目录失败", e);
		}
	}

	/**
	 * 判断数组中是否有value
	 * 
	 * @param serverPaths
	 * @param path
	 * @return
	 */
	protected boolean hasLsEntry(Vector<LsEntry> files, String value) {
		boolean flag = false;
		if (StringUtils.isEmpty(value)) {
			return flag;
		}
		Enumeration<LsEntry> enu = files.elements();
		while (enu.hasMoreElements()) {
			LsEntry lsEntity = enu.nextElement();
			if (lsEntity.getFilename().equals(value))
				flag = true;
		}
		return flag;
	}

	@Override
	protected void unzip(UpgradeEntity upgradeEntity, String zipFilePath,
			String saveFileDir) {
		// 获得远程链接session
		this.getSSHConnect(upgradeEntity.getServerUser(),
				upgradeEntity.getServerPassword(),
				upgradeEntity.getServerHost(), upgradeEntity.getServerPort());

		StringBuilder command = new StringBuilder();

	}

	/**
	 * 获得ssh链接
	 * 
	 * @param username
	 * @param password
	 * @param host
	 * @param DEFAULT_SSH_PORT
	 */
	protected void getSSHConnect(String username, String password, String host,
			int DEFAULT_SSH_PORT) {
		// 如果已经链接返回
		if (session != null && session.isConnected()) {
			return;
		}
		JSch jsch = new JSch();
		MyUserInfo userInfo = new MyUserInfo(password);
		try {
			Session session = jsch.getSession(username, host, DEFAULT_SSH_PORT);
			session.setPassword(password);
			session.setUserInfo(userInfo);
			session.connect();
			this.session = session;
		} catch (Exception e) {
			throw new MsgErrorException("获得ssh链接失败", e);
		}
	}

	/**
	 * 执行shell命令
	 */
	protected Vector<String> exeShell(String command) {
		BufferedReader input = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			channel.setInputStream(null);
			input = new BufferedReader(new InputStreamReader(
					channel.getInputStream()));

			channel.connect();
			logger.info("执行ssh命令：{}", command);

			// Get the output of remote command.
			String line;
			Vector<String> stdout = new Vector<String>();
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
			input.close();

			// 获得返回状态
			if (channel.isClosed()) {
				logger.info("shell命令执行返回状态：{}", channel.getExitStatus());
			}
			channel.disconnect();
			return stdout;
		} catch (Exception e) {
			if (input != null) {
				input = null;
			}
			if (channel != null && !channel.isClosed()) {
				channel.disconnect();
			}

			logger.error("执行ssh命令：{}失败", command);
			throw new MsgErrorException("执行ssh命令失败", e);
		}

	}

	class MyUserInfo implements UserInfo {
		private String password;

		public MyUserInfo() {
		}
		public MyUserInfo(String password) {
			this.password=password;
		}

		public String getPassword() {
			return password;
		}

		public boolean promptYesNo(String str) {
			return true;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			return true;
		}

		public void showMessage(String message) {

		}
	}

	@Override
	protected void deleteFileTmepDir(UpgradeEntity upgradeEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteFileTmep(UpgradeEntity upgradeEntity) {
		// TODO Auto-generated method stub
		
	}

}
