package hexlet.code;

import hexlet.code.parser.Parser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FileReaderTest {

    private static final String EXISTING_YAML_PATH = "src/test/resources/yaml/file1.yml";
    private static final String NON_EXISTENT_PATH = "src/test/resources/yaml/file3.yml";

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
    public void testGetFileData() throws IOException {
        Parser parser = Mockito.mock(Parser.class);
        Mockito.when(parser.parse(Mockito.anyString())).thenReturn(new HashMap<>());
        Map<String, Object> result = FileReader.getFileData(EXISTING_YAML_PATH, "yml");
        assertNotNull(result);
        assertTrue(result.containsKey("setting1"));
        assertTrue(result.containsKey("key1"));
    }
}

