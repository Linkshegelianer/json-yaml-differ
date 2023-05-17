package hexlet.code.parser;

public class ParserFactory {

    private static final String JSON_FORMAT = "json";
    private static final String YAML_FORMAT = "yaml";

    private static final String YML_FORMAT = "yml";

    public static Parser getParser(String dataFormat) {
        Parser parser;
        parser = switch (dataFormat) {
            case JSON_FORMAT -> new JsonParser();
            case YAML_FORMAT, YML_FORMAT -> new YamlParser();
            default -> throw new RuntimeException("Data format '%s' is not supported!".formatted(dataFormat));
        };
        return parser;
    }
}
