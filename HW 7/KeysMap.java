import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class KeysMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("0", "1");
        map.put("1", "2");
        map.put("2", "3");
        System.out.println(map.entrySet().stream()
                .map((entryMap) -> entryMap.getKey())
                .collect(Collectors.joining(",", "{", "}")));

        /*Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 2);
        map.put(2, 3);
        Set<Entry<Integer, Integer>> set = map.entrySet();
        Integer keys = set.stream()
                .map((entryMap) -> entryMap.getKey())
                .collect(Collectors.joining(","));
        System.out.println(keys);*/
        /*Set<Entry<Integer, Integer>> set = map.entrySet();
        System.out.println(set.stream()
                .map(Entry::getKey))
                .collect(Collectors.joining(",", "{", "}"));*/

        //System.out.println(keys);

    }
}
