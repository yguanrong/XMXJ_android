package com.example.guanrong.xmxj_android.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBtest{

    private static Connection getSQLConnection()
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://你电脑的IP地址/你项目数据的名字",
                    "root", "root");  //本机模拟测试用

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public static String login(String uname,String pwd)
    {
        String result = "0";
        try {
            Connection conn = getSQLConnection();
            String sql = "select * from user where name='" + uname + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()){
                if (pwd.equals(rs.getString("password"))){
                    result = "1";
                }else {
                    result = "0";
                }
            }else {
                result = "-1";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}