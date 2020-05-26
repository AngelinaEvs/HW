import java.util.Arrays;
import java.util.Objects;

public class Set<T> {
    private T[] set;
    private int size;

    public Set() {
        size = 0;
        set = (T[]) new Object[1];
    }

    public Set(T[] array) {
        set = (T[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
    }

    public Set(Set<T> set) {
        this.set = set.set;
        this.size = set.size;
    }

    public boolean isHas(T el) {
        if (size == 0) return false;
        for (int i = 0; i < size; i++) {
            if (set[i].equals(el)) return true;
        }
        return false;
    }

    public void add(T el) {
        if (!(isHas(el))) {
            if (set.length == size) {
                size++;
                T[] newSet = (T[]) new Object[size * 2];
                System.arraycopy(set, 0, newSet, 0, size - 1);
                newSet[size - 1] = el;
                set = newSet;
            }
            else {
                set[size] = el;
                size++;
            }
        }
    }

    public int size() {
        return size;
    }

    public void delete(T el) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(el)) {
                set[i] = set[size - 1];
                set[size - 1] = null;
                size--;
                break;
            }
        }
    }

    public Set<T> merge(Set<T> o) {
        Set<T> newSet = new Set<>(this);
        System.out.println(this);
        for (int i = 0; i < o.size; i++) {
            newSet.add(o.set[i]);
        }
        return newSet;
    }

    @Override
    public String toString() {
        return Arrays.toString(set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<T> that = (Set<T>) o;
        if (size != that.size) return false;
        boolean f = true;
        for (int i = 0; i < this.size; i++) {
            if (!f) break;
            for (int j = i; j < that.size; j++) {
                if (this.set[i].equals(that.set[j])) {
                    T el = that.set[j];
                    that.set[j] = that.set[i];
                    that.set[i] = el;
                    break;
                }
                if (j == that.size - 1 && !(set[i].equals(that.set[j]))) {
                    f = false;
                    break;
                }
            }
        }
        return f;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(set);
        return result;
    }
}
