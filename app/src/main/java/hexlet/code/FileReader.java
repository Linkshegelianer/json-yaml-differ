package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // create Path objects
import java.nio.file.Paths; // work with Path objects

public class FileReader {

    public static String read(String pathString) throws RuntimeException, IOException {
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
        int index = fileName.lastIndexOf('.'); // find last occurence of the . and return -1 if not found
        if (index > 0) {
            extension = fileName.substring(index + 1); // substract string after '.'
        }
        return extension;
    }
}
