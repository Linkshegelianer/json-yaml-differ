package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Objects;

public class DiffBuilder {

    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> keys = new TreeSet<>(Comparator.naturalOrder());
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> resultMap = new LinkedHashMap<>();
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);

            resultMap.put("key", key);
            if (!map1.containsKey(key)) {
                resultMap.put("status", ParamStatus.ADDED.getStatus());
                resultMap.put("value", newValue);
            } else if (!map2.containsKey(key)) {
                resultMap.put("status", ParamStatus.DELETED.getStatus());
                resultMap.put("value", oldValue);
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                resultMap.put("status", ParamStatus.UNMODIFIED.getStatus());
                resultMap.put("value", oldValue);
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                resultMap.put("status", ParamStatus.UPDATED.getStatus());
                resultMap.put("oldValue", oldValue);
                resultMap.put("newValue", newValue);
            }
            diffList.add(resultMap);
        }
        return diffList;
    }
}
