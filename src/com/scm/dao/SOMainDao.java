package com.scm.dao;

import com.scm.model.SCMUser;
import com.scm.model.SOItem;
import com.scm.model.SOMain;
import com.scm.model.Page;
import com.scm.util.ConnectionUtil;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭老师 on 2017/5/4.
 */
public class SOMainDao {

    //单例模式
    private static final SOMainDao instance = new SOMainDao();
    private SOMainDao(){}
    public static SOMainDao getInstance(){
        return instance;
    }


    //检查是否存在[根据客户编号]
    public boolean checkCustomer(String id){
        boolean flag = false;
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from somain where customerCode="+"'"+id+"'";

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

    //添加主单
    public boolean addSOMain(SOMain sm){
        boolean flag = false;
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt=null;
        String sql=null;
        sql="insert into somain(SOID,CustomerCode,Account,CreateTime,TipFee,ProductTotal," +
                "SOTotal,PayType,PrePayFee,Status,Remark,StockTime," +
                "StockUser,PayTime,PayUser,PrePayTime,PrePayUser,EndTime," +
                "EndUser) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,sm.getSoID());
            pstmt.setString(2,sm.getCustomerCode());
            pstmt.setString(3,sm.getAcount());
            pstmt.setString(4,sm.getCreateTime());
            pstmt.setFloat(5,sm.getTipFee());
            pstmt.setFloat(6,sm.getProductTotal());
            pstmt.setFloat(7,sm.getSoTotal());

            pstmt.setString(8,sm.getPayType());
            pstmt.setFloat(9,sm.getPrePayFee());
            pstmt.setInt(10,sm.getStatus());

            pstmt.setString(11,sm.getRemark());
            pstmt.setString(12,sm.getStockTime());
            pstmt.setString(13,sm.getStockUser());
            pstmt.setString(14,sm.getPayTime());
            pstmt.setString(15,sm.getPayUser());
            pstmt.setString(16,sm.getPrePayTime());
            pstmt.setString(17,sm.getPrePayUser());
            pstmt.setString(18,sm.getEndTime());
            pstmt.setString(19,sm.getEndUser());

            pstmt.executeUpdate();
            flag = true;
            System.out.println("主单添加成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("主单添加失败-SQL:"+sql);
        } finally {
            ConnectionUtil.close(null, pstmt, conn);
        }
        return flag;
    }

    /*================【事务处理-业务】=================*/

    //【添加-事务处理】添加主单，明细，修改待发货数量
    public boolean addSOMain(SOMain sm,ArrayList<SOItem> al){
        boolean flag = false;

        Connection conn=null;
        conn = ConnectionUtil.getConn();
        Statement stat=null;

        String main=null;
        String item=null;
        String product=null;

        main="insert into somain values('"+sm.getSoID()+"','"+sm.getCustomerCode()+"','"+sm.getAcount()+"','"+sm.getCreateTime()
                +"',"+sm.getTipFee()+","+sm.getProductTotal()+","+sm.getSoTotal()
                +",'"+sm.getPayType()+"',"+sm.getPrePayFee()+","+sm.getStatus()+",'"
                +sm.getRemark()+"','"+sm.getStockTime()+"','"+sm.getStockUser()+"','"+sm.getPayTime()+"','"+sm.getPayUser()+"','"+sm.getPrePayTime()+"','"+sm.getPrePayUser()+"','"+sm.getEndTime()+"','"+sm.getEndUser()+"')";

        product="update ((select * from soitem where SOID='"+sm.getSoID()+"') t1 join " +
                "product on t1.productCode=product.productCode) set SONum=SONum+Num";

        int len=0;
        len=al.size();

        try {
            conn.setAutoCommit(false);
            stat = conn.createStatement();

            stat.addBatch(main);

            if (len!=0) {
                for (int i = 0; i <al.size() ; i++) {
                    item="insert into soitem values('"+sm.getSoID()+"','"
                            +al.get(i).getProductCode()+"',"+al.get(i).getUnitPrice()+","+al.get(i).getNum()+",'"
                            +al.get(i).getUnitName()+"',"+al.get(i).getItemPrice()+")";
                    stat.addBatch(item);
                }
                stat.addBatch(product);
            }

            stat.executeBatch();
            conn.commit();
            flag = true;
        } catch (SQLException e) {

            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.close(null, stat, conn);
        }
        return flag;
    }

    //【删除-事务处理】
    public boolean delSOMain(String soid){
        boolean flag = false;

        Connection conn=null;
        conn = ConnectionUtil.getConn();
        Statement stat=null;

        String product=null;
        String item=null;
        String main=null;

        product="update ((select * from soitem where SOID='"+soid+"') t1 join " +
                "product on t1.productCode=product.productCode) set SONum=SONum-Num";
        item="delete from soitem where soid='"+soid+"'";;
        main="delete from somain where soid='"+soid+"'";

        int len=0;
        len=SOItemDao.getInstance().getBySoid(soid).size();


        try {
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            if (len!=0) {
                stat.addBatch(product);
                stat.addBatch(item);
            }
            stat.addBatch(main);

            stat.executeBatch();
            conn.commit();
            flag = true;
        } catch (SQLException e) {

            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.close(null, stat, conn);
        }
        return flag;
    }

    //【修改-事务处理】
    public boolean updateSOMain(String soid,SOMain sm,ArrayList<SOItem> al){
        boolean flag = false;

        Connection conn=null;
        conn = ConnectionUtil.getConn();
        Statement stat=null;

        String dproduct = null;
        String ditem = null;
        String main = null;
        String product = null;
        String item = null;

        int len1=0;
        int len2=0;
        len1=SOItemDao.getInstance().getBySoid(soid).size();
        len2=al.size();

        dproduct="update ((select * from soitem where SOID='"+soid+"') t1 join " +
                "product on t1.productCode=product.productCode) set SONum=SONum-Num";
        ditem="delete from soitem where soid='"+soid+"'";;

        main="update somain set customerCode='"+sm.getCustomerCode()+"',account='"+sm.getAcount()
                +"',tipfee="+sm.getTipFee()+",producttotal="+sm.getProductTotal()+",sototal="+sm.getSoTotal()
                +",paytype='"+sm.getPayType()+"',prepayfee="+sm.getPrePayFee()+",status="+sm.getStatus()+" where soid='"+sm.getSoID()+"'";

        product="update ((select * from soitem where SOID='"+sm.getSoID()+"') t1 join " +
                "product on t1.productCode=product.productCode) set SONum=SONum+Num";

        try {
            conn.setAutoCommit(false);
            stat = conn.createStatement();

            if (len1!=0) {
                stat.addBatch(dproduct);
                stat.addBatch(ditem);
            }
            stat.addBatch(main);

            if (len2!=0) {
                for (int i = 0; i <al.size() ; i++) {
                    item="insert into soitem values('"+sm.getSoID()+"','"
                            +al.get(i).getProductCode()+"',"+al.get(i).getUnitPrice()+","+al.get(i).getNum()+",'"
                            +al.get(i).getUnitName()+"',"+al.get(i).getItemPrice()+")";

                    stat.addBatch(item);
                }
                stat.addBatch(product);
            }

            stat.executeBatch();
            conn.commit();
            flag = true;
        } catch (SQLException e) {
            System.out.println(main);
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectionUtil.close(null, stat, conn);
        }
        return flag;
    }

    /*================【查询---报表】=================*/
    //【状态反馈】
    public int gs(String status){
        int i=0;
        if ("新订单".equals(status))  i=1;
        else  if ("已收货".equals(status))  i=2;
        else  if ("已付款".equals(status))  i=3;
        else  if ("已了结".equals(status))  i=4;
        else  if ("已预付".equals(status))  i=5;
        return i;
    }

    //【查询】
    public List<SOMain> getFind(String soid, String startTime, String endTime, String customerName,
                                String payType,String status,String condition){
        List<SOMain> list = new ArrayList<>();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        String sql = "select *from somain where 1=1 ";

        boolean f1="".equals(soid);
        boolean f2="".equals(startTime);
        boolean f3="".equals(endTime);
        boolean f4="".equals(customerName);
        boolean f5="".equals(payType);
        boolean f6="".equals(status);
        boolean f7="满足上述所有条件".equals(condition);
        String sel=(f7)?"and":"or";

        if (!f1)     sql+=(sel+" soid like '"+soid+"'");
        if (!f4)     sql+=(sel+" customercode='"+CustomerDao.getInstance().getOneByName(customerName).getCustomerCode()+"'");
        if (!f5)     sql+=(sel+" paytype='"+payType+"'");
        if (!f6)     sql+=(sel+" status="+gs(status)/*turnStatus*/);
    //时间查询
        if (!f2&&f3)     sql+=(" and createTime >'"+startTime+"'");
        if (!f3&&f2)     sql+=(" and createTime <'"+endTime+"'");
        if (!f2&&!f3)    sql+=(" and createTime between '"+startTime+"' and '"+endTime+"'");

        System.out.println(sql);
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String soi=rs.getString(1);
                String code=rs.getString(2);;
                String account=rs.getString(3);
                String time = rs.getString(4);

                float tipfee = rs.getFloat(5);
                float proTotal = rs.getFloat(6);
                float soTatal = rs.getFloat(7);

                String paytype = rs.getString(8);
                float ppf = rs.getFloat(9);
                int s = rs.getInt(10);

                String  remark= rs.getString(11);
                String  stocktime= rs.getString(12);
                String  stockuser= rs.getString(13);
                String  paytime= rs.getString(14);
                String  payuser= rs.getString(15);
                String  ppt= rs.getString(16);
                String  ppu= rs.getString(17);
                String  et= rs.getString(18);
                String  eu= rs.getString(19);

                list.add(new SOMain(soi, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, s,
                        remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));

            }
            System.out.println("得到主单数为"+list.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("得到主单失败-SQL"+sql);
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }

        return list;

    }
    //【报表】
    public List<SOMain> getReport(String year,String month){
        List<SOMain> list = new ArrayList<>();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        String sql = "select *from somain where 1=1 ";

        boolean f1="".equals(year);
        boolean f2="".equals(month);

        if (!f2)   sql+=" and createTime between '"+year+"-"+month+"-01'"+" and '"+year+"-"+month+"-31'";
        if (f2)   sql+=" and createTime between '"+year+"-"+"01-01'"+" and '"+year+"-"+"12-31'";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String soi=rs.getString(1);
                String code=rs.getString(2);;
                String account=rs.getString(3);
                String time = rs.getString(4);

                float tipfee = rs.getFloat(5);
                float proTotal = rs.getFloat(6);
                float soTatal = rs.getFloat(7);

                String paytype = rs.getString(8);
                float ppf = rs.getFloat(9);
                int s = rs.getInt(10);

                String  remark= rs.getString(11);
                String  stocktime= rs.getString(12);
                String  stockuser= rs.getString(13);
                String  paytime= rs.getString(14);
                String  payuser= rs.getString(15);
                String  ppt= rs.getString(16);
                String  ppu= rs.getString(17);
                String  et= rs.getString(18);
                String  eu= rs.getString(19);

                list.add(new SOMain(soi, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, s,
                        remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));
            }
            System.out.println("得到主单数为"+list.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("得到主单失败-SQL"+sql);
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return list;
    }

    //【了结】
    public List<SOMain> getEndList(String payType,String status) throws UnsupportedEncodingException {
        List<SOMain> list = new ArrayList<>();
        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = "select *from somain where paytype='"+payType+"' and status="+status;
        String sql1 = "select *from somain where status=3";
        String sql2 = "select *from somain where paytype='款到发货' and status=2";

        System.out.println(sql);
        try {
            if (payType==null&&status==null) {
                pstmt = conn.prepareStatement(sql1);
                rs = pstmt.executeQuery();

                while (rs.next()){
                    String soi=rs.getString(1);
                    String code=rs.getString(2);;
                    String account=rs.getString(3);
                    String time = rs.getString(4);

                    float tipfee = rs.getFloat(5);
                    float proTotal = rs.getFloat(6);
                    float soTatal = rs.getFloat(7);

                    String paytype = rs.getString(8);
                    float ppf = rs.getFloat(9);
                    int s = rs.getInt(10);

                    String  remark= rs.getString(11);
                    String  stocktime= rs.getString(12);
                    String  stockuser= rs.getString(13);
                    String  paytime= rs.getString(14);
                    String  payuser= rs.getString(15);
                    String  ppt= rs.getString(16);
                    String  ppu= rs.getString(17);
                    String  et= rs.getString(18);
                    String  eu= rs.getString(19);

                    list.add(new SOMain(soi, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, s,
                            remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));
                }
                pstmt = conn.prepareStatement(sql2);
                rs = pstmt.executeQuery();

                while (rs.next()){
                    String soi=rs.getString(1);
                    String code=rs.getString(2);;
                    String account=rs.getString(3);
                    String time = rs.getString(4);

                    float tipfee = rs.getFloat(5);
                    float proTotal = rs.getFloat(6);
                    float soTatal = rs.getFloat(7);

                    String paytype = rs.getString(8);
                    float ppf = rs.getFloat(9);
                    int s = rs.getInt(10);

                    String  remark= rs.getString(11);
                    String  stocktime= rs.getString(12);
                    String  stockuser= rs.getString(13);
                    String  paytime= rs.getString(14);
                    String  payuser= rs.getString(15);
                    String  ppt= rs.getString(16);
                    String  ppu= rs.getString(17);
                    String  et= rs.getString(18);
                    String  eu= rs.getString(19);

                    list.add(new SOMain(soi, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, s,
                            remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));
                }
            } else {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

                while (rs.next()){
                    String soi=rs.getString(1);
                    String code=rs.getString(2);;
                    String account=rs.getString(3);
                    String time = rs.getString(4);

                    float tipfee = rs.getFloat(5);
                    float proTotal = rs.getFloat(6);
                    float soTatal = rs.getFloat(7);

                    String paytype = rs.getString(8);
                    float ppf = rs.getFloat(9);
                    int s = rs.getInt(10);

                    String  remark= rs.getString(11);
                    String  stocktime= rs.getString(12);
                    String  stockuser= rs.getString(13);
                    String  paytime= rs.getString(14);
                    String  payuser= rs.getString(15);
                    String  ppt= rs.getString(16);
                    String  ppu= rs.getString(17);
                    String  et= rs.getString(18);
                    String  eu= rs.getString(19);

                    list.add(new SOMain(soi, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, s,
                            remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));
                }

            }
            System.out.println("得到主单数为"+list.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("得到主单失败-SQL"+sql);
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }

        return list;
    }

    public void end(String id,String enduser){
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        Statement pstmt=null;
        ResultSet rs = null;

        String sql=null;
        sql = "UPDATE somain set status=4,endtime=now(),enduser='"+enduser+"' where soid='"+id+"'";
        System.out.println(sql);
        try {
            pstmt = conn.createStatement();
            pstmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
    }
    /*=========================================*/
    //得到所有SOMAIN对象
    public ArrayList<SOMain> getAll(){
        ArrayList<SOMain> al = new ArrayList<SOMain>();
        Connection conn=null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt=null;
        ResultSet rs = null;

        String sql=null;
        sql = "select * from somain ";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String soid=rs.getString(1);
                String code=rs.getString(2);;
                String account=rs.getString(3);
                String time = rs.getString(4);

                float tipfee = rs.getFloat(5);
                float proTotal = rs.getFloat(6);
                float soTatal = rs.getFloat(7);

                String paytype = rs.getString(8);
                float ppf = rs.getFloat(9);
                int status = rs.getInt(10);

                String  remark= rs.getString(11);
                String  stocktime= rs.getString(12);
                String  stockuser= rs.getString(13);
                String  paytime= rs.getString(14);
                String  payuser= rs.getString(15);
                String  ppt= rs.getString(16);
                String  ppu= rs.getString(17);
                String  et= rs.getString(18);
                String  eu= rs.getString(19);

                al.add(new SOMain(soid, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, status,
                        remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));

            }
            System.out.println("导出主单数为"+al.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("导出主单失败-SQL"+sql);
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }
        return al;
    }

    //由ID得到SOMain
    public SOMain getOneById(String id){

        SOMain sm=new SOMain();

        Connection conn =null;
        conn = ConnectionUtil.getConn();
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String sql = null;

        sql = "select * from somain where soid="+"'"+id+"'";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();

            sm=new SOMain(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getFloat(5),rs.getFloat(6),rs.getFloat(7),
                    rs.getString(8),rs.getFloat(9),rs.getInt(10),
                    rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),
                    rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.close(rs,pstmt,conn);
        }

        return sm;
    }

    /*================【分页相关】=================*/
    //【分页】查询数据库表格的总条数
    public static int getCount(String tableName){
        Connection conn = ConnectionUtil.getConn();
        Statement stat = null;
        ResultSet rs=null;
        try {
            stat=conn.createStatement();
            rs=stat.executeQuery("select count(*)c from "+tableName);
            if(rs.next()){
                return rs.getInt("c");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally{
            ConnectionUtil.close(rs, stat, conn);
        }
    }

    //【分页】通过分页得到对象
    public static Page getSomain(Page sp){
        Connection conn = ConnectionUtil.getConn();
        //select * from foods limit ?,?  第一个？表示起始的条数  第二个？表示每页显示的条数
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select * from somain limit ?,?");
            ps.setInt(1, sp.getCurrentPage()*sp.getCount()); //起始条数 是当前页*每页显示条数
            ps.setInt(2, sp.getCount());//page.getCount()每页显示的条数
            rs=ps.executeQuery();
            List<SOMain> list=new ArrayList<SOMain>(sp.getCount());//List集合的元素个数最好就显示的数据条数
            while(rs.next()){

                String soid=rs.getString(1);
                String code=rs.getString(2);;
                String account=rs.getString(3);
                String time = rs.getString(4);

                float tipfee = rs.getFloat(5);
                float proTotal = rs.getFloat(6);
                float soTatal = rs.getFloat(7);

                String paytype = rs.getString(8);
                float ppf = rs.getFloat(9);
                int status = rs.getInt(10);

                String  remark= rs.getString(11);
                String  stocktime= rs.getString(12);
                String  stockuser= rs.getString(13);
                String  paytime= rs.getString(14);
                String  payuser= rs.getString(15);
                String  ppt= rs.getString(16);
                String  ppu= rs.getString(17);
                String  et= rs.getString(18);
                String  eu= rs.getString(19);

                list.add(new SOMain(soid, code, account, time, tipfee, proTotal, soTatal, paytype, ppf, status,
                        remark, stocktime, stockuser, paytime, payuser, ppt, ppu, et, eu));
            }
            sp.setPageMessage(list); //将数据放在page对象中
            return sp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally{
            ConnectionUtil.close(rs, ps, conn);
        }
    }


    //根据account得到SCMUSER对象
    public SCMUser getSCMUser(String account){
        SCMUser scm = new SCMUser();
        Connection conn = ConnectionUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select * from scmuser where account=?");
            ps.setString(1, account);
            rs=ps.executeQuery();
            while(rs.next()){
                scm.setAccount(rs.getString("account"));
                scm.setPassword(rs.getString("password"));
                scm.setName(rs.getString("name"));
                scm.setCreateDate(rs.getString("createDate"));
                //scm.setStatus(rs.getString("status").equals("0")?"未锁定":"锁定");
                scm.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }finally{
            ConnectionUtil.close(rs, ps, conn);
        }
        return scm;
    }

}
