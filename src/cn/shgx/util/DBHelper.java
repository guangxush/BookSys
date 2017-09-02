package cn.shgx.util;

import java.sql.*;

/**
 * Created by Consule on 2017/9/2.
 */
public class DBHelper {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    //获得连接
    private void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/booksys";
            conn =  DriverManager.getConnection(url,"root","Root");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //关闭连接
    public void close(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //更新 数据库  增加、修改、删除
    //sql=insert into book(name,price,author,pubDate)values(?,?,?,?)
    public int executeUpdate(String sql,Object...objects){
        try {
            this.getConnection();
            ps = conn.prepareStatement(sql);
            if (objects != null) //设置参数
            {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            this.close();
        }
        return -1;
    }
    //查询
    public ResultSet  executeQuery(String sql,Object...objects){
        try {
            this.getConnection();
            ps = conn.prepareStatement(sql);
            if (objects != null) //设置参数
            {
                for (int i = 0; i < objects.length; i++) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            return rs=ps.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
