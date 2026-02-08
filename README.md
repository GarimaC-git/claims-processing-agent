# Insurance Claims Processing Agent

An autonomous agent that processes FNOL (First Notice of Loss) insurance claim documents, extracts key information, and routes claims to appropriate workflows.

## Overview

This Java application reads insurance claim documents (PDF or TXT format), extracts relevant fields, identifies missing information, and automatically routes claims based on predefined business rules.

## Features

- **Document Processing**: Supports both PDF and TXT file formats
- **Field Extraction**: Extracts key claim information including policy details, incident information, and damage estimates
- **Smart Routing**: Automatically routes claims based on business rules:
  - Missing mandatory fields → Manual Review
  - Fraud indicators (keywords: fraud, inconsistent, staged) → Investigation Flag
  - Injury claims → Specialist Queue
  - Low damage (<₹25,000) → Fast-track
  - High damage (≥₹25,000) → Manual Review
- **JSON Output**: Structured output with extracted fields, missing fields, routing decision, and reasoning

## Technology Stack

- **Java 8**: Core programming language
- **Apache PDFBox 2.0.27**: PDF text extraction
- **Jackson 2.13.4**: JSON serialization
- **Maven**: Dependency management and build tool

## Project Structure
```
claims-processing-agent/
├── src/
│   ├── ClaimsProcessor.java       # Main entry point
│   ├── DocumentReader.java        # Reads PDF/TXT files
│   ├── FieldExtractor.java        # Extracts fields using regex
│   ├── RoutingEngine.java         # Applies routing rules
│   ├── ClaimData.java             # Data model for claims
│   └── ProcessingResult.java      # Output model
├── sample-documents/
│   ├── claim1.txt                 # Fast-track scenario
│   └── claim2.txt                 # Manual review scenario
├── pom.xml                        # Maven configuration
└── README.md
```

## How to Run

### Prerequisites
- Java 8 or higher
- Maven 3.x

### Build the Project
```bash
mvn clean package
```

### Run the Application
```bash
java -jar target/claims-processing-agent-1.0.jar sample-documents/claim1.txt
```

Replace `claim1.txt` with any claim document path.

## Sample Output
```json
{
  "extractedFields": {
    "policyNumber": "POL-2024-123456",
    "policyholderName": "Rajesh Kumar",
    "incidentDate": "01/15/2024",
    "incidentLocation": "MG Road, Bangalore",
    "estimatedDamage": 18000.0,
    "claimantName": "Rajesh Kumar",
    "assetType": "Vehicle"
  },
  "missingFields": [],
  "recommendedRoute": "Fast-track",
  "reasoning": "Estimated damage ₹18000.0 is below ₹25,000 threshold"
}
```

## Routing Logic

The system applies routing rules in the following priority order:

1. **Missing Mandatory Fields** → Manual Review
   - Required fields: policyNumber, policyholderName, incidentDate, incidentLocation, estimatedDamage

2. **Fraud Indicators** → Investigation Flag
   - Triggers on keywords: "fraud", "inconsistent", "staged"

3. **Injury Claims** → Specialist Queue
   - When claim type is "injury"

4. **Low Damage** → Fast-track
   - When estimated damage < ₹25,000

5. **High Damage** → Manual Review
   - When estimated damage ≥ ₹25,000

## Development Approach

### Architecture
I designed the system with separation of concerns, breaking the problem into focused components:
- Document reading logic separated from extraction logic
- Business rules isolated in dedicated routing engine
- Clean data models for input and output

### Use of AI Tools
I used AI assistants (Claude/ChatGPT) to accelerate development:
- Generating regex patterns for text field extraction
- Researching Apache PDFBox library usage
- Debugging Maven configuration issues

However, I designed the overall architecture, implemented the routing logic, and made all technical decisions myself. AI tools served as coding assistants, similar to professional development practices.

### Text Extraction Approach
I used regular expressions for pattern matching in unstructured text. While AI tools helped generate the regex patterns, I understand the approach: each pattern searches for specific field labels (e.g., "POLICY NUMBER") and captures the values that follow them, handling variations in formatting and spacing.

## Future Enhancements

- Support for additional document formats (DOCX, scanned PDFs with OCR)
- Machine learning model for fraud detection
- Database integration for claim persistence
- REST API for integration with claim management systems

## Author

Garima

## Assignment Context

This project was developed as part of a Junior Software Developer assessment, demonstrating skills in:
- Problem decomposition and code structure
- Document processing and text extraction
- Business logic implementation
- Use of industry-standard tools and libraries