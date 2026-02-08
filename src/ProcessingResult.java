import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessingResult {
    private Map<String, Object> extractedFields;
    private List<String> missingFields;
    private String recommendedRoute;
    private String reasoning;
    
    public ProcessingResult() {
        this.missingFields = new ArrayList<>();
    }
    
    // Getters and Setters
    public Map<String, Object> getExtractedFields() {
        return extractedFields;
    }
    
    public void setExtractedFields(Map<String, Object> fields) {
        this.extractedFields = fields;
    }
    
    public List<String> getMissingFields() {
        return missingFields;
    }
    
    public void setMissingFields(List<String> fields) {
        this.missingFields = fields;
    }
    
    public void addMissingField(String field) {
        this.missingFields.add(field);
    }
    
    public String getRecommendedRoute() {
        return recommendedRoute;
    }
    
    public void setRecommendedRoute(String route) {
        this.recommendedRoute = route;
    }
    
    public String getReasoning() {
        return reasoning;
    }
    
    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }
}