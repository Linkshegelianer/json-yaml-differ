package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/file1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/file2.json";

    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/file1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/file2.yml";
    private static final String EXPECTED_STYLISH_PATH = "src/test/resources/expected/expectedStylish.txt";

    private static final String EXPECTED_PLAIN_PATH = "src/test/resources/expected/expectedPlain.txt";


    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";

    private static final String JSON_FORMAT = "json";


    @Test // fine
    void testStylishJson() throws IOException {
        Path stylishFilePath = Paths.get(EXPECTED_STYLISH_PATH).toAbsolutePath().normalize();
        String expected = Files.readString(stylishFilePath);
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, STYLISH_FORMAT);
        assertEquals(expected, actual);
    }

    @Test // fine
    void testStylishYaml() throws IOException {
        Path stylishFilePath = Paths.get(EXPECTED_STYLISH_PATH).toAbsolutePath().normalize();
        String expected = Files.readString(stylishFilePath);
        String actual = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, STYLISH_FORMAT);
        assertEquals(expected, actual);
    }

    @Test // fine
    void testPlainJson() throws IOException {
        Path plainFilePath = Paths.get(EXPECTED_PLAIN_PATH).toAbsolutePath().normalize();
        String expected = Files.readString(plainFilePath);
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, PLAIN_FORMAT);
        assertEquals(expected, actual);
    }

    @Test // fine
    void testPlainYaml() throws IOException {
        Path plainFilePath = Paths.get(EXPECTED_PLAIN_PATH).toAbsolutePath().normalize();
        String expected = Files.readString(plainFilePath);
        String actual = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, PLAIN_FORMAT);
        assertEquals(expected, actual);
    }

    @Test // fine
    void testJsontoJson() throws IOException {
        Path plainFilePath = Paths.get(EXPECTED_PLAIN_PATH).toAbsolutePath().normalize();
        String expected = Files.readString(plainFilePath);
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, PLAIN_FORMAT);
        assertEquals(expected, actual);
    }


//    @Test
//    void testPlainWrite() throws IOException {
//        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, PLAIN_FORMAT);
//        String EXPECTED_PLAIN_PATH = "src/test/resources/expected/expectedPlain.txt";
//        BufferedWriter writer = new BufferedWriter(new FileWriter(EXPECTED_PLAIN_PATH));
//        writer.write(actual);
//        writer.close();
//    }


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