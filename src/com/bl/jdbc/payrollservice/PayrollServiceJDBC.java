package com.bl.jdbc.payrollservice;

import java.sql.*;

public class PayrollServiceJDBC {

    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "Rehan@123";
        String query = "select * from employee_payroll;";

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

                Statement statement = con.createStatement();
                ResultSet resultset = statement .executeQuery(query);
                while (resultset.next())
                {
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    String phone_number = resultset.getString("phone_number");
                    String address = resultset.getString("address");
                    String department = resultset.getString("department");
                    String gender = resultset.getString("gender");
                    Double basic_pay = resultset.getDouble("basic_pay");
                    Double deductions = resultset.getDouble("deductions");
                    Double taxable_pay = resultset.getDouble("taxable_pay");
                    Double tax = resultset.getDouble("tax");
                    Double net_pay = resultset.getDouble("net_Pay");
                    String start_date = resultset.getString("start_Date");
                    System.out.println(id+" "+name+" "+" "+gender+" "+start_date+" "+phone_number+" "+address+" "+department+" "+basic_pay+" "+deductions+" "+taxable_pay+" "+tax+" "+net_pay);
                    System.out.println();

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


