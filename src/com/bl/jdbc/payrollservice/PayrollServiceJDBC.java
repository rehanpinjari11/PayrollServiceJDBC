package com.bl.jdbc.payrollservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayrollServiceJDBC {

    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "Rehan@123";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            //create connection for sql database
            Connection con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Database connected successfully!");
                System.out.println("Connection Info: " + con);
                java.sql.Driver driver = DriverManager.getDriver(url);
                System.out.println("JDBC Driver: " + driver.getClass().getName());
                java.util.Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();

                while (driverList.hasMoreElements()) {
                    java.sql.Driver d = driverList.nextElement();
                    System.out.println("Registered JDBC Driver: " + d.getClass().getName());
                }

                con.close();
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

}


