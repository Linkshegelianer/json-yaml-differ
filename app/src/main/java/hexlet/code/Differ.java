package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException, UnsupportedOperationException {
        String extension1 = FileReader.getFileExtension(filePath1);
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
        Formatter formatter = new JsonFormatter();
        return formatter.format(diffList);
    }

//    public static List<Map<String,Object>> generate(String filePath1, String filePath2) throws IOException, UnsupportedOperationException {
//        String extension1 = FileReader.getFileExtension(filePath1);
//        String extension2 = FileReader.getFileExtension(filePath2);
//        if (!extension2.equals(extension1)) {
//            throw new UnsupportedOperationException("Provided files have different extensions");
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        File file1 = new File(filePath1);
//        File file2 = new File(filePath2);
//
//        Map<String, Object> map1 = mapper.readValue(file1, Map.class);
//        Map<String, Object> map2 = mapper.readValue(file2, Map.class);
//
//        List<Map<String, Object>> diffList = DiffBuilder.build(map1, map2);
//        return diffList;
//    }
}