package edu.jsu.mcis.tas_fa20;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Shift {
    
    private int id, interval, graceperiod, dock, lunchdeduct;
    private String description;
    
    private LocalTime shiftstart, shiftstop, lunchstart, lunchstop;
    
    private int shiftduration, lunchduration;

    public Shift(int id, int interval, int graceperiod, int dock, int lunchdeduct, String description, LocalTime shiftstart, LocalTime shiftstop, LocalTime lunchstart, LocalTime lunchstop) {
        
        this.id = id;
        this.interval = interval;
        this.graceperiod = graceperiod;
        this.dock = dock;
        this.lunchdeduct = lunchdeduct;
        this.description = description;
        this.shiftstart = shiftstart;
        this.shiftstop = shiftstop;
        this.lunchstart = lunchstart;
        this.lunchstop = lunchstop;
        
        this.shiftduration = (int)(ChronoUnit.MINUTES.between(shiftstart, shiftstop));
        this.lunchduration = (int)(ChronoUnit.MINUTES.between(lunchstart, lunchstop));
        
    }

    public int getId() {
        return id;
    }

    public int getInterval() {
        return interval;
    }

    public int getGraceperiod() {
        return graceperiod;
    }

    public int getDock() {
        return dock;
    }

    public int getLunchdeduct() {
        return lunchdeduct;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getShiftstart() {
        return shiftstart;
    }

    public LocalTime getShiftstop() {
        return shiftstop;
    }

    public LocalTime getLunchstart() {
        return lunchstart;
    }

    public LocalTime getLunchstop() {
        return lunchstop;
    }
    
    public int getShiftStartHour() {
        return (shiftstart.getHour());
    }
    
    public int getShiftStartMinute() {
        return (shiftstart.getMinute());
    }
    
    public int getShiftStopHour() {
        return (shiftstop.getHour());
    }
    
    public int getShiftStopMinute() {
        return (shiftstop.getMinute());
    }
    
    public int getLunchStartHour() {
        return (lunchstart.getHour());
    }
    
    public int getLunchStartMinute() {
        return (lunchstart.getMinute());
    }
    
    public int getLunchStopHour() {
        return (lunchstop.getHour());
    }
    
    public int getLunchStopMinute() {
        return (lunchstop.getMinute());
    }

    public int getShiftduration() {
        return shiftduration;
    }

    public int getLunchduration() {
        return lunchduration;
    }
    
    public String toString() {
        
        // "Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)"
        
        StringBuilder s = new StringBuilder();
        
        String fShiftStart = String.format("%02d:%02d", shiftstart.getHour(), shiftstart.getMinute());
        String fShiftStop = String.format("%02d:%02d", shiftstop.getHour(), shiftstop.getMinute());
        String fLunchStart = String.format("%02d:%02d", lunchstart.getHour(), lunchstart.getMinute());
        String fLunchStop = String.format("%02d:%02d", lunchstop.getHour(), lunchstop.getMinute());
        
        s.append(description).append(": ");
        s.append(fShiftStart).append(" - ").append(fShiftStop).append(" ");
        s.append("(").append(shiftduration).append(" minutes); Lunch");
        
        s.append(": ");
        s.append(fLunchStart).append(" - ").append(fLunchStop).append(" ");
        s.append("(").append(lunchduration).append(" minutes)");
        
        return s.toString();
        
    }

}