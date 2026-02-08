import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldExtractor {
    
    /**
     * Extracts all fields from document text and populates ClaimData
     */
    public ClaimData extractFields(String text) {
        ClaimData claimData = new ClaimData();
        
        // Extract Policy Information
        claimData.setPolicyNumber(extractPolicyNumber(text));
        claimData.setPolicyholderName(extractPolicyholderName(text));
        claimData.setEffectiveDate(extractEffectiveDate(text));
        
        // Extract Incident Information
        claimData.setIncidentDate(extractIncidentDate(text));
        claimData.setIncidentTime(extractIncidentTime(text));
        claimData.setIncidentLocation(extractIncidentLocation(text));
        claimData.setIncidentDescription(extractIncidentDescription(text));
        
        // Extract Claimant Information
        claimData.setClaimantName(extractClaimantName(text));
        claimData.setClaimantContact(extractClaimantContact(text));
        
        // Extract Asset Information
        claimData.setAssetType(extractAssetType(text));
        claimData.setAssetId(extractAssetId(text));
        claimData.setEstimatedDamage(extractEstimatedDamage(text));
        
        // Extract Other Fields
        claimData.setClaimType(extractClaimType(text));
        
        return claimData;
    }
    
    // Extract Policy Number
    private String extractPolicyNumber(String text) {
        // Look for patterns like "POLICY NUMBER: ABC123" or "Policy #: 12345"
        Pattern pattern = Pattern.compile("POLICY\\s*NUMBER[:\\s]+(\\S+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Policyholder Name
    private String extractPolicyholderName(String text) {
        // Look for "NAME OF INSURED" followed by name (stop at newline)
        Pattern pattern = Pattern.compile("NAME\\s+OF\\s+INSURED[:\\s]+([A-Za-z\\s]+?)(?=\\r?\\n|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Effective Date
    private String extractEffectiveDate(String text) {
        // Look for date patterns (MM/DD/YYYY or DD-MM-YYYY)
        Pattern pattern = Pattern.compile("EFFECTIVE\\s+DATE[:\\s]+(\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Incident Date
    private String extractIncidentDate(String text) {
        Pattern pattern = Pattern.compile("DATE\\s+OF\\s+LOSS[:\\s]+(\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4})", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Incident Time
    private String extractIncidentTime(String text) {
        Pattern pattern = Pattern.compile("TIME[:\\s]+(\\d{1,2}:\\d{2}\\s*(?:AM|PM)?)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Incident Location
    private String extractIncidentLocation(String text) {
        // Look for location after keywords like "LOCATION:" or "STREET:"
        Pattern pattern = Pattern.compile("(?:LOCATION|STREET)[:\\s]+([^\\n]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Incident Description
    private String extractIncidentDescription(String text) {
        Pattern pattern = Pattern.compile("DESCRIPTION\\s+OF\\s+(?:ACCIDENT|LOSS)[:\\s]+([^\\n]+(?:\\n(?!\\s*[A-Z]+:)[^\\n]+)*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Claimant Name
    private String extractClaimantName(String text) {
        // Look for "CLAIMANT" followed by name (stop at newline)
        Pattern pattern = Pattern.compile("CLAIMANT[:\\s]+([A-Za-z\\s]+?)(?=\\r?\\n|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Contact Information
    private String extractClaimantContact(String text) {
        // Look for phone or email
        Pattern phonePattern = Pattern.compile("(\\d{3}[-\\s]?\\d{3}[-\\s]?\\d{4})");
        Matcher phoneMatcher = phonePattern.matcher(text);
        if (phoneMatcher.find()) {
            return phoneMatcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Asset Type
    private String extractAssetType(String text) {
        // For automobile forms, usually "Vehicle" or extracted from form type
        if (text.toLowerCase().contains("automobile") || text.toLowerCase().contains("vehicle")) {
            return "Vehicle";
        }
        return "Unknown";
    }
    
    // Extract Asset ID (like VIN or plate number)
    private String extractAssetId(String text) {
        Pattern pattern = Pattern.compile("(?:VIN|VEHICLE\\s+ID)[:\\s]+(\\S+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    // Extract Estimated Damage (in rupees)
    private Double extractEstimatedDamage(String text) {
        // Look for currency amounts (₹ or Rs.)
        Pattern pattern = Pattern.compile("(?:ESTIMATE|DAMAGE)[:\\s]*(?:₹|Rs\\.?|INR)?\\s*([0-9,]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String amount = matcher.group(1).replaceAll(",", "");
            try {
                return Double.parseDouble(amount);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
    
    // Extract Claim Type
    private String extractClaimType(String text) {
        if (text.toLowerCase().contains("injury") || text.toLowerCase().contains("injured")) {
            return "injury";
        } else if (text.toLowerCase().contains("collision")) {
            return "collision";
        } else if (text.toLowerCase().contains("theft")) {
            return "theft";
        }
        return "property";
    }
}