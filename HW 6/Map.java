import java.util.*;
import java.util.Set;

public class Map<K, V> extends AbstractMap<K, V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;

    private class MyEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V el = this.getValue();
            this.value = value;
            return el;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;
            return Objects.equals(key, myEntry.key) &&
                    Objects.equals(value, myEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "myEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public Map() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public Map(ArrayList<K> keys, ArrayList<V> values) {
        this();
        for (int i = 0; i < keys.size(); i++) {
            if (!(this.keys.contains(keys.get(i)))) {
                this.keys.add(keys.get(i));
                this.values.add(values.get(i));
            }
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
         for (int i = 0; i < keys.size(); i++) {
            set.add(new MyEntry(keys.get(i), values.get(i)));
        }
        return set;
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        if (values.contains(value)) return true;
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public V get(Object key) {
        if (keys.contains(key)) {
            return values.get(keys.indexOf(key));
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (keys.contains(key)) {
            V val = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), value);
            return val;
        }
        keys.add(key);
        values.add(value);
        return null;
    }

    @Override
    public V remove(Object key) {
        if (keys.contains(key)) {
            V value = values.get(keys.indexOf(key));
            values.remove(keys.indexOf(key));
            keys.remove(key);
            return value;
        }
        return null;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (keys.contains(key)) return values.get(keys.indexOf(key));
        keys.add(key);
        values.add(value);
        return null;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        if (keys.contains(key) && oldValue.equals(values.get(keys.indexOf(key)))) {
            values.set(keys.indexOf(key), newValue);
            return true;
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        if (keys.contains(key)) {
            V val = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), value);
            return val;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        Iterator<K> it = keys.iterator();
        while (it.hasNext()) {
            set.add(it.next());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<>();
        Iterator<V> it = values.iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
        return collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Map<?, ?> map = (Map<?, ?>) o;
        return Objects.equals(keys, map.keys) &&
                Objects.equals(values, map.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), keys, values);
    }

    @Override
    public String toString() {
        return "Map{" +
                "keys=" + keys +
                ", values=" + values +
                '}';
    }
}
