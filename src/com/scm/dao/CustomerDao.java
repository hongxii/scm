package com.scm.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.scm.model.Customer;
import com.scm.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by 郭老师 on 2017/5/3.
 */


public class CustomerDao {

    private static final CustomerDao instance = new CustomerDao();
    private CustomerDao(){}
    public static CustomerDao getInstance(){
        return instance;
    }

    //新增(成功失败均有提示)
    public boolean addCustomer(Customer customer){

        boolean flag = false;
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into customer(CustomerCode,Name,Password,Contactor,Address,Postcode,Tel,Fax,createDate) values(?,?,?,?,?,?,?,?,now())";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,customer.getCustomerCode() );
            pstmt.setString(2,customer.getName() );
            pstmt.setString(3,customer.getPassword() );
            pstmt.setString(4,customer.getContactor() );
            pstmt.setString(5,customer.getAddress() );
            pstmt.setString(6,customer.getPostcode() );
            pstmt.setString(7,customer.getTel() );
            pstmt.setString(8,customer.getFax() );

            pstmt.executeUpdate();
            flag = true;
            System.out.println("添加客户成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("客户添加失败-SQL:"+sql);
        } finally {
            ConnectionUtil.close(null, pstmt, conn);
        }

        return flag;
    }

    //删除
    public boolean delCustomer(String id){

        boolean flag=false;
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        Statement stat = null;
        String sql=null;

        sql="delete from customer where CustomerCode='"+id+"'";

        try {
            stat=conn.createStatement();
            stat.execute(sql);
            flag = true;
            System.out.println("已删除");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("未成功删除-SQL："+sql);
        } finally {
            ConnectionUtil.close(null, stat, conn);
        }

        return flag;
    }

    //修改
    public boolean editCustomer(Customer customer){
        boolean flag = false;
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt=null;
        String sql=null;
        sql="update customer set Name=?,Password=?,Contactor=?,Address=?,Postcode=?,Tel=?,Fax=? where customerCode=? ";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(8,customer.getCustomerCode() );
            pstmt.setString(1,customer.getName() );
            pstmt.setString(2,customer.getPassword() );
            pstmt.setString(3,customer.getContactor() );
            pstmt.setString(4,customer.getAddress() );
            pstmt.setString(5,customer.getPostcode() );
            pstmt.setString(6,customer.getTel() );
            pstmt.setString(7,customer.getFax() );

            pstmt.executeUpdate();
            flag = true;
            System.out.println("修改信息成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("客户添加失败-SQL:"+sql);
        } finally {
            ConnectionUtil.close(null, pstmt, conn);
        }

        return flag;
    }

    //得到所有客户
    public ArrayList<Customer> getAll(){

        ArrayList<Customer> al=new ArrayList<Customer>();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from customer ";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String customerCode=rs.getString(1);
                String name=rs.getString(2);;
                String contactor=rs.getString(4);;
                String address=rs.getString(5);;
                String postcode=rs.getString(6);;
                String tel=rs.getString(7);;
                String fax=rs.getString(8);;
                String createDate=rs.getString(9);;

                al.add(new Customer(customerCode, name, contactor, address, postcode, tel, fax, createDate));
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

    //查重
    public boolean checkId(String id){

        boolean flag = false;
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from customer where customerCode="+"'"+id+"'";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return flag;
    }

    //根据ID得到用户
    public Customer getOne(String id){
        Customer customer = new Customer();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from customer where customerCode="+"'"+id+"'";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();

            customer.setCustomerCode(rs.getString(1));
            customer.setName(rs.getString(2));
            customer.setPassword(rs.getString(3));
            customer.setContactor(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setPostcode(rs.getString(6));
            customer.setTel(rs.getString(7));
            customer.setFax(rs.getString(8));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return customer;
    }

    //根据name得到用户
    public Customer getOneByName(String name){
        Customer customer = new Customer();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from customer where name="+"'"+name+"'";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();

            customer.setCustomerCode(rs.getString(1));
            customer.setName(rs.getString(2));
            customer.setPassword(rs.getString(3));
            customer.setContactor(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setPostcode(rs.getString(6));
            customer.setTel(rs.getString(7));
            customer.setFax(rs.getString(8));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return customer;
    }


    /*public static void main(String[] args) {
        CustomerDao cd = new CustomerDao();
        //cd.addCustomer(new Customer("0001", "江西重汽", "123"));
        //cd.addCustomer(new Customer("0003", "厦门烟花", "123"));
        //cd.addCustomer(new Customer("0002", "吉林花卉", "123"));
        //System.out.println(cd.checkId("0009"));
        //cd.delCustomer("0005");
    }*/
    /*public static void main(String[] args) {
        System.out.println(CustomerDao.getInstance().getOneByName("汉东大学").getCustomerCode());
    }*/

}
