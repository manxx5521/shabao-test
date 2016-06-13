package com.xiaoshabao.system.entity;

/**
 * 菜单实体类
 */
public class MenuEntity {
	/**
	 * id标识
	 */
	private String menuId;
	/**
	 * 分组id，100为admin左侧菜单
	 */
	private Integer groupId;
	/**
	 * 标题，显示名
	 */
	private String menuTitle;
	
	/**
	 * 是否是菜单按钮 1是
	 */
	private Integer ismenu;
	
	/**
	 * 菜单样式
	 */
	private String ioc;
	
	/**
	 * 描述信息
	 */
	private String menuDes;
	/**
	 * 父级id标识
	 */
	private String parentMenuId;
	/**
	 * 是否使用：1、使用，0、不使用
	 */
	private Integer foruse;
	/**
	 * 标题等级
	 */
	private Integer level;
	/**
	 * 对应权限id表示
	 */
	private Integer permissionId;
	/**
	 * URL
	 */
	private String url;
	/**
	 * 菜单当前等级排序
	 */
	private Integer orderNo;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public Integer getIsmenu() {
		return ismenu;
	}
	public void setIsmenu(Integer ismenu) {
		this.ismenu = ismenu;
	}
	public String getIoc() {
		return ioc;
	}
	public void setIoc(String ioc) {
		this.ioc = ioc;
	}
	public String getMenuDes() {
		return menuDes;
	}
	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public Integer getForuse() {
		return foruse;
	}
	public void setForuse(Integer foruse) {
		this.foruse = foruse;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}
