package com.star.foodfans.db;

import java.sql.*;

public class DBUtil {

    private String url = "jdbc:mysql://127.0.0.1:3306/class";
    private String allUrl = url + "?user=kle&password=***";

    public Connection getConnection(){
        Connection conn = null;
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);
            conn = DriverManager.getConnection(allUrl);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param rs
     * @param conn
     * @param stat
     */
    public void close(Connection conn,Statement stat, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                rs = null;
            }
        }
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                stat = null;
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conn = null;
            }
        }
    }

    /**
     * 关闭数据库连接
     * @param conn
     * @param stat
     */
    public void close(Connection conn,Statement stat){
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                stat = null;
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                conn = null;
            }
        }
    }

//    //静态内部类-单例模式 获取数据库资源连接类
//
//    private static class SingleHolder{
//        private static DBUtil INSTANCE = new DBUtil();
//    }
//
//    public static DBUtil getInstance(){
//        return SingleHolder.INSTANCE;
//    }

    private volatile static DBUtil singleton;

    private DBUtil (){}

    public static DBUtil getSingleton() {
        if (singleton == null) {
            synchronized (DBUtil.class) {
                if (singleton == null) {
                    singleton = new DBUtil();
                }
            }
        }
        return singleton;
    }

}