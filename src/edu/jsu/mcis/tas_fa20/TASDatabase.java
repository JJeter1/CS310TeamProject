package edu.jsu.mcis.tas_fa20;

import java.sql.*;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TASDatabase {
    
    private Connection conn;
    
    public TASDatabase() {
        
        conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/tas";
            conn = DriverManager.getConnection(url, "tasuser", "cs310groupC");
        }
        catch (Exception e) { e.printStackTrace(); }
        
    }
    
    public Badge getBadge(String id) {
        
        Badge b = null;
        
        try {
       
            String query = "SELECT * FROM badge WHERE id = ?";
            PreparedStatement pstatement = conn.prepareStatement(query);     
            pstatement.setString(1, id);
            boolean hasresults = pstatement.execute();
            
            System.out.println("It is running!!");   
            
            if ( hasresults ) {
                
                ResultSet resultset = pstatement.getResultSet();
                
                if(resultset.next()) {
                    
                    String badgeId = resultset.getString("id");
                    String description = resultset.getString("description");
                    
                    b = new Badge(badgeId, description);
                    
                }
                
            }
            
            pstatement.close();
 
        }
        catch(Exception e) { e.printStackTrace(); }
        
        return b;
        
    }
    
    public Shift getShift(int id) {
        
        Shift s = null;
        
        try {
       
            String query = "select *, HOUR(`start`) AS shiftstarthour, " +
            "MINUTE(`start`) AS shiftstartminute,  HOUR(`stop`) AS shiftstophour, " +
            "MINUTE(`stop`) AS shiftstopminute, HOUR(`lunchstart`) AS lunchstarthour, " + 
            "MINUTE(`lunchstart`) AS lunchstartminute,  HOUR(`lunchstop`) AS lunchstophour, " +
            "MINUTE(`lunchstop`) AS lunchstopminute from shift WHERE id = ?";
            
            PreparedStatement pstatement = conn.prepareStatement(query);     
            pstatement.setInt(1, id);            
            boolean hasresults = pstatement.execute();
            
            System.out.println("It is running!!");   
            
            if ( hasresults ) 
            {
                
                ResultSet resultset = pstatement.getResultSet();
                
                if(resultset.next()) {
                    
                    String description = resultset.getString("description");
                    int interval = resultset.getInt("interval");
                    int graceperiod = resultset.getInt("graceperiod");
                    int dock = resultset.getInt("dock");
                    int lunchdeduct = resultset.getInt("lunchdeduct");
                    
                    LocalTime start = LocalTime.of(resultset.getInt("shiftstarthour"), resultset.getInt("shiftstartminute"));
                    LocalTime stop = LocalTime.of(resultset.getInt("shiftstophour"), resultset.getInt("shiftstopminute"));
                    
                    LocalTime lunchstart = LocalTime.of(resultset.getInt("lunchstarthour"), resultset.getInt("lunchstartminute"));
                    LocalTime lunchstop = LocalTime.of(resultset.getInt("lunchstophour"), resultset.getInt("lunchstopminute"));
                    
                    s = new Shift(id, interval, graceperiod, dock, lunchdeduct, description, start, stop, lunchstart, lunchstop);
                    
                }

            }
            
            pstatement.close();
 
        }
        catch(Exception e){ e.printStackTrace(); }
       
        return s;
        
    }
    
    public Punch getPunch(int id) {

        Punch p = null;
        
        try {
       
            String query = "SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS ts FROM punch WHERE id = ?";
            
            PreparedStatement pstatement = conn.prepareStatement(query);     
            pstatement.setInt(1, id);
            boolean hasresults = pstatement.execute();
            
            System.out.println("It is running!!");   
            
            if ( hasresults ) 
            {
                
                ResultSet resultset = pstatement.getResultSet();
                
                if (resultset.next()) {
                    
                    String badgeid = resultset.getString("badgeid");
                    int terminalid = resultset.getInt("terminalid");
                    int punchtypeid = resultset.getInt("punchtypeid");
                    long originaltimestamp = resultset.getLong("ts");
                    
                    // (int id, int terminalId, String badgeid, long originaltimestamp, int punchtypeid)
                    
                    p = new Punch(id, terminalid, badgeid, originaltimestamp, punchtypeid);
                    
                }

            }
            
            pstatement.close();
 
        }
        catch(Exception e){ e.printStackTrace(); }
        
        return p;
        
    }
    
    public Shift getShift(Badge b) {
        
        Shift s = null;
        
        try {
       
            String query = "SELECT * FROM employee WHERE badgeid = ?";
            PreparedStatement pstatement = conn.prepareStatement(query);     
            pstatement.setString(1, b.getId());
            boolean hasresults = pstatement.execute();
            
            System.out.println("It is running!!");   
            
            if ( hasresults ) {
                
                ResultSet resultset = pstatement.getResultSet();
                
                if (resultset.next()) {
                    
                    int shiftid = resultset.getInt("shiftid");
                    
                    s = getShift(shiftid);
                    
                }
                
            }
            
            pstatement.close();
 
        }
        catch(Exception e) { e.printStackTrace(); }
        
        return s;
        
    }
    public int insertPunch(Punch p){
        ResultSet resultset = null;
        int updateCount=0;
        int punchID = p.getId();
        int punchTypeID = p.getPunchtypeid();
        int punchTerminalID = p.getTerminalid();
        String badgeID = p.getBadgeid();
        long timeStamp = p.getOriginaltimestamp();
        String sqlQuery = "INSERT INTO punch (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pState = conn.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            pState.setInt(1,punchTerminalID);
            pState.setString(2,badgeID);
            pState.setLong(3,timeStamp);
            pState.setInt(4,punchTypeID);
            updateCount = pState.executeUpdate();
            if (updateCount > 0) {
                resultset = pState.getGeneratedKeys();
                   if (resultset.next()) {
                        System.out.println(resultset.getInt(1));

                    }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return punchID;
    }
    
   
}
