/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.tas_fa20;

import java.sql.*;

/**
 *
 * @author jeter4ja
 */
public class punch {
    String adjustmenttype = null;
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/Tas";
   static final String USER = "tasuser";
   static final String PASS = "cs310groupC";
   public static void main(String args[]){
       Connection conn = null;
       Statement stmt = null;
       try{
           Class.forName("com.mysql.jdbc.Driver");
           System.out.println("Connecting to selected database...");
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           System.out.println("Connected to database successfully...");
           System.out.println("Creating Statement...");
           stmt = conn.createStatement();
           String sql = "SELECT ClockIn, LunchClockOut, LunchClockIn, ClockOut FROM punch";
           ResultSet rs = stmt.executeQuery(sql);
           while(rs.next()){
               //Get Punch Values
               String ClockIn = rs.getString("ClockIn");
               String LunchCO = rs.getString("LunchClockOut");
               String LunchCI = rs.getString("LunchClockIn");
               String ClockOut = rs.getString("ClockOut");
               //Display Values
               System.out.println("Clocked in at " + ClockIn);
               System.out.println("Clocked Out for lunch at " + LunchCO);
               System.out.println("Clocked in from lunch at " + LunchCI);
               System.out.println("Clocked out to go home at " + ClockOut);
           }
           rs.close();
           
       }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
       }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
       }finally{
           //finally block used to close resources
           try{
               if(stmt!=null)
                   conn.close();
           }catch(SQLException se){
               
           }
           try{
               if(conn!=null)
                   conn.close();
           }catch(SQLException se){
               se.printStackTrace();
           }
       }
       punch obj = new punch();
       System.out.println("Adjustment for lunch " + obj.adjustmenttype);
       
       
   }
}
