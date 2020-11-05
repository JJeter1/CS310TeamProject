/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.tas_fa20;
import java.sql.*;
/**
 *
 * @author ralph
 */
public class Shift {
    String lunchduration = "30:00";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   public static final String DB_URL = "jdbc:mysql://localhost:3306/Tas";
   public static final String USER = "tasuser";
   public static final String PASS = "cs310groupC";
   
    public static void main(String args[]){
    Connection conn = null;
   Statement stmt = null;
   try{
       Class.forName("com.mysql.jdbc.Driver");
       System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql = "SELECT * FROM shift";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
         //Retrieve by column name
         int id = rs.getInt("id");
         String description = rs.getString("description");
         Time start  = rs.getTime("start");
         Time stop = rs.getTime("stop");
         int interval = rs.getInt("interval");
         int graceperiod = rs.getInt("graceperiod");
         int dock = rs.getInt("dock");
         Time lunchstart = rs.getTime("lunchstart");
         Time lunchstop = rs.getTime("lunchstop");
         int lunchdeduct = rs.getInt("lunchdeduct");

         //Display values
         System.out.print("Shift ID: " + id + " ");
         System.out.print("Shift description: " + description + " ");
         System.out.print("Shift start: " + start + " ");
         System.out.print(", Shift stop: " + stop + " ");
         System.out.print(", Shift interval: " + interval + " ");
         System.out.print("Grace Period: " + graceperiod + " ");
         System.out.print("Dock: " + dock + " ");
         System.out.print("Lunch Start: " + lunchstart + " ");
         System.out.print("Lunch Stop: " + lunchstop + " ");
         System.out.println("Late Lunch Deduction: " + lunchdeduct);
      }
      rs.close();
   }
   catch(SQLException se){
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
      }//end finally try
   }//end try
   Shift obj = new Shift();
   System.out.println("Lunch duration: " + obj.lunchduration);
   
}
}
