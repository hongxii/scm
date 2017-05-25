package com.scm.dao;

import com.scm.model.*;
import com.scm.util.ConnectionUtil;
import org.omg.CORBA.CustomMarshal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭老师 on 2017/5/5.
 */
public class SOItemDao {
    private static final SOItemDao instance = new SOItemDao();
    private SOItemDao(){}
    public static SOItemDao getInstance(){
        return instance;
    }

    //添加明细单[单个]
    public boolean addItem(SOItem si){

        boolean flag = false;
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into soitem(soid,productcode,unitprice,num,unitname,itemprice) values(?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,si.getSoID() );
            pstmt.setString(2,si.getProductCode() );
            pstmt.setFloat(3,si.getUnitPrice());
            pstmt.setInt(4,si.getNum());
            pstmt.setString(5,si.getUnitName() );
            pstmt.setFloat(6,si.getItemPrice());

            pstmt.executeUpdate();
            flag = true;
            System.out.println("明细单添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("明细单添加失败-SQL:"+sql);
        } finally {
            ConnectionUtil.close(null, pstmt, conn);
        }

        return flag;
    }

    //添加明细单【集合】
    public void addItems(ArrayList<SOItem> al){

        for (int i = 0; i <al.size() ; i++) {
            addItem(al.get(i));
        }
    }

    //得到多个明细单
    public ArrayList<SOItem> getBySoid(String id){
        ArrayList<SOItem> al = new ArrayList<SOItem>();
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt=null;
        ResultSet rs = null;

        String sql=null;
        sql = "select * from soitem where soid='" + id + "'";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String soid=rs.getString(1);
                String code=rs.getString(2);;
                Float unitprice = rs.getFloat(3);
                int num = rs.getInt(4);
                String unitname = rs.getString(5);
                Float itemprice = rs.getFloat(6);

                al.add(new SOItem(soid,code,unitprice,num,unitname,itemprice));
            }
            System.out.println("导出明细数为"+al.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("导出明细失败-SQL"+sql);
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }

        return al;
    }


    //【AJAX】查询要显示的product
    public  Page getProduct(Page page){
        Connection conn=ConnectionUtil.getConn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select p.CategoryID,p.CreateDate,p.`Name`,p.Price,p.ProductCode,p.Remark,p.UnitName,c.`Name` cname from product p,category c where p.CategoryID=c.CategoryID limit ?,?");
            int allCount=getCount("product"); //获得数据的总条数
            int allPage=0;
            if(allCount%page.getCount()==0){
                allPage=allCount/page.getCount();
            }else{
                allPage=allCount/page.getCount()+1;
            }
            page.setAllPage(allPage);

            ps.setInt(1, allPage*page.getCurrentPage());
            ps.setInt(2, page.getCount());

            rs=ps.executeQuery();
            List<Product> list=new ArrayList<Product>();
            while(rs.next()){
                Product p=new Product();
                p.setCategoryId(rs.getInt("categoryId"));
                p.setCreateDate(rs.getString("createDate"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                p.setProductCode(rs.getString("productCode"));
                p.setUnitName(rs.getString("unitName"));

                Category c=new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setName(rs.getString("cname"));

                p.setCategory(c);

                list.add(p);
            }
            page.setPageMessage(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            ConnectionUtil.close(rs, ps, conn);
        }
        return page;
    }

    //【AJAX】查询要显示的customer
    public  Page getCustomer(Page page){
        Connection conn=ConnectionUtil.getConn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select * from customer limit ?,?");
            int allCount=getCount("customer"); //获得数据的总条数
            int allPage=0;
            if(allCount%page.getCount()==0){
                allPage=allCount/page.getCount();
            }else{
                allPage=allCount/page.getCount()+1;
            }
            page.setAllPage(allPage);

            ps.setInt(1, allPage*page.getCurrentPage());
            ps.setInt(2, page.getCount());

            rs=ps.executeQuery();
            List<Customer> list=new ArrayList<Customer>();
            while(rs.next()){

                String customerCode=rs.getString(1);
                String name=rs.getString(2);
                String contactor=rs.getString(4);
                String address=rs.getString(5);
                String postcode=rs.getString(6);
                String tel=rs.getString(7);
                String fax=rs.getString(8);
                String createDate=rs.getString(9);

                list.add(new Customer(customerCode,name,contactor,address,postcode,tel,fax,createDate));
            }
            page.setPageMessage(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            ConnectionUtil.close(rs, ps, conn);
        }
        return page;
    }

    //【AJAX】查询总表总条数
    public int getCount(String tableName){
        Connection conn=ConnectionUtil.getConn();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select count(*)c from "+tableName);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt("c");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally{
            ConnectionUtil.close(rs, ps, conn);
        }
    }

    //根据ID得到Product对象
    public Product getProductById(String id){

        Product product = new Product();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from product where ProductCode="+"'"+id+"'";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();

            product.setProductCode(rs.getString(1));
            product.setCategoryId(rs.getInt(2));
            product.setName(rs.getString(3));
            product.setUnitName(rs.getString(4));
            product.setPrice(rs.getFloat(5));
            product.setCreateDate(rs.getString(6));
            product.setRemark(rs.getString(7));
            product.setPoNum(rs.getInt(8));
            product.setSoNum(rs.getInt(9));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return product;
    }

    //得到所有的产品对象
    public ArrayList<Product> getAll(){

        ArrayList<Product> al = new ArrayList<Product>();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from  product";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String productCode=rs.getString(1);
                int categoryID = rs.getInt(2);
                String name = rs.getString(3);
                String unitname=rs.getString(4);;
                float price = rs.getFloat(5);
                String createDate=rs.getString(6);;
                String remark = rs.getString(7);
                int ponum = rs.getInt(8);
                int sonum = rs.getInt(9);

                al.add(new Product(productCode,categoryID,name,unitname,price,createDate,remark,ponum,sonum));
            }
            System.out.println("共取得客户数为"+al.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("导出客户失败-SQL"+sql);
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return al;
    }




/*
    public static void main(String[] args) {
        //SOItem si = new SOItem("30002","20002",3);
        //System.out.println(si.getItemPrice());
        SOItemDao soItemDao = new SOItemDao();
        //soItemDao.addSell(new SOItem("S0001", "20002", 3));
        //soItemDao.addSell(new SOItem("S0001", "20001", 4));
    }
*/
}
