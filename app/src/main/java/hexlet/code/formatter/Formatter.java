package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Formatter {

    String format(List<Map<String, Object>> diff) throws JsonProcessingException;
}