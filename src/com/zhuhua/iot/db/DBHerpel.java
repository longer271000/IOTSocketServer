package com.zhuhua.iot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBHerpel {

    private static Connection Conn; // ���ݿ����Ӷ���

    // ���ݿ����ӵ�ַ
    private static String URL = "jdbc:mysql://xx.xx.xx.xx:8806/xx?characterEncoding=utf8&useSSL=true";

    // ���ݿ���û���
    private static String UserName = "xx";
    // ���ݿ������
    private static String Password = "xxxxxx";
    
    static String TAG="DBHerpel";

    /**
     * * @Description: TODO ��ȡ�������ݿ��Connection����
     * @param @return
     * @return Connection �������ݵĶ���
     * @author ����i
     */
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.jdbc.Driver"); // ��������

            System.out.println("���������ɹ�!!!");
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {

            //ͨ��DriverManager���getConenction����ָ����������,�������ݿ�
            Conn = DriverManager.getConnection(URL, UserName, Password);
            System.out.println("�������ݿ�ɹ�!!!");

            //�������Ӷ���
            return Conn;

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    public static ResultSet  query(String Sql) {
		  // ���Ӷ���
	     Connection conn = null;
	    // ����sql���
	     Statement stt;
	    // �����
	     ResultSet set = null;
    	 try {

             // ��ȡ����
             conn = DBHerpel.getConnection();
             if (conn == null)
                 return null;
             // ִ��sql���
             stt = conn.createStatement();
             // ���ؽ����
             set = stt.executeQuery(Sql);
             // ��ȡ����

         } catch (Exception e) {
             e.printStackTrace();
         } finally {

             // �ͷ���Դ
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
              // ��ȡ����
    		  conn = DBHerpel.getConnection();

              if (conn == null) {
                  System.out.println(TAG+"--conn--is null ");
                  return false;

              }
              //��ȡStatement����
              stt = conn.createStatement();
              System.out.println(TAG+"--execSQL--"+sql);

              //ִ��sql���
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
