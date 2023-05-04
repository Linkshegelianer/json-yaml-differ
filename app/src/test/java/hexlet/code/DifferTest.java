package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/file1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/file2.json";
    private static final String EXPECTED_JSON_STR_PATH = "src/test/resources/expected/json.txt";
    private static String expectedJsonString;

    @BeforeAll
    static void init() throws IOException {
        Path jsonFilePath = Paths.get(EXPECTED_JSON_STR_PATH).toAbsolutePath().normalize();
        expectedJsonString = Files.readString(jsonFilePath);
    }

//    @ParameterizedTest
    void testDiffJson(String expected) throws IOException {
        List<Map<String,Object>> actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2);
        assertEquals(expected, actual);
    }
}