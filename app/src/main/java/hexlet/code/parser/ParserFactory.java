package hexlet.code.parser;

public class ParserFactory {

    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";

    public static Parser getParser(String extension) {
        Parser parser;
        parser = switch (extension) {
            case JSON_EXTENSION -> new JsonParser();
            case YAML_EXTENSION -> new YamlParser();
            default -> throw new RuntimeException("File extension '%s' is not supported!".formatted(extension));
        };
        return parser;
    }
}
