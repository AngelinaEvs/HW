import java.util.Iterator;

public class RandomNumberIterator<T> implements Iterator<T> {
    protected T[] data;
    protected int range;

    public RandomNumberIterator(T[] data) {
        this.data = data;
        this.range = data.length - 1;
    }

    public boolean hasNext() {
        return data.length > 0;
    }

    public T next() {
        try {
            return data[(int) ((Math.random() * (range + 1)))];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new java.util.NoSuchElementException();
        }
    }
}

