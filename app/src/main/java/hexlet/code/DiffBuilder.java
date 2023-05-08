package hexlet.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DiffBuilder {

    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        keys.stream()
                .sorted()
                .forEachOrdered(key -> diffList.add(getKeyStatusMap(key, map1, map2)));
        return diffList;
    }

    private static Map<String, Object> getKeyStatusMap(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (map1.containsKey(key) && map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);
            if (Objects.equals(oldValue, newValue)) {
                resultMap.put("key", key);
                resultMap.put("status", ParamStatus.UNMODIFIED.getName());
                resultMap.put("value", oldValue);
            } else {
                resultMap.put("key", key);
                resultMap.put("status", ParamStatus.UPDATED.getName());
                resultMap.put("oldValue", oldValue);
                resultMap.put("newValue", newValue);
            }
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            resultMap.put("key", key);
            resultMap.put("status", ParamStatus.DELETED.getName());
            resultMap.put("value", oldValue);
        } else if (!map1.containsKey(key) && map2.containsKey(key)) {
            Object newValue = map2.get(key);
            resultMap.put("key", key);
            resultMap.put("status", ParamStatus.ADDED.getName());
            resultMap.put("value", newValue);
        }
        return resultMap;
    }
}
