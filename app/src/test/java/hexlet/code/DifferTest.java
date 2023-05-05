package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/file1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/file2.json";
    private static final String EXPECTED_JSON_STR_PATH = "src/test/resources/expected/expected.json";
    private static String expectedJsonString;

    @BeforeAll
    static void init() throws IOException {
        // absolute path is a path that starts from the floor of the file system
        Path jsonFilePath = Paths.get(EXPECTED_JSON_STR_PATH).toAbsolutePath().normalize();
        expectedJsonString = Files.readString(jsonFilePath);
    }

    @Test
    void testDiffJson() throws IOException, JsonProcessingException {
        String actual = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2);
        System.out.println("actual: " + actual);
        ObjectMapper mapper = new ObjectMapper();
        String actualJsonString = mapper.writeValueAsString(actual);
        System.out.println("actualJsonString: " + actualJsonString);
        assertEquals(expectedJsonString, actualJsonString);
    }
}