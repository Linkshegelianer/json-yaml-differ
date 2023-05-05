package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.parser.JsonParser;
import hexlet.code.parser.Parser;

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

        Map<String, Object> map1 = getFileData(filePath1);
        Map<String, Object> map2 = getFileData(filePath2);

        List<Map<String, Object>> diffList = DiffBuilder.build(map1, map2);
        Formatter formatter = new JsonFormatter();
        return formatter.format(diffList);
    }

    private static Map<String, Object> getFileData(String pathString) throws IOException {
        String content = FileReader.read(pathString);
        Parser parser = new JsonParser();
        return parser.parse(content);
    }
}