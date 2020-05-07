import java.util.*;

public class MyCollection <T> {
    private T[] data;
    private int size;
    private int maxsize = 10;

    public MyCollection() {
        this.data = (T[]) new Object[this.maxsize];
        this.size = 0;
    }

    public MyCollection(Collection<? extends T> col) {
        this.size = col.size();
        this.data = (T[]) new Object[this.size];
        int i = 0;
        Iterator<T> it = (Iterator<T>) col.iterator();
        while (it.hasNext()) {
            this.data[i] = it.next();
            i++;
        }
    }

    private int size() {
        return this.size;
    }

    public Iterator<T> iterator() {
        return new StrangeArrayIterator();
    }

    private class StrangeArrayIterator <T> implements Iterator<T> {

        protected int cursor;

        public boolean hasNext() {
            if (!(data.length > cursor)) {
                return false;
            }
            for (int i = cursor; i < data.length; i = i + 2) {
                if (data[i] != null) {
                    return true;
                }
            }
            return false;
        }

        public T next() {
            try {
                T el = (T) data[cursor];
                cursor = cursor + 2;
                while (el == null) {
                    el = (T) data[cursor];
                    cursor = cursor + 2;
                }
                return el;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCollection<T> that = (MyCollection<T>) o;
        if (size != that.size) return false;
        boolean[] value = new boolean[that.data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < that.data.length; j++) {
                if ((data[i].equals(that.data[j])) && !(value[j])) {
                    value[j] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < value.length; i++) {
            if (!(value[i]) && i == value.length - 1 ) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, maxsize);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "MyCollection{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", maxsize=" + maxsize +
                '}';
    }
}


