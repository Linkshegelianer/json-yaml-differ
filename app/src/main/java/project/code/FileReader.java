package project.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

    public static String read(String pathString) throws IOException {
        Path path = Paths.get(pathString).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new RuntimeException("The file '%s' does not exist!".formatted(path));
        }

        String content = "";
        content = Files.readString(path);
        return content;
    }

    public static String getFileExtension(String fileName) {
        String extension = "";
        if (fileName.indexOf(".") > 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return extension;
    }
}
