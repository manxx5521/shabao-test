package com.xiaoshabao.wechat.api.wxuser.model;


/**
 * 分组实体类<br>
 * 取多媒体文件
 */
public class Group {
	/**
	 * 分组名字(30个字符以内)
	 * <p>创建分组时，只需要传入名字</p>
	 */
	private String name;
	
	/**
	 * 分组id
	 */
	private String id;
	
	/**
	 * 分组内用户数量,只在获取所有用户分组是有效
	 */
	private String count;
	
	/**
	 * 分组的id
	 * <p>创建分组成功后，返回此id。<br>
	 * 获得分组信息时有效。</p>
	 * @return
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获得分组名字
	 * <p>创建分组成功后，返回此名字。<br>
	 * 获得分组信息时有效。</p>
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置分组名字
	 * <p>创建分组时只需要设置用户名字，其他属性无需设置。</p>
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 分组内用户数量
	 * <p>获得分组信息时有效</p>
	 * @return
	 */
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
