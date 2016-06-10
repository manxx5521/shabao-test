package com.xiaoshabao.system.dto;

import java.util.List;

import com.xiaoshabao.system.entity.MenuEntity;

/**
 * 菜单实体类<br/>
 * 原来通过二级方式取菜单但是现在不用啦
 */
public class MenuListValue {

	private List<MenuEntity> list;
	/**
	 * id标识
	 */
	private String menu_id;
	/**
	 * 标题，显示名
	 */
	private String menu_title;
	/**
	 * 描述信息
	 */
	private String menu_des;
	/**
	 * 父级id标识
	 */
	private String parent_menu_id;
	/**
	 * 显示区域：1、后台管理
	 */
	private Integer menu_code;
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
	private String right_code;
	/**
	 * URL
	 */
	private String url;
	/**
	 * 菜单当前等级排序
	 */
	private Integer order_no;

	public List<MenuEntity> getList() {
		return list;
	}

	public void setList(List<MenuEntity> list) {
		this.list = list;
	}

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

	public Integer getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(Integer menu_code) {
		this.menu_code = menu_code;
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

	public String getRight_code() {
		return right_code;
	}

	public void setRight_code(String right_code) {
		this.right_code = right_code;
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
}
