package hexlet.code;

import java.io.IOException; // exception is thrown when an input/output operation fails
import java.util.List;
import java.util.Map;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {

//    private static final String DEFAULT_FORMAT = "stylish";

    public static List<Map<String,Object>> generate(String filePath1, String filePath2) throws IOException, UnsupportedOperationException {
        // Extract the extension (the part of the file name after the last dot) and return it as String
        String extension1 = FileReader.getFileExtension(filePath1); //
        String extension2 = FileReader.getFileExtension(filePath2);
        if (!extension2.equals(extension1)) {
            throw new UnsupportedOperationException("Provided files have different extensions");
        }

        ObjectMapper mapper = new ObjectMapper();
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        Map<String, Object> map1 = mapper.readValue(file1, Map.class);
        Map<String, Object> map2 = mapper.readValue(file2, Map.class);


        List<Map<String, Object>> diffList = DiffBuilder.build(map1, map2);
//        Formatter formatter = FormatterFactory.getFormatter(format);
        return diffList;
    }

//    public static String generate(String filePath1, String filePath2) throws IOException, UnsupportedOperationException {
//        return generate(filePath1, filePath2, DEFAULT_FORMAT);
//    }
}