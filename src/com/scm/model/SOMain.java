package com.scm.model;

import com.scm.dao.CustomerDao;
import com.scm.dao.SOMainDao;

/**
 * Created by 郭老师 on 2017/5/2.
 */
public class SOMain {

    private String soID;            //销售单编号
    private String customerCode;    //客户编号
    private String account;          //用户账号
    private String createTime;      //创建事件
    private float tipFee;           //附加费用
    private float productTotal;     //产品总价
    private float soTotal;          //总价格
    private String payType;         //付款方式
    private float prePayFee;        //最低预付款金额
    private int status;             //处理状态
    private String remark;          //备注
    private String stockTime;       //出库登记时间
    private String stockUser;       //出库登记用户
    private String payTime;         //付款登记时间
    private String payUser;         //付款登记用户
    private String prePayTime;      //预付登记事件
    private String prePayUser;      //预付登记用户
    private String endTime;         //了结时间
    private String endUser;         //了结用户
    private Customer customer;      //客户
    private SCMUser scmUser;        //操作

    public SOMain(){}

    public SOMain(String soID, String createTime, String customerName, String account, float tipFee, float productTotal, String payType, float prePayFee) {
        this.soID = soID;
        this.customerCode = CustomerDao.getInstance().getOneByName(customerName).getCustomerCode();
        this.account = account;
        this.createTime = createTime;
        this.tipFee = tipFee;
        this.productTotal = productTotal;
        this.soTotal = tipFee+productTotal;
        this.payType = payType;
        this.prePayFee = prePayFee;
        this.status = 1;
        this.remark = null;
        this.stockTime = null;
        this.stockUser = null;
        this.payTime = null;
        this.payUser = null;
        this.prePayTime = null;
        this.prePayUser = null;
        this.endTime = null;
        this.endUser = null;
        this.customer = CustomerDao.getInstance().getOneByName(customerName);
        this.scmUser = SOMainDao.getInstance().getSCMUser(account);
    }

    public SCMUser getScmUser() {
        return scmUser;
    }

    public void setScmUser(SCMUser scmUser) {
        this.scmUser = scmUser;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SOMain(String soID, String customerCode, String account, String createTime, float tipFee, float productTotal, float soTotal, String payType, float prePayFee, int status, String remark, String stockTime, String stockUser, String payTime, String payUser, String prePayTime, String prePayUser, String endTime, String endUser) {
        this.soID = soID;
        this.customerCode = customerCode;
        this.account = account;
        this.createTime = createTime;
        this.tipFee = tipFee;
        this.productTotal = productTotal;
        this.soTotal = soTotal;
        this.payType = payType;
        this.prePayFee = prePayFee;
        this.status = status;
        this.remark = remark;
        this.stockTime = stockTime;
        this.stockUser = stockUser;
        this.payTime = payTime;
        this.payUser = payUser;
        this.prePayTime = prePayTime;
        this.prePayUser = prePayUser;
        this.endTime = endTime;
        this.endUser = endUser;
        this.customer = CustomerDao.getInstance().getOne(customerCode);
    }

    public String getSoID() {
        return soID;
    }

    public void setSoID(String soID) {
        this.soID = soID;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAcount() {
        return account;
    }

    public void setAcount(String acount) {
        this.account = acount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public float getTipFee() {
        return tipFee;
    }

    public void setTipFee(float tipFee) {
        this.tipFee = tipFee;
    }

    public float getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(float productTotal) {
        this.productTotal = productTotal;
    }

    public float getSoTotal() {
        return soTotal;
    }

    public void setSoTotal(float soTotal) {
        this.soTotal = soTotal;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public float getPrePayFee() {
        return prePayFee;
    }

    public void setPrePayFee(float prePayFee) {
        this.prePayFee = prePayFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStockTime() {
        return stockTime;
    }

    public void setStockTime(String stockTime) {
        this.stockTime = stockTime;
    }

    public String getStockUser() {
        return stockUser;
    }

    public void setStockUser(String stockUser) {
        this.stockUser = stockUser;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPrePayTime() {
        return prePayTime;
    }

    public void setPrePayTime(String prePayTime) {
        this.prePayTime = prePayTime;
    }

    public String getPrePayUser() {
        return prePayUser;
    }

    public void setPrePayUser(String prePayUser) {
        this.prePayUser = prePayUser;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndUser() {
        return endUser;
    }

    public void setEndUser(String endUser) {
        this.endUser = endUser;
    }

    @Override
    public String toString() {
        return "SOMain{" +
                "soID='" + soID + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", account='" + account + '\'' +
                ", createTime='" + createTime + '\'' +
                ", tipFee=" + tipFee +
                ", productTotal=" + productTotal +
                ", soTotal=" + soTotal +
                ", payType='" + payType + '\'' +
                ", prePayFee=" + prePayFee +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", stockTime='" + stockTime + '\'' +
                ", stockUser='" + stockUser + '\'' +
                ", payTime='" + payTime + '\'' +
                ", payUser='" + payUser + '\'' +
                ", prePayTime='" + prePayTime + '\'' +
                ", prePayUser='" + prePayUser + '\'' +
                ", endTime='" + endTime + '\'' +
                ", endUser='" + endUser + '\'' +
                '}';
    }
}
