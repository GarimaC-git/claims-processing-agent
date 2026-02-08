import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClaimsProcessor {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java ClaimsProcessor <path-to-document>");
            System.out.println("Example: java ClaimsProcessor sample-documents/claim1.pdf");
            return;
        }
        
        String filePath = args[0];
        
        try {
            // Step 1: Read the document
            DocumentReader reader = new DocumentReader();
            String documentText = reader.readDocument(filePath);
            System.out.println("Document read successfully!");
            
            // Step 2: Extract fields
            FieldExtractor extractor = new FieldExtractor();
            ClaimData claimData = extractor.extractFields(documentText);
            System.out.println("Fields extracted successfully!");
            
            // Step 3: Identify missing mandatory fields
            List<String> missingFields = identifyMissingFields(claimData);
            
            // Step 4: Determine routing
            RoutingEngine router = new RoutingEngine();
            ProcessingResult result = router.determineRoute(claimData, missingFields);
            
            // Step 5: Output as JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonOutput = mapper.writerWithDefaultPrettyPrinter()
                                      .writeValueAsString(result);
            
            System.out.println("\n=== PROCESSING RESULT ===");
            System.out.println(jsonOutput);
            
        } catch (Exception e) {
            System.err.println("Error processing claim: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Checks which mandatory fields are missing
     */
    private static List<String> identifyMissingFields(ClaimData claimData) {
        List<String> missing = new ArrayList<>();
        
        // Check mandatory fields
        if (claimData.getPolicyNumber() == null || claimData.getPolicyNumber().isEmpty()) {
            missing.add("policyNumber");
        }
        if (claimData.getPolicyholderName() == null || claimData.getPolicyholderName().isEmpty()) {
            missing.add("policyholderName");
        }
        if (claimData.getIncidentDate() == null || claimData.getIncidentDate().isEmpty()) {
            missing.add("incidentDate");
        }
        if (claimData.getIncidentLocation() == null || claimData.getIncidentLocation().isEmpty()) {
            missing.add("incidentLocation");
        }
        if (claimData.getEstimatedDamage() == null) {
            missing.add("estimatedDamage");
        }
        
        return missing;
    }
}