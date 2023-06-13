package project.code.formatter;

import project.code.ParamStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public final class PlainFormatter implements Formatter {

    private static final String NEW_LINE = System.lineSeparator();

    @Override
    public String format(List<Map<String, Object>> mapList) {
        StringJoiner sj = new StringJoiner(NEW_LINE); // use new line as a delimiter
        for (Map<String, Object> map : mapList) {
            Object key = map.get("key");
            String status = (String) map.get("status");
            ParamStatus paramStatus = ParamStatus.getByStatus(status);
            switch (paramStatus) {
                case ADDED -> {
                    Object value = map.get("value");
                    sj.add("Property '%s' was added with value: %s".formatted(key, objectToString(value)));
                }
                case UPDATED -> {
                    Object oldValue = map.get("oldValue");
                    Object newValue = map.get("newValue");
                    sj.add("Property '%s' was updated. From %s to %s".formatted(key, objectToString(oldValue),
                            objectToString(newValue)));
                }
                case DELETED -> sj.add("Property '%s' was removed".formatted(key));
                case UNMODIFIED -> { } // unmodified objects aren't shown in this format
                default -> throw new UnsupportedOperationException("Invalid parameter status: '%s'!".formatted(status));
            }
        }
        return sj.toString();
    }

    private static String objectToString(Object object) {
        if (object instanceof String) {
            String stringObject = (String) object; // cast the object to a string
            String quotedString = "'%s'".formatted(stringObject); // add single quotes around the string
            return quotedString;
        } else if ((object instanceof ArrayList<?> || object instanceof LinkedHashMap<?, ?>)) {
            return "[complex value]";
        }
        return String.valueOf(object);
    }
}
