package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/file1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/file2.json";

    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/file1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/file1.yml";
    private static final String EXPECTED_JSON_STR_PATH = "src/test/resources/expected/expectedJson.txt";
    private static final String EXPECTED_YAML_STR_PATH = "src/test/resources/expected/expected.yml";
    private static String expectedJsonString;
    private static final String STYLISH_FORMAT = "stylish";


    @Test
    void testDiffJson() throws IOException {
        Path jsonFilePath = Paths.get(EXPECTED_JSON_STR_PATH).toAbsolutePath().normalize();
        String expected = Files.readString(jsonFilePath);
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, STYLISH_FORMAT);
        assertEquals(expected, actual);
    }

    @Test
    void testDiffJsonWrite() throws IOException {
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, STYLISH_FORMAT);
        String pathExpectedWrite = "src/test/resources/expected/expectedWrite.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathExpectedWrite));
        writer.write(actual);
        writer.close();
    }



//    @Test
//    void testDiffJson() throws IOException, JsonProcessingException {
//        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, jsonFormat);
////        System.out.println("actual: " + actual);
//        ObjectMapper mapper = new ObjectMapper();
//        String actualJsonString = mapper.writeValueAsString(actual);
////        System.out.println("actualJsonString: " + actualJsonString);
//        assertEquals(expectedJsonString, actualJsonString);
//    }
}