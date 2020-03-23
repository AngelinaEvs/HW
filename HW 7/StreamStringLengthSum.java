import java.util.Arrays;
import java.util.Collection;

public class StreamStringLengthSum {
    public static void main(String[] args) {
        Collection<String> cl = Arrays.asList("School", "Nice", "Nature", "April", "Laptop");
        System.out.println(cl.stream()
                .mapToInt(String::length)
                .filter(length -> length > 5)
                .sum());
    }
 }
