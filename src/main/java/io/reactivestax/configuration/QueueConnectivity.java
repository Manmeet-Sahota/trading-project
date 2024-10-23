//package org.example.configuration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class QueueConnectivity {
//    private Connection connection;
//    public Connection getConnection(){
//        if (connection!=null){
//            try{
//                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Trading","root","password123");
//                System.out.println("queue Connection done ");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//}
