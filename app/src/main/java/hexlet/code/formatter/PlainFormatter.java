package hexlet.code.formatter;

import hexlet.code.ParamStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public final class PlainFormatter implements Formatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String COMPLEX_VALUE_STRING = "[complex value]";

    @Override
    public String format(List<Map<String, Object>> mapList) {
        StringJoiner stringJoiner = new StringJoiner(NEW_LINE);
        for (Map<String, Object> map : mapList) {
            Object key = map.get("key");
            String keyStatus = (String) map.get("status");
            ParamStatus paramStatus = ParamStatus.getByName(keyStatus);
            switch (paramStatus) {
                case ADDED -> {
                    Object value = map.get("value");
                    stringJoiner.add("Property '%s' was added with value: %s".formatted(key, stringify(value)));
                }
                case UPDATED -> {
                    Object oldValue = map.get("oldValue");
                    Object newValue = map.get("newValue");
                    stringJoiner.add("Property '%s' was updated. From %s to %s".formatted(key, stringify(oldValue),
                            stringify(newValue)));
                }
                case DELETED -> stringJoiner.add("Property '%s' was removed".formatted(key));
                case UNMODIFIED -> { }
                default -> throw new UnsupportedOperationException("Plain format: invalid parameter status!");
            }
        }
        return stringJoiner.toString();
    }

    private static Object stringify(Object object) {
        if (object instanceof String) {
            return "'%s'".formatted(object);
        }
        if (object instanceof ArrayList<?> || object instanceof LinkedHashMap<?, ?>) {
            return COMPLEX_VALUE_STRING;
        }
        return object;
    }
}
