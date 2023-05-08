package hexlet.code.formatter;

import hexlet.code.ParamStatus;

import java.util.List;
import java.util.Map;

public final class StylishFormatter implements Formatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String PLUS_PREFIX = "  + ";
    private static final String MINUS_PREFIX = "  - ";
    private static final String NEUTRAL_PREFIX = "    ";
    private static final String SPLITTER = ": ";

    @Override
    public String format(List<Map<String, Object>> mapList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append(NEW_LINE);
        mapList.forEach(map -> stringBuilder.append(buildStylishLine(map)));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String buildStylishLine(Map<String, Object> map) throws UnsupportedOperationException {
        StringBuilder sb = new StringBuilder();
        Object key = map.get("key");
        String keyStatus = (String) map.get("status");
        ParamStatus paramStatus = ParamStatus.getByName(keyStatus);
        switch (paramStatus) {
            case ADDED -> {
                Object value = map.get("value");
                sb.append(PLUS_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
            }
            case DELETED -> {
                Object value = map.get("value");
                sb.append(MINUS_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
            }
            case UPDATED -> {
                Object oldValue = map.get("oldValue");
                Object newValue = map.get("newValue");
                sb.append(MINUS_PREFIX).append(key).append(SPLITTER).append(oldValue).append(NEW_LINE);
                sb.append(PLUS_PREFIX).append(key).append(SPLITTER).append(newValue).append(NEW_LINE);
            }
            case UNMODIFIED -> {
                Object value = map.get("value");
                sb.append(NEUTRAL_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
            }
            default -> throw new UnsupportedOperationException("Stylish format: invalid parameter status!");
        }
        return sb.toString();
    }
}
