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
        if (col != null) {
            this.size = col.size();
            this.data = (T[]) new Object[this.size];
            int i = 0;
            Iterator<T> it = (Iterator<T>) col.iterator();
            while (it.hasNext()) {
                this.data[i] = it.next();
                i++;
            }
        } else throw new NullPointerException();
    }

    public boolean add(T el) throws NullPointerException {
        if (el != null) {
            size++;
            if (size > data.length) {
                T[] newData = (T[]) new Object[data.length * 2];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[size - 1] = el;
                data = newData;
            } else data[size - 1] = el;
            return true;
        }
        else throw new NullPointerException();
    }

    public int size() {
        return this.size;
    }

    public Iterator<T> iterator() {
        return new StrangeArrayIterator();
    }

    private class StrangeArrayIterator <T> implements Iterator<T> {
        protected int cursor;

        public boolean hasNext() {
            return (size > cursor);
        }

        public T next() throws NoSuchElementException {
            try {
                T el = (T) data[cursor];
                flag = true;
                cursor++;
                return el;
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
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
        for (int i = 0; i < size; i++) {
            if (!f) break;
            for (int j = i; j < size; j++) {
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


