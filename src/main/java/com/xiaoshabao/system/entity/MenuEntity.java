package com.xiaoshabao.system.entity;

/**
 * 菜单实体类
 */
public class MenuEntity {
	/**
	 * id标识
	 */
	private String menu_id;
	/**
	 * 分组id，100为admin左侧菜单
	 */
	private Integer group_id;
	/**
	 * 标题，显示名
	 */
	private String menu_title;
	
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
	private String menu_des;
	/**
	 * 父级id标识
	 */
	private String parent_menu_id;
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
	private Integer permission_id;
	/**
	 * URL
	 */
	private String url;
	/**
	 * 菜单当前等级排序
	 */
	private Integer order_no;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public String getMenu_des() {
		return menu_des;
	}
	public void setMenu_des(String menu_des) {
		this.menu_des = menu_des;
	}
	public String getParent_menu_id() {
		return parent_menu_id;
	}
	public void setParent_menu_id(String parent_menu_id) {
		this.parent_menu_id = parent_menu_id;
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
	
	public Integer getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
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
	
}
