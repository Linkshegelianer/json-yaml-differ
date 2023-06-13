package project.code.formatter;

import project.code.ParamStatus;

import java.util.List;
import java.util.Map;

public final class StylishFormatter implements Formatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String ADDED_PREFIX = "  + ";
    private static final String DELETED_PREFIX = "  - ";
    private static final String UNMODIFIED_PREFIX = "    ";
    private static final String SEPARATOR = ": ";

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
        String status = (String) map.get("status");
        ParamStatus paramStatus = ParamStatus.getByStatus(status);
        switch (paramStatus) {
            case ADDED -> {
                Object value = map.get("value");
                sb.append(ADDED_PREFIX).append(key).append(SEPARATOR).append(value).append(NEW_LINE);
            }
            case DELETED -> {
                Object value = map.get("value");
                sb.append(DELETED_PREFIX).append(key).append(SEPARATOR).append(value).append(NEW_LINE);
            }
            case UPDATED -> {
                Object oldValue = map.get("oldValue");
                Object newValue = map.get("newValue");
                sb.append(DELETED_PREFIX).append(key).append(SEPARATOR).append(oldValue).append(NEW_LINE);
                sb.append(ADDED_PREFIX).append(key).append(SEPARATOR).append(newValue).append(NEW_LINE);
            }
            case UNMODIFIED -> {
                Object value = map.get("value");
                sb.append(UNMODIFIED_PREFIX).append(key).append(SEPARATOR).append(value).append(NEW_LINE);
            }
            default -> throw new UnsupportedOperationException("Invalid parameter status: '%s'!".formatted(status));
        }
        return sb.toString();
    }
}
