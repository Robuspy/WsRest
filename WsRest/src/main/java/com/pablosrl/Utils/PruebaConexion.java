package com.pablosrl.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {

    private static String url        = "jdbc:oracle:thin:@192.168.100.225:1521:orcl";
    private static String username   = "wsinv";
    private static String pass       = "wsinv";
    private static Connection connection;

    public static Connection getInstance()  throws SQLException{
    	/*
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, pass);
        }
        return connection;*/
    	return DriverManager.getConnection(url, username, pass);
    }


}
