package hexlet.code;

//import java.io.FileReader;
import java.io.IOException; // exception is thrown when an input/output operation fails
import java.nio.file.Files; // is used here to read the contents of the two JSON files
import java.nio.file.Paths; // convert the file paths to a Path object that can be used with Files.readAllBytes()
import com.fasterxml.jackson.databind.JsonNode; // represents a node in a JSON object, used to represent the contents of the two JSON files
import com.fasterxml.jackson.databind.ObjectMapper; //  parse JSON data into JsonNode objects, used to parse the contents of the JSON files
import java.util.Iterator; // iterate over the field names in the JSON files

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException, UnsupportedOperationException {
        // Extract the extension (the part of the file name after the last dot) and return it as String
        String extension1 = FileReader.getFileExtension(filePath1);
        String extension2 = FileReader.getFileExtension(filePath2);
        if (!extension2.equals(extension1)) {
            throw new UnsupportedOperationException("Provided files have different extensions.");
        }

        // Read JSON data from files
        String json1 = new String(Files.readAllBytes(Paths.get(filePath1)));
        String json2 = new String(Files.readAllBytes(Paths.get(filePath2)));

        // Parse JSON data
        ObjectMapper mapper = new ObjectMapper();
        JsonNode file1 = mapper.readTree(json1);
        JsonNode file2 = mapper.readTree(json2);

        // Build difference between JSON files
        StringBuilder diffMessage = new StringBuilder();
        diffMessage.append("{\n");

        Iterator<String> fieldNames = file1.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (!file2.has(fieldName)) {
                diffMessage.append("  - ").append(fieldName).append(": ").append(file1.get(fieldName)).append("\n");
            } else if (!file1.get(fieldName).equals(file2.get(fieldName))) {
                diffMessage.append("  - ").append(fieldName).append(": ").append(file1.get(fieldName)).append("\n");
                diffMessage.append("  + ").append(fieldName).append(": ").append(file2.get(fieldName)).append("\n");
            }
        }

        fieldNames = file2.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (!file1.has(fieldName)) {
                diffMessage.append("  + ").append(fieldName).append(": ").append(file2.get(fieldName)).append("\n");
            }
        }
        diffMessage.append("}");
        return diffMessage.toString(); // return formatted data
    }
}