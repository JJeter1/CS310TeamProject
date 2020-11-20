package edu.jsu.mcis.tas_fa20;

public class Badge {
    
    private String id, description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        
        // "#12565C60 (Chapman, Joshua E)"
        
        StringBuilder s = new StringBuilder();
        
        s.append("#").append(id).append(" ");
        s.append("(").append(description).append(")");
        
        return s.toString();
        
    }
    
}
