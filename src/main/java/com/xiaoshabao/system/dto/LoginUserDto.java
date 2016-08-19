package com.xiaoshabao.system.dto;

import com.xiaoshabao.system.entity.DepartEntity;
import com.xiaoshabao.system.entity.UserEntity;

/**
 * 获取用户登录相关信息
 */
public class LoginUserDto extends UserEntity {
	private DepartEntity depart;

	public DepartEntity getDepart() {
		return depart;
	}

	public void setDepart(DepartEntity depart) {
		this.depart = depart;
	}

}
