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
public class TASDatabase {
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Tas" , "tasuser" , "cs310groupC");
        } /*this will get us the connection to the database itself, where we will
        be able to get information from the tables */
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
