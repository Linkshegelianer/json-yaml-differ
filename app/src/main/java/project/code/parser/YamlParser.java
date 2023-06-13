package project.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public final class YamlParser implements Parser {

    @Override
    public Map<String, Object> parse(String string) throws JsonProcessingException {
        ObjectMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(string, new TypeReference<Map<String, Object>>() { });
    }
}
