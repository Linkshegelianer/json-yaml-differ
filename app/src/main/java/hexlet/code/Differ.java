package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {

    private static final String DEFAULT_FORMAT = "stylish";

    public static String generate(String pathString1, String pathString2, String format)
            throws IOException, UnsupportedOperationException {
        if (pathString1.equals(pathString2)) {
            throw new UnsupportedOperationException("Unable to match equal files!");
        }
        String extension1 = FileReader.getFileExtension(pathString1);
        String extension2 = FileReader.getFileExtension(pathString2);
        if (!extension1.equals(extension2)) {
            throw new UnsupportedOperationException("Unable to match files with different extensions!");
        }
        Map<String, Object> map1 = FileReader.getFileData(pathString1, extension1);
        Map<String, Object> map2 = FileReader.getFileData(pathString2, extension2);
        List<Map<String, Object>> diffList = DiffBuilder.build(map1, map2);
        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(diffList);
    }

    public static String generate(String pathString1, String pathString2) throws IOException {
        return generate(pathString1, pathString2, DEFAULT_FORMAT);
    }

}
