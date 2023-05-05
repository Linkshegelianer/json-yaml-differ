package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> diff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
//        return json;



        mapper.enable(SerializationFeature.INDENT_OUTPUT); // pretty-print the output
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter(); // default implementation for pretty-printing JSON
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        return mapper.writer(prettyPrinter).writeValueAsString(diff);
//        return mapper.writeValueAsString(diff);
    }

}