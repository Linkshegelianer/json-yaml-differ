package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/file1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/file2.json";

    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/file1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/file2.yml";
    private static final String EXPECTED_STYLISH_PATH = "src/test/resources/expected/expectedStylish.txt";

    private static final String EXPECTED_PLAIN_PATH = "src/test/resources/expected/expectedPlain.txt";

    private static final String EXPECTED_JSON_PATH = "src/test/resources/expected/expectedJson.json";

    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";

    private static final String JSON_FORMAT = "json";

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;


    @BeforeAll
    static void init() throws IOException {
        Path stylishFilePath = Paths.get(EXPECTED_STYLISH_PATH).toAbsolutePath().normalize();
        Path plainFilePath = Paths.get(EXPECTED_PLAIN_PATH).toAbsolutePath().normalize();
        Path jsonFilePath = Paths.get(EXPECTED_JSON_PATH).toAbsolutePath().normalize();
        expectedStylish = Files.readString(stylishFilePath);
        expectedPlain = Files.readString(plainFilePath);
        expectedJson = Files.readString(jsonFilePath);
    }

    @ParameterizedTest
    @MethodSource("argsStyles")
    void testJsonInput(String expected, String format) throws IOException {
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("argsStyles")
    void testYamlInput(String expected, String format) throws IOException {
        String actual = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, format);
        assertEquals(expected, actual);
    }


    private static Stream<Arguments> argsStyles() {
        return Stream.of(
                arguments(expectedStylish, STYLISH_FORMAT),
                arguments(expectedPlain, PLAIN_FORMAT),
                arguments(expectedJson, JSON_FORMAT)
        );
    }

}