import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentReader {
    
    /**
     * Reads a document (PDF or TXT) and returns its text content
     * @param filePath Path to the document file
     * @return Extracted text from the document
     */
    public String readDocument(String filePath) throws IOException {
        File file = new File(filePath);
        
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }
        
        // Check file extension
        if (filePath.toLowerCase().endsWith(".pdf")) {
            return readPDF(filePath);
        } else if (filePath.toLowerCase().endsWith(".txt")) {
            return readTXT(filePath);
        } else {
            throw new IOException("Unsupported file format. Only PDF and TXT are supported.");
        }
    }
    
    //Reads PDF file and extracts text
    private String readPDF(String filePath) throws IOException {
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(filePath));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            return text;
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }
    
    //Reads TXT file
    private String readTXT(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}