import java.util.*;

public class ModifCollection <T> {
    private T[] data;
    private int size;
    private int maxsize = 10;
    private boolean flag;

    public ModifCollection() {
        this.data = (T[]) new Object[this.maxsize];
        this.size = 0;
    }

    public ModifCollection(Collection<? extends T> col) {
        this.size = col.size();
        this.data = (T[]) new Object[this.size];
        int i = 0;
        Iterator<T> it = (Iterator<T>) col.iterator();
        while (it.hasNext()) {
            this.data[i] = it.next();
            i++;
        }
    }

    public boolean add(T el) throws NullPointerException {
        if (size != data.length && el != null) {
            data[size++] = el;
            return true;
        }
        if (el == null) {
            throw new NullPointerException();
        }
        return false;
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
            return (data.length > cursor && size != 0);
        }

        public T next() throws NoSuchElementException {
            try {
                T el = (T) data[cursor];
                flag = true;
                cursor++;
                return el;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() throws IllegalStateException {
            if (flag) {
                size--;
                data[cursor - 1] = data[size];
                flag = false;
                data[size] = null;
                cursor--;
            }
            else {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModifCollection<T> that = (ModifCollection<T>) o;
        if (size != that.size) return false;
        boolean f = true;
        for (int i = 0; i < data.length; i++) {
            if (!f) break;
            for (int j = i; j < that.data.length; j++) {
                if (data[i].equals(that.data[j])) {
                    T el = that.data[j];
                    that.data[j] = that.data[i];
                    that.data[i] = el;
                    break;
                }
                if (j == that.data.length - 1 && !(data[i].equals(that.data[j]))) {
                    f = false;
                    break;
                }
            }
        }
        return f;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, maxsize, flag);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "ModifCollection{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", maxsize=" + maxsize +
                '}';
    }
}


