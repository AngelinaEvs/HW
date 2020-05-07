import java.util.Comparator;

public class ArrayBubbleSortComp<T> {
    public static <T> void sort(Comparable<T>[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                int flag = data[j].compareTo((T) data[j + 1]);
                if (flag > 0) {
                    T t = (T) data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = (Comparable<T>) t;
                }
            }
        }
    }

    public static <T> void sort(T[] data, Comparator<? super T> comp) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                int flag = comp.compare(data[j], data[j + 1]);
                if (flag > 0) {
                    T t = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = t;
                }
            }
        }
    }
}
