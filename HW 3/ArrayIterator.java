import java.util.Iterator;

public class ArrayIterator implements Iterator <String> {
    protected String[] data;
    protected int cursor;

    public ArrayIterator(String[] data) {
        this.cursor = 0; //конечный эл
        this.data = data;
    }

    public boolean hasNext() {
        return data.length > cursor;
    }

    public String next() {
        /*if (data.length <= cursor) {
            throw new java.util.NoSuchElementException();
        }*/
        try {
            String el = data[cursor];
            cursor++;
            return el;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new java.util.NoSuchElementException();
        }
    }
}
