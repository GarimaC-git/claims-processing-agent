import java.util.List;

public class RoutingEngine {
    
    /**
     * Determines routing based on claim data and missing fields
     * Returns ProcessingResult with route and reasoning
     */
    public ProcessingResult determineRoute(ClaimData claimData, List<String> missingFields) {
        ProcessingResult result = new ProcessingResult();
        result.setExtractedFields(claimData.toMap());
        result.setMissingFields(missingFields);
        
        String route = "";
        String reasoning = "";
        
        // Rule 1: Check for missing mandatory fields (highest priority)
        if (!missingFields.isEmpty()) {
            route = "Manual Review";
            reasoning = "Missing mandatory fields: " + String.join(", ", missingFields);
        }
        // Rule 2: Check for fraud indicators
        else if (containsFraudIndicators(claimData.getIncidentDescription())) {
            route = "Investigation Flag";
            reasoning = "Description contains potential fraud indicators (fraud/inconsistent/staged)";
        }
        // Rule 3: Check if claim type is injury
        else if ("injury".equalsIgnoreCase(claimData.getClaimType())) {
            route = "Specialist Queue";
            reasoning = "Claim type is injury - requires specialist review";
        }
        // Rule 4: Check damage amount for fast-track
        else if (claimData.getEstimatedDamage() != null && claimData.getEstimatedDamage() < 25000) {
            route = "Fast-track";
            reasoning = "Estimated damage ₹" + claimData.getEstimatedDamage() + " is below ₹25,000 threshold";
        }
        // Default: Manual review
        else {
            route = "Manual Review";
            reasoning = "Estimated damage ₹" + claimData.getEstimatedDamage() + " exceeds fast-track threshold";
        }
        
        result.setRecommendedRoute(route);
        result.setReasoning(reasoning);
        
        return result;
    }
    
    /**
     * Checks if description contains fraud-related keywords
     */
    private boolean containsFraudIndicators(String description) {
        if (description == null) {
            return false;
        }
        
        String lowerDesc = description.toLowerCase();
        return lowerDesc.contains("fraud") || 
               lowerDesc.contains("inconsistent") || 
               lowerDesc.contains("staged");
    }
}
