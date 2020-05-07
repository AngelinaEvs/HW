import java.util.Iterator;
import java.util.NoSuchElementException;

public class StartEndIterator <T> implements Iterator <T> {
    protected T[] data;
    protected int cursor;

    public StartEndIterator(T[] data) {
        this.cursor = 0;
        this.data = data;
    }
    public boolean hasNext() {
        if (!((data.length/2 > cursor && data.length % 2 == 0) ||
                (data.length/2 + 1 > cursor && data.length % 2 == 1))) {
            return false;
        }
        for (int i = cursor; i < data.length/2 + 1; i++) {
            if (data[i] != null || data[data.length - 1 - cursor] != null) {
                return true;
            }
        }
        return false;
    }

    public T next() {
        try {
            T el = data[cursor];
            while (data[cursor] == null && data[data.length - 1 - cursor] == null) {
                cursor++;
            }
            if (data[cursor] == null && data[data.length - 1 - cursor] != null) {
                el = data[data.length - 1 - cursor];
                cursor++;
                return el;
            }
            else if (data[cursor] != null && data[data.length - 1 - cursor] == null) {
                el = data[cursor];
                cursor++;
                return el;
            }
            if (data[cursor] != null && data[data.length - 1 - cursor] != null) {
                if (data[cursor] != null) {
                    el = data[cursor];
                    data[cursor] = null;
                    return el;
                }
            }
            return el;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}
