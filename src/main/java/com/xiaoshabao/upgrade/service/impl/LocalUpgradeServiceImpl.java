package com.xiaoshabao.upgrade.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.io.FilenameUtils;
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
import com.xiaoshabao.upgrade.util.ZipUpgradeUtil;
import com.xiaoshabao.webframework.dto.AjaxResult;

/**
 * 本地直接升级服务端代码
 */
@Service("localUpgradeServiceImpl")
@Scope("prototype")
public class LocalUpgradeServiceImpl extends BaseUpgradeServiceImpl {
	/** 远程链接session */
	private Session session;

	/** 是否检查目录标识 **/
	private boolean checkDir = false;

	private final static String FILE_PATH_LOCAL = "C:\\Users\\xv\\Desktop\\upgrade";

	@Override
	public AjaxResult upgradeApplication(Integer upgradeId) {
		UpgradeEntity upgradeEntity = getUpgradeEntity(upgradeId);
		File file = new File(this.getFilePathRoot(upgradeEntity, upgradeEntity.getUpgradeFileName()));
		if (file == null || !file.exists()) {
			throw new MsgErrorException("未能在升级目录找到所需升级文件");
		}
		// 检查目录
		existsDir(upgradeEntity);
		checkDir = true;

		// 要把本地包传到服务器
		ChannelSftp channel = null;
		try {
			this.getSSHConnect(upgradeEntity.getServerUser(), upgradeEntity.getServerPassword(),
					upgradeEntity.getServerHost(), upgradeEntity.getServerPort());
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			// channel.put(this.getFilePathRoot(upgradeEntity,upgradeEntity.getUpgradeFileName()),
			// UpgradeConstants.getFilePath(upgradeEntity.getServerPath()));

			channel.quit();
			channel.disconnect();
		} catch (Exception e) {
			if (channel != null && !channel.isClosed()) {
				channel.disconnect();
			}
			throw new MsgErrorException("检查目录失败", e);
		}

		return upgradeApplication(upgradeId, upgradeEntity);
	}

	@Override
	protected void existsDir(UpgradeEntity upgradeEntity) {
		if (checkDir) {
			return;
		}

		ChannelSftp channel = null;
		try {
			this.getSSHConnect(upgradeEntity.getServerUser(), upgradeEntity.getServerPassword(),
					upgradeEntity.getServerHost(), upgradeEntity.getServerPort());
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			// 验证目录是否存在
			@SuppressWarnings("unchecked")
			Vector<LsEntry> serverPaths = channel.ls(upgradeEntity.getServerPath());
			if (!hasLsEntry(serverPaths, UpgradeConstants.DIR_PATH)) {
				channel.mkdir(UpgradeConstants.getUpgradeDirPath(upgradeEntity.getServerPath()));
			}

			@SuppressWarnings("unchecked")
			Vector<LsEntry> filePaths = channel.ls(UpgradeConstants.getUpgradeDirPath(upgradeEntity.getServerPath()));
			if (!hasLsEntry(filePaths, UpgradeConstants.FILE_PATH)) {
				channel.mkdir(UpgradeConstants.getFilePath(upgradeEntity.getServerPath()));
			}
			if (!hasLsEntry(filePaths, UpgradeConstants.LOG_PATH)) {
				channel.mkdir(UpgradeConstants.getLogPath(upgradeEntity.getServerPath()));
			}
			if (!hasLsEntry(filePaths, UpgradeConstants.BACKUP_PATH)) {
				channel.mkdir(UpgradeConstants.getBackupPath(upgradeEntity.getServerPath()));
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

	@Override
	protected void deleteFileTmepDir(UpgradeEntity upgradeEntity) {
		this.exeShell("rm -rf "
				+ UpgradeConstants.getFileTempPath(upgradeEntity.getServerPath(), upgradeEntity.getUpgradeFileName()));
	}

	@Override
	protected String getFilePathRoot(UpgradeEntity upgradeEntity) {
		return FILE_PATH_LOCAL;
	}

	@Override
	protected String getServerPathRoot(UpgradeEntity upgradeEntity) {
		return upgradeEntity.getServerPath() + UpgradeConstants.separator + UpgradeConstants.DIR_PATH;
	}

	/**
	 * 获得文件路径的分割符号
	 * 
	 * @return
	 */
	@Override
	protected String getFileRootSeparator() {
		return "\\";
	}

	/**
	 * 获得数据连接（多数源需重写）
	 * 
	 * @return
	 */
	@Override
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection("", "", "");
		} catch (Exception e) {
			throw new MsgErrorException("设置升级数据源失败");
		}
	}

	@Override
	protected void deleteFileTmep(UpgradeEntity upgradeEntity) {
		deleteServerFile(upgradeEntity, UpgradeConstants.getFilePath(upgradeEntity.getServerPath())
				+ UpgradeConstants.separator + upgradeEntity.getUpgradeFileName());
	}

	@Override
	protected void existsUpgradeFile(UpgradeEntity upgradeEntity) {
		ChannelSftp channel = null;
		try {
			this.getSSHConnect(upgradeEntity.getServerUser(), upgradeEntity.getServerPassword(),
					upgradeEntity.getServerHost(), upgradeEntity.getServerPort());
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();

			// 验证目录是否存在
			@SuppressWarnings("unchecked")
			Vector<LsEntry> serverPaths = channel.ls(UpgradeConstants.getFilePath(upgradeEntity.getServerPath()));
			if (!hasLsEntry(serverPaths, upgradeEntity.getUpgradeFileName())) {
				throw new MsgErrorException("未找到要升级的文件");
			}

			channel.quit();
			channel.disconnect();
		} catch (Exception e) {
			if (channel != null && !channel.isClosed()) {
				channel.disconnect();
			}
			throw new MsgErrorException("验证升级的文件是否存在时错误", e);
		}

	}

	@Override
	protected String getSpecialConfigPath(UpgradeEntity upgradeEntity, String specialPath) {
		return FILE_PATH_LOCAL + FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName()) + specialPath;
	}

	/**
	 * 判断数组中是否有value
	 * 
	 * @param serverPaths
	 * @param path
	 * @return
	 */
	private boolean hasLsEntry(Vector<LsEntry> files, String value) {
		boolean flag = false;
		if (StringUtils.isEmpty(value)) {
			return flag;
		}
		Enumeration<LsEntry> enu = files.elements();
		while (enu.hasMoreElements()) {
			LsEntry lsEntity = enu.nextElement();
			if (lsEntity.getFilename().equals(value)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 解压文件
	 * <p>
	 * 需要服务端解压一份，用来升级文件。本地解压一份用来升级sql
	 * </p>
	 */
	@Override
	protected void unzip(UpgradeEntity upgradeEntity, String zipFilePath, String saveFileDir) {
		// 获得远程链接session
		this.getSSHConnect(upgradeEntity.getServerUser(), upgradeEntity.getServerPassword(),
				upgradeEntity.getServerHost(), upgradeEntity.getServerPort());

		// 创建解压文件夹
		this.exeShell("mkdir " + this.getServerPathRoot(upgradeEntity, UpgradeConstants.FILE_PATH,
				FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName())));
		// 考本文件到解压文件夹
		this.exeShell("cp "
				+ this.getServerPathRoot(upgradeEntity, UpgradeConstants.FILE_PATH, upgradeEntity.getUpgradeFileName())
				+ " " + this.getServerPathRoot(upgradeEntity, UpgradeConstants.FILE_PATH,
						FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName())));
		// 解压文件
		this.exeShell("jar -xvf " + this.getServerPathRoot(upgradeEntity, UpgradeConstants.FILE_PATH,
				FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName()), upgradeEntity.getUpgradeFileName()));// 执行命令解压服务器文件

		// 删除用来解压数据的临时包
		this.exeShell("rm -rf  " + this.getServerPathRoot(upgradeEntity, UpgradeConstants.FILE_PATH,
				FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName()), upgradeEntity.getUpgradeFileName()));// 执行命令解压服务器文件

		File tempDir = new File(
				this.getFilePathRoot(upgradeEntity, FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName())));
		if (tempDir.exists()) {
			tempDir.delete();// 先删除
		}
		ZipUpgradeUtil.unzip(this.getFilePathRoot(upgradeEntity, upgradeEntity.getUpgradeFileName()),
				this.getFilePathRoot(upgradeEntity, FilenameUtils.getBaseName(upgradeEntity.getUpgradeFileName()))
						+ this.getFileRootSeparator());
	}

	@Override
	protected void deleteServerFile(UpgradeEntity upgradeEntity, String path) {
		this.getSSHConnect(upgradeEntity.getServerUser(), upgradeEntity.getServerPassword(),
				upgradeEntity.getServerHost(), upgradeEntity.getServerPort());
		exeShell("rm -rf " + path);
	}

	@Override
	protected void copyServerFileToFile(UpgradeEntity upgradeEntity, String srcPath, String destPath) {
		this.getSSHConnect(upgradeEntity.getServerUser(), upgradeEntity.getServerPassword(),
				upgradeEntity.getServerHost(), upgradeEntity.getServerPort());
		exeShell("cp " + srcPath + " " + destPath);
	}

	/**
	 * 获得ssh链接
	 * 
	 * @param username
	 * @param password
	 * @param host
	 * @param DEFAULT_SSH_PORT
	 */
	protected void getSSHConnect(String username, String password, String host, int DEFAULT_SSH_PORT) {
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
			input = new BufferedReader(new InputStreamReader(channel.getInputStream()));

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
			this.password = password;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public boolean promptYesNo(String str) {
			return true;
		}

		@Override
		public String getPassphrase() {
			return null;
		}

		@Override
		public boolean promptPassphrase(String message) {
			return true;
		}

		@Override
		public boolean promptPassword(String message) {
			return true;
		}

		@Override
		public void showMessage(String message) {

		}
	}

}
