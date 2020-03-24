import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KeysMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 2);
        map.put(2, 3);
        System.out.println(map.entrySet().stream()
                .map((entryMap) -> String.valueOf(entryMap.getKey()))
                .collect(Collectors.joining(",", "{", "}")));
    }
}
