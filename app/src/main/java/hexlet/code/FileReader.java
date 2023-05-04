package hexlet.code;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

    public static String read(String pathString) throws RuntimeException, IOException {
        Path path = Paths.get(pathString).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new RuntimeException("The file '%s' does not exist!".formatted(path));
        }
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public static String getFileExtension(String fileName) {
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }
        return extension;
    }
}
