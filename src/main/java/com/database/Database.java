package com.database;

import java.sql.*;

public final class Database  {
    private static Connection connect = null;
    private static Database database;
    private static Statement statement;

    private Database() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "java_api";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "admin";
        String password = "Admin_4321";
        try {

            Class.forName(driver);
            connect = DriverManager.getConnection(url+dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",userName,password);

            if(connect != null ){
                System.out.println("Connected to the database");
            }

        } catch (ClassNotFoundException ex) {

            System.out.println("Could not find database driver class");
            ex.printStackTrace();


        } catch (SQLException ex) {

            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();

        }
    }

    public static synchronized Database getDbCon() {
        if ( database == null ) {
            database = new Database();
        }
        return database;

    }

    public void query(String query){
        System.out.println(query);
    }

    public void insert(String insertQuery)  {
       System.out.println(insertQuery);
    }
}
