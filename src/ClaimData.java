import java.util.Map;
import java.util.HashMap;

public class ClaimData {
    // Policy Information
    private String policyNumber;
    private String policyholderName;
    private String effectiveDate;
    
    // Incident Information
    private String incidentDate;
    private String incidentTime;
    private String incidentLocation;
    private String incidentDescription;
    
    // Claimant Information
    private String claimantName;
    private String claimantContact;
    
    // Asset Information
    private String assetType;
    private String assetId;
    private Double estimatedDamage;
    
    // Other Fields
    private String claimType;
    
    public ClaimData() {
    }
    
    // Getters and Setters
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { 
        this.policyNumber = policyNumber; 
    }
    
    public String getPolicyholderName() { return policyholderName; }
    public void setPolicyholderName(String name) {
         this.policyholderName = name; 
        }
    
    public String getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(String date) { 
        this.effectiveDate = date;
     }
    
    public String getIncidentDate() { return incidentDate; }
    public void setIncidentDate(String date) { 
        this.incidentDate = date; 
    }
    
    public String getIncidentTime() { return incidentTime; }
    public void setIncidentTime(String time) { 
        this.incidentTime = time; 
    }
    
    public String getIncidentLocation() {return incidentLocation; }
    public void setIncidentLocation(String location) { 
        this.incidentLocation = location;
    }
    
    public String getIncidentDescription() { return incidentDescription; }
    public void setIncidentDescription(String desc) {
         this.incidentDescription = desc; 
        }
    
    public String getClaimantName() { return claimantName; }
    public void setClaimantName(String name) { 
        this.claimantName = name; 
    }
    
    public String getClaimantContact() { return claimantContact; }
    public void setClaimantContact(String contact) { 
        this.claimantContact = contact; 
    }
    
    public String getAssetType() { return assetType; }
    public void setAssetType(String type) { 
        this.assetType = type; 
    }
    
    public String getAssetId() { return assetId; }
    public void setAssetId(String id) { 
        this.assetId = id; 
    }
    
    public Double getEstimatedDamage() { return estimatedDamage; }
    public void setEstimatedDamage(Double damage) { 
        this.estimatedDamage = damage; 
    }
    
    public String getClaimType() { return claimType; }
    public void setClaimType(String type) { 
        this.claimType = type; 
    }
    
    // Convert to Map for JSON output
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (policyNumber != null) map.put("policyNumber", policyNumber);
        if (policyholderName != null) map.put("policyholderName", policyholderName);
        if (effectiveDate != null) map.put("effectiveDate", effectiveDate);
        if (incidentDate != null) map.put("incidentDate", incidentDate);
        if (incidentTime != null) map.put("incidentTime", incidentTime);
        if (incidentLocation != null) map.put("incidentLocation", incidentLocation);
        if (incidentDescription != null) map.put("incidentDescription", incidentDescription);
        if (claimantName != null) map.put("claimantName", claimantName);
        if (claimantContact != null) map.put("claimantContact", claimantContact);
        if (assetType != null) map.put("assetType", assetType);
        if (assetId != null) map.put("assetId", assetId);
        if (estimatedDamage != null) map.put("estimatedDamage", estimatedDamage);
        if (claimType != null) map.put("claimType", claimType);
        return map;
    }
}