package com.xiaoshabao.baseframework.component.quartz.core.dto;

public class TaskInfoDto extends RouteEntity {

	private TaskEntity task;

	private boolean replaceFlag;

	/**
	 * 并行项目数
	 */
	private int eqnum;

	public TaskEntity getTask() {
		return task;
	}

	public void setTask(TaskEntity task) {
		this.task = task;
	}

	public boolean isReplaceFlag() {
		return replaceFlag;
	}

	public void setReplaceFlag(boolean replaceFlag) {
		this.replaceFlag = replaceFlag;
	}

	public int getEqnum() {
		return eqnum;
	}

	public void setEqnum(int eqnum) {
		this.eqnum = eqnum;
	}

}
