package com.xiaoshabao.webframework.ui.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 视图位置
 */
public enum ViewPositionEnum {

	/** 主要视图 */
	MAIN_VIEW(1),
	/** 子要视图 */
	SUB_VIEW(2);

	/** 位置代码 */
	private int position;

	private ViewPositionEnum(int position) {
		this.position = position;
	}

	public boolean equals(int position) {
		if (this.position != position) {
			return false;
		}
		return true;
	}

	public boolean equals(Integer position) {
		if (position == null) {
			return false;
		}
		if (position.intValue() != this.position) {
			return false;
		}
		return true;
	}

	public boolean equals(String position) {

		if (StringUtils.isEmpty(position)) {
			return false;
		}
		if (!NumberUtils.isNumber(position)) {
			return false;
		}
		if (Integer.valueOf(position).intValue() != this.position) {
			return false;
		}

		return true;
	}

	public int getPosition() {
		return position;
	}

}
