import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class GenericEA <T> implements Iterable<T> {
    private final int MAXSIZE = 10;
    private T[] arr;
    private int size;
    private int maxsize;

    public GenericEA() {
        this.arr = (T[]) new Object[this.MAXSIZE];
        this.size = 0;
        this.maxsize = this.MAXSIZE;
    }

    public GenericEA(T value, int i) throws NegativeArraySizeException {
        if (i >= 0) {
            this.arr = (T[]) new Object[this.MAXSIZE];
            for(int j = 0; j < i; j++) {
                this.arr[j] = value;
            }
            this.size = i;
            this.maxsize = this.MAXSIZE;
        } else {
            throw new NegativeArrayIndexException("Entered a negative number!");
        }
    }

    public GenericEA(T[] arr) {
        if (arr != null) {
            this.size = arr.length;
            this.maxsize = arr.length;
            this.arr = (T[]) new Object[this.size];
            System.arraycopy(arr, 0, this.arr, 0, this.size);
       }
        else {
            this.arr = (T[]) new Object[this.MAXSIZE];
            this.size = 0;
            this.maxsize = this.MAXSIZE;
        }
    }

    public void add(T element) {
        this.size++;
        if (this.size > this.maxsize) {
            this.maxsize *= 2;
            T[] newArr = (T[]) new Object[this.maxsize];
            System.arraycopy(this.arr, 0, newArr, 0, this.size - 1);
            newArr[this.size - 1] = element;
            this.arr = newArr;
        }
        else {
            this.arr[this.size - 1] = element;
        }
    }

    public void addBetween(T element, int j) throws NegativeArrayIndexException {
        if (j >= 0) {
            this.size++;
            if (j < this.arr.length) {
                for (int i = this.size - 1; i >= j; i--) {
                    this.arr[i + 1] = this.arr[i];
                }
                this.arr[j] = element;
            } else {
                this.maxsize = j + 1;
                T[] newArr = (T[]) new Object[this.maxsize];;
                System.arraycopy(this.arr, 0, newArr, 0, this.size - 1);
                newArr[j] = element;
                this.arr = newArr;
            }
        }
        else {
            throw new NegativeArrayIndexException("Entered a negative index of array!");
        }
    }

    public boolean contains(T value, int i) throws ArrayIndexOutOfBoundsException {
        if (i >= 0 && i < this.size) {
            if (this.arr[i] == value) {
                return true;
            } else return false;
        } else {
            throw new ArrayIndexOutOfBoundsException("You have gone beyond the array");
        }
    }

    public int indexOf(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T value) {
        for(int i = this.size - 1; i >= 0; i--) {
            if (this.arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public T finalElement() {
        return this.arr[size - 1];
    }

    public void removeNum(int i) throws NegativeArrayIndexException {
        if (i >= 0 && i < this.size) {
            for (int j = i; j < this.size; j++) {
                this.arr[j] = this.arr[j + 1];
            }
            this.size--;
        } else if (i < 0) {
            throw new NegativeArrayIndexException("Entered a negative index!");
        }
    }

    public void remove(T value, int count) {
        if (count > 0) {
            while (count != 0) {
                int j = -1;
                for (int i = 0; i < this.size; i++) {
                    if (this.arr[i] == value && j == -1) {
                        j = i;
                    }
                    if (j != -1) {
                        this.arr[i] = this.arr[i + 1];
                    }
                }
                count--;
                this.size--;
            }
        } else if (count < 0) {
            throw new NegativeNumberException("Entered a negative number!");
        }
    }

    public void removeAll(T value) {
        int count = 0;
        int j = -1;
        for (int i = 0; i < this.size - 1; i++) {
            if (this.arr[i] == value) {
                count++;
                if (j == -1) {
                    j = i;
                }
            }
        }
        this.size -= count;
        int k = 1;
        if (count != 0) {
            for (int i = j; i < this.size; i++) {
                if (this.arr[i + k] != value) {
                    this.arr[i] = this.arr[i + k];
                } else if (this.arr[i + k] == value) {
                    while (this.arr[i + k] == value) {
                        k++;
                    }
                    this.arr[i] = this.arr[i + k];
                }
            }
        }
    }

    public T get(int i) throws ArrayIndexOutOfBoundsException {
        if (i >= 0) {
            return this.arr[i];
        } else if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("You have gone beyond the array!");
        }
        return this.arr[i];
    }

    public int getSize() {
        return this.size;
    }

    public int getMaxsize() {
        return this.maxsize;
    }

    public void set(int i, T element) {
        if (i >= 0 && i < this.size) {
            this.arr[i] = element;
        }
        if (i >= this.size || i < 0) {
           this.addBetween(element, i);
        }
    }

    public void setMaxsize(int maxsize) throws NegativeArrayIndexException {
        if (maxsize < this.maxsize) {
            throw new NegativeArrayIndexException("Entered a negative number!");
        }
        else if (maxsize > this.maxsize) {
            this.maxsize = maxsize;
            T[] newArr = (T[]) new Object[this.maxsize];
            System.arraycopy(this.arr, 0, newArr, 0, this.size);
            this.arr = newArr;
        }
    }



     @Override
    public String toString() {
        return "EndlessArray{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                ", maxsize=" + maxsize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericEA that = (GenericEA) o;
        return size == that.size &&
                //maxsize == that.maxsize &&
                Arrays.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, maxsize, MAXSIZE);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
    private class ArrayIterator<T> implements Iterator <T> {
        protected int cursor;

        public ArrayIterator() {
            this.cursor = 0;
        }

        public boolean hasNext() {
            return size > cursor;
        }

        public T next() {
            try {
                T el = (T) arr[cursor];
                cursor++;
                return el;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new java.util.NoSuchElementException();
            }
        }
    }

}

































 /*else {
            throw new ArrayIndexOutOfBoundsException("You have gone beyond the array!");
            //Ангелина, измени этот дурацкий ришатов текст в кавычках выше на адекватный
        }*/