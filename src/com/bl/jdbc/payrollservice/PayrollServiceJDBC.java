package com.bl.jdbc.payrollservice;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class PayrollServiceJDBC {

    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "Rehan@123";
//        String query = "SELECT gender, SUM(basic_pay) AS total_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender;";
        String query = "select * from employee_payroll WHERE start_date BETWEEN CAST('2019-01-03' AS DATE) AND DATE (NOW());";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection for SQL database
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

                System.out.println();

                // First query: retrieving employee payroll details
                Statement statement = con.createStatement();
                ResultSet resultset = statement.executeQuery(query);

                while (resultset.next()) {
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
                    System.out.println(id + " " + name + " " + gender + " " + start_date + " " + phone_number + " " + address + " " + department + " " + basic_pay + " " + deductions + " " + taxable_pay + " " + tax + " " + net_pay);

                }

                System.out.println();

                resultset.close(); // Close the first result set after processing

                // Second query: retrieving sum of salaries by gender
                System.out.println("------ Sum of salary ------");

                ResultSet rs1 = statement.executeQuery("SELECT gender, SUM(basic_pay) AS total_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender;");
                while (rs1.next()) {
                    System.out.println("Gender: " + rs1.getString("gender"));
                    System.out.println("Total Salary: " + rs1.getDouble("total_salary"));
                }
                System.out.println();

                rs1.close();

                System.out.println("------ Avg of salary ------");

                ResultSet rs2 = statement.executeQuery("SELECT gender, AVG(basic_pay) AS avg_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender;");
                while (rs2.next()){
                    System.out.println("Gender: " + rs2.getString("gender"));
                    System.out.println("Avg Salary: " + rs2.getDouble("avg_salary"));
                }
                System.out.println();

                rs2.close();

                System.out.println("----- Min of salary -----");

                ResultSet rs3 = statement.executeQuery("SELECT gender, MIN(basic_pay) AS min_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender;");
                while (rs3.next()){
                    System.out.println("Gender: " + rs3.getString("gender"));
                    System.out.println("Min Salary: " + rs3.getDouble("min_salary"));
                }

                System.out.println();

                rs3.close();

                System.out.println("----- Max of salary -----");

                ResultSet rs4 = statement.executeQuery("SELECT gender, MAX(basic_pay) AS max_salary FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender;");
                while (rs4.next()){
                    System.out.println("Gender: " + rs4.getString("gender"));
                    System.out.println("Max Salary: " + rs4.getDouble("max_salary"));
                }

                System.out.println();

                rs4.close();

                System.out.println("----- Count of Employees ------");

                ResultSet rs5 = statement.executeQuery("SELECT gender, COUNT(*) AS employee_count FROM employee_payroll WHERE gender = 'M' OR gender = 'F' GROUP BY gender;");
                while (rs5.next()){
                    System.out.println("Gender: " + rs5.getString("gender"));
                    System.out.println("Max Salary: " + rs5.getDouble("employee_count"));
                }

                System.out.println();

                rs5.close();


                con.close(); // Close the connection
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

}


