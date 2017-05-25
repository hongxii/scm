package com.scm.model;
/*产品表类*/
public class Product {
	private String productCode;//产品编号
	private int categoryId;//类别序列号
	private String name ;//名称
	private String unitName;//数量单位
	private float price;//销售价
	private String createDate;//添加日期
	private String remark ;//产品描述
	private int poNum ;//采购在途数
	private int soNum ;//销售待发数
	private Category category;

	public Product() {}

	public Product(String productCode, int categoryId, String name, String unitName, float price, String createDate, String remark, int poNum, int soNum) {
		this.productCode = productCode;
		this.categoryId = categoryId;
		this.name = name;
		this.unitName = unitName;
		this.price = price;
		this.createDate = createDate;
		this.remark = remark;
		this.poNum = poNum;
		this.soNum = soNum;
	}

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPoNum() {
		return poNum;
	}
	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}
	public int getSoNum() {
		return soNum;
	}
	public void setSoNum(int soNum) {
		this.soNum = soNum;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productCode='" + productCode + '\'' +
				", categoryId=" + categoryId +
				", name='" + name + '\'' +
				", unitName='" + unitName + '\'' +
				", price=" + price +
				", createDate='" + createDate + '\'' +
				", remark='" + remark + '\'' +
				", poNum=" + poNum +
				", soNum=" + soNum +
				", category=" + category +
				'}';
	}
}
