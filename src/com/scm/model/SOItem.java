package com.scm.model;

import com.scm.dao.SOItemDao;

/**
 * Created by 郭老师 on 2017/5/2.
 */
public class SOItem {

    private String soID;            //销售单编号
    private String productCode;     //产品编号
    private float unitPrice;        //产品单价
    private int num;                //产品数量
    private String unitName;        //数量单位
    private float itemPrice;        //明细总价

    public SOItem(){}

    public SOItem(String soID, String productCode, int num) {
        this.soID = soID;
        this.productCode = productCode;
        this.num = num;

        this.unitPrice = SOItemDao.getInstance().getProductById(productCode).getPrice();
        this.unitName = SOItemDao.getInstance().getProductById(productCode).getUnitName();
        this.itemPrice = unitPrice*num;
    }

    public SOItem(String soID, String productCode, float unitPrice, int num, String unitName, float itemPrice) {
        this.soID = soID;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.num = num;
        this.unitName = unitName;
        this.itemPrice = itemPrice;
    }

    public String getSoID() {
        return soID;
    }

    public void setSoID(String soID) {
        this.soID = soID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "SOItem{" +
                "soID='" + soID + '\'' +
                ", productCode='" + productCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", num=" + num +
                ", unitName='" + unitName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
