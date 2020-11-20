package edu.jsu.mcis.tas_fa20;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Punch {
    
    public static final int CLOCK_OUT_PUNCH = 0;
    public static final int CLOCK_IN_PUNCH = 1;
    public static final int TIME_OUT_PUNCH = 2;
    
    private int id, terminalid, punchtypeid;    
    private String badgeid;                    
    private String adjustmenttype;    
    private long originaltimestamp, adjustedtimestamp;
    
    public Punch(int id, int terminalid, String badgeid, long originaltimestamp, int punchtypeid) {
        
        this.id = id;
        this.terminalid = terminalid;
        this.badgeid = badgeid;
        this.punchtypeid = punchtypeid;

        this.originaltimestamp = this.adjustedtimestamp = originaltimestamp;
       
    }
    
    public Punch(String badgeid, int terminalid, int punchtypeid) {
        
        this.badgeid = badgeid;
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.adjustmenttype = "";
        
        GregorianCalendar gc = new GregorianCalendar();
        originaltimestamp = adjustedtimestamp = gc.getTimeInMillis();
        
    }
    
    public String printOriginalTimestamp() {
        
        GregorianCalendar ots = new GregorianCalendar();
        ots.setTimeInMillis(originaltimestamp);
        
        StringBuilder s = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");

        s.append("#").append(badgeid).append(" ");

        switch ( punchtypeid ) {

            case CLOCK_OUT_PUNCH:
                s.append("CLOCKED OUT");
                break;
            case CLOCK_IN_PUNCH:
                s.append("CLOCKED IN");
                break;
            case TIME_OUT_PUNCH:
                s.append("TIMED OUT");
                break;
	}

        s.append(": ").append(format.format(ots.getTime()).toUpperCase());

        return s.toString();

    }

    public String printAdjustedTimestamp() {
        
        GregorianCalendar ots = new GregorianCalendar();
        GregorianCalendar ats = new GregorianCalendar();
        ots.setTimeInMillis(originaltimestamp);
        ats.setTimeInMillis(adjustedtimestamp);
        
        StringBuilder s = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");

        s.append("#").append(badgeid).append(" ");

        switch ( punchtypeid ) {

            case CLOCK_OUT_PUNCH:
                s.append("CLOCKED OUT");
                break;
            case CLOCK_IN_PUNCH:
                s.append("CLOCKED IN");
                break;
            case TIME_OUT_PUNCH:
                s.append("TIMED OUT");
                break;
	}
        
        s.append(": ").append(format.format(ats.getTime()).toUpperCase());
        
        s.append(" (").append(adjustmenttype).append(")");

        return s.toString();

    }

    public int getId() {
        return id;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public String getAdjustmenttype() {
        return adjustmenttype;
    }

    public long getOriginaltimestamp() {
        return originaltimestamp;
    }

    public long getAdjustedtimestamp() {
        return adjustedtimestamp;
    }
    
}
