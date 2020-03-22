import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> k = new ArrayList<>();
        ArrayList<Integer> v = new ArrayList<>();
        k.add(0);
        k.add(1);
        k.add(2);
        v.add(1);
        v.add(2);
        v.add(3);
        Map<Integer, Integer> map = new Map<>(k, v);
        Map<Integer, Integer> map1 = new Map<>(k, v);
        System.out.println(map.toString());
        System.out.println("size: " + map.size());
        System.out.println("isEmpty: " + map.isEmpty());
        System.out.println("containsValue: " + map.containsValue(2));
        System.out.println("containsKey: " + map.containsKey(5));
        System.out.println("get: " + map.get(2));
        System.out.println("put: " + map.put(2, 11) + " -> " + map.toString());
        System.out.println("putIfAbsent: " + map.putIfAbsent(3, 12) + " - > " + map.toString());
        System.out.println("remove: " + map.remove(2) + " -> " + map.toString());
        System.out.println("keySet: " + map.keySet());
        System.out.println("values: " + map.values());
        System.out.println(map.toString());
        System.out.println("entrySet: " + map.entrySet());
        System.out.println("boolean replace: " + map.replace(3, 12, 15) + " -> " + map.toString());
        System.out.println("V replace: " + map.replace(1, 19));

        System.out.println("1: " + map.toString());
        System.out.println("putIfAbsent: " + map1.putIfAbsent(3, 12) + " - > " + map1.toString());
        //System.out.println("remove: " + map1.remove(2) + " -> " + map1.toString());
        System.out.println("2: " + map1.toString());
        System.out.println("equals: " + map.equals(map1));
        System.out.println("map1.entrySet: " + map1.entrySet());

        map.clear();
        System.out.println("clear: " + " - > " + map.toString());
    }
}
