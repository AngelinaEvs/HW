import java.util.Iterator;
import java.util.NoSuchElementException;

public class StrangeArrayIterator <T> implements Iterator <T> {
    protected T[] data;
    protected int cursor;

    public StrangeArrayIterator(T[] data) {
        this.cursor = 0;
        this.data = data;
    }

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
            T el = data[cursor];
            cursor = cursor + 2;
            while (el == null) {
                el = data[cursor];
                cursor = cursor + 2;
            }
            return el;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}
