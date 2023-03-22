// В этом методе generate() должен отчётливо проглядываться пайплайн:
//
//        => Читаем файлы и формат
//
//        => Парсим данные
//
//        => Построение разницы
//
//        => Возвращаем форматированные данные
//
//        Метод generate принимает две строки - пути к файлам.
//
package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;


public class Differ {
    public static String compareJsonFiles(Path file1Path, Path file2Path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode file1 = mapper.readTree(Files.newInputStream(file1Path));
        JsonNode file2 = mapper.readTree(Files.newInputStream(file2Path));

        StringBuilder diffMessage = new StringBuilder("{\n");
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
        return diffMessage.toString();
    }

    public static void main(String[] args) throws IOException {
        Path file1Path = Paths.get("file1.json");
        Path file2Path = Paths.get("file2.json");
        System.out.println(compareJsonFiles(file1Path, file2Path));
    }
}