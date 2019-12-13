/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triper360;

import java.sql.*;
import java.util.*;
/**
 *
 * @author My AsUs
 */
public class DatabaseConnection implements AutoCloseable {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/triper360?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    Connection connection;
    private static Properties properties;

    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    public DatabaseConnection() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
        
            connection = DriverManager.getConnection(DATABASE_URL, getProperties());
        } catch (SQLException e){e.printStackTrace();}
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
//    public static void main(String args[]) throws SQLException{
//        
//        try (DatabaseConnection dbc = new DatabaseConnection()) {
//            String sql = "INSERT INTO `user`(`name`, `email`, `password`, `mobile`, `address`) VALUES ('rafsan', 'mortudsdi@balsl.com', 'basall', 'hsdgsh', 'kuratoli')";
//
//            try (PreparedStatement Ups = dbc.connection.prepareStatement(sql)) {
//                Ups.executeUpdate();
//            }
//        }
//        
//        try (DatabaseConnection dbc = new DatabaseConnection()) {
//            String username = "mortudsdi@balsl.com";
//            String sql = "SELECT * FROM user WHERE email=?";
//
//            try (PreparedStatement ps = dbc.connection.prepareStatement(sql)) {
//                ps.setString(1, username);
//
//                try (ResultSet rs = ps.executeQuery()) {
//                    rs.next();
//                    System.out.println(rs.getInt(1));
//                    System.out.println(rs.getString(2));
//                    System.out.println(rs.getString(3));
//                }
//            }
//        }
//    }
}