package com.scm.util;

import java.sql.*;

/**
 * Created by 郭老师 on 2017/5/2.
 */
public class ConnectionUtil {
    //加载数据库
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //连接数据库
    public static Connection getConn(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scm","root","1800438578");

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return conn;
    }

    //关闭数据库
    public static void close(ResultSet rs, Statement stat, Connection conn){
        try {
            if(rs!=null)
                rs.close();
            rs=null;
            if(stat!=null)
                stat.close();
            stat=null;
            if(conn!=null)
                conn.close();
            conn=null;
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


}
