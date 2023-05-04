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
        // we need to order all keys alphabetically before comparing them
        Set<String> keys = new HashSet<>(); // to store all keys from both maps
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        keys.stream()
                .sorted() // sort the keys alphabetically
                // forEachOrdered ensures that each element is processed in encounter order
                .forEachOrdered(key -> diffList.add(getKeyStatusMap(key, map1, map2)));
        return diffList;
    }

    public static Map<String, Object> getKeyStatusMap(String key, Map<String, Object> map1, Map<String, Object> map2) {
        // use LinkedHashMap to store data since it preserves the order of insertion of its elements
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (map1.containsKey(key) && map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);
            if (Objects.equals(oldValue, newValue)) {
                resultMap.put(key, oldValue);
            } else {
                resultMap.put("- " + key, oldValue);
                resultMap.put("+ " + key, newValue);
            }
        } else if (map1.containsKey(key)) {
            resultMap.put("- " + key, map1.get(key));
        } else if (map2.containsKey(key)) {
            resultMap.put("+ " + key, map2.get(key));
        }
        return resultMap;
    }
}