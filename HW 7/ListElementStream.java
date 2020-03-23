
import java.util.Arrays;
import java.util.List;

public class ListElementStream {
    public static void main(String[] args) {
        List<Integer> first = Arrays.asList(3, 6, 11);
        List<Integer> second = Arrays.asList(2, 5, 4);

        int maxSecond = second.stream()
                .max((o1, o2) -> o1 - o2)
                .get();

        first.stream()
                .filter(o -> o > maxSecond)
                .forEach(System.out::println);
    }
}
