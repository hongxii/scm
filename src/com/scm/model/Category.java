package com.scm.model;
/*产品分类表类*/
public class Category {
	private int categoryId;//类别序列号
	private String name;//名称
	private String remark;//描述
	public Category() {
		super();
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
