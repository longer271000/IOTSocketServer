package com.zhuhua.iot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBHerpel {

    private static Connection Conn; // 数据库连接对象

    // 数据库连接地址
    private static String URL = "jdbc:mysql://xx.xx.xx.xx:8806/xx?characterEncoding=utf8&useSSL=true";

    // 数据库的用户名
    private static String UserName = "xx";
    // 数据库的密码
    private static String Password = "xxxxxx";
    
    static String TAG="DBHerpel";

    /**
     * * @Description: TODO 获取访问数据库的Connection对象
     * @param @return
     * @return Connection 连接数据的对象
     * @author 情绪i
     */
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动

            System.out.println("加载驱动成功!!!");
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {

            //通过DriverManager类的getConenction方法指定三个参数,连接数据库
            Conn = DriverManager.getConnection(URL, UserName, Password);
            System.out.println("连接数据库成功!!!");

            //返回连接对象
            return Conn;

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    public static ResultSet  query(String Sql) {
		  // 连接对象
	     Connection conn = null;
	    // 传递sql语句
	     Statement stt;
	    // 结果集
	     ResultSet set = null;
    	 try {

             // 获取连接
             conn = DBHerpel.getConnection();
             if (conn == null)
                 return null;
             // 执行sql语句
             stt = conn.createStatement();
             // 返回结果集
             set = stt.executeQuery(Sql);
             // 获取数据

         } catch (Exception e) {
             e.printStackTrace();
         } finally {

             // 释放资源
             try {
                 if(conn!=null)
                 conn.close();
             } catch (Exception e2) {
                 // TODO: handle exception
             }

         }
		return set;
    }
    public static boolean execSQL(String sql) {
    	 Connection conn=null;
    	 Statement stt=null;
    	  try {
              // 获取连接
    		  conn = DBHerpel.getConnection();

              if (conn == null) {
                  System.out.println(TAG+"--conn--is null ");
                  return false;

              }
              //获取Statement对象
              stt = conn.createStatement();
              System.out.println(TAG+"--execSQL--"+sql);

              //执行sql语句
              stt.executeUpdate(sql);

          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              try {
            	  stt.close();
                  conn.close();
                  return true;
              } catch (Exception e2) {
                  // TODO: handle exception
              }
          }
		return false;
    }

}
