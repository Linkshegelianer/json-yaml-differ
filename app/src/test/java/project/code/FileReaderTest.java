package project.code;

import project.code.parser.Parser;
import project.code.parser.ParserFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderTest {

    private static final String EXISTING_YAML_PATH = "src/test/resources/fixtures/file1.yml";
    private static final String NON_EXISTENT_PATH = "src/test/resources/fixtures/file3.yml";

    @Test
    void testNonExistentPath() {
        assertThrows(RuntimeException.class, () ->
                FileReader.read(NON_EXISTENT_PATH));
    }

    @Test
    public void testGetFileExtension() {
        assertEquals("txt", FileReader.getFileExtension("file.txt"));
        assertEquals("docx", FileReader.getFileExtension("document.docx"));
    }


    @Test
    void testGetFileData() throws IOException {
        String content = FileReader.read(EXISTING_YAML_PATH);
        Parser parser = ParserFactory.getParser("yml");
        Map<String, Object> result = parser.parse(content);

        assertNotNull(result);
        assertTrue(result.containsKey("setting1"));
        assertTrue(result.containsKey("key1"));
    }
}

