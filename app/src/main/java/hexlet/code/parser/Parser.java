package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface Parser {

    Map<String, Object> parse(String string) throws JsonProcessingException;

}
