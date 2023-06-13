package project.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static final String STR_FIXTURES_PATH = "src/test/resources/fixtures/";
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
    @ValueSource(strings = {"json", "yml"})
    void testExtensions(String format) throws Exception {
        String filePath1 = (STR_FIXTURES_PATH + "file1." + format);
        String filePath2 = (STR_FIXTURES_PATH + "file2." + format);


        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(expectedStylish); // default stylish

        assertThat(Differ.generate(filePath1, filePath2, STYLISH_FORMAT))
                .isEqualTo(expectedStylish);

        assertThat(Differ.generate(filePath1, filePath2, PLAIN_FORMAT))
                .isEqualTo(expectedPlain);

        assertThat(Differ.generate(filePath1, filePath2, JSON_FORMAT))
                .isEqualTo(expectedJson);
    }

    @Test
    void testEqualFiles() {
        assertThrows(UnsupportedOperationException.class, () ->
                Differ.generate((STR_FIXTURES_PATH + "file1." + "yml"),
                        (STR_FIXTURES_PATH + "file1." + "yml"), STYLISH_FORMAT));
    }

    @Test
    void testDifferentExtensions() {
        assertThrows(UnsupportedOperationException.class, () ->
            Differ.generate((STR_FIXTURES_PATH + "file1." + "yml"),
                    (STR_FIXTURES_PATH + "file1." + "json"), STYLISH_FORMAT));
    }

}
