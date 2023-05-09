package hexlet.code;

import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FileReader {

    public static String read(String pathString) throws RuntimeException {
        Path path = Paths.get(pathString).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new RuntimeException("The file '%s' does not exist!".formatted(path));
        }

        String content = "";
        try {
            content = Files.readString(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static String getFileExtension(String fileName) {
        String extension = "";
        if (fileName.indexOf(".") > 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return extension;
    }

    public static Map<String, Object> getFileData(String pathString, String extension) throws IOException {
        String content = FileReader.read(pathString);
        Parser parser = ParserFactory.getParser(extension);
        return parser.parse(content);
    }
}
