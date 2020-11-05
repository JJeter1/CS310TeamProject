/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.tas_fa20;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jeter4ja
 */
public class TASDatabase {
    Connection con;
    public void TASDatabase(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Tas" , "tasuser" , "cs310groupC");
        } /*this will get us the connection to the database itself, where we will
        be able to get information from the tables */
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public Badge getBadge(String id){
      Badge badgeObject = new Badge();
      return badgeObject;
    }
    public Shift getShift(int id){
        Shift shiftObject = new Shift();
        return shiftObject;
    }
    public Punch getPunch(int id){
        Punch punchObject = new Punch();
        return punchObject;
    }
    public Shift getShift(Badge b){
        Shift shiftObject = new Shift();
        return shiftObject;
    }
    public void close(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
