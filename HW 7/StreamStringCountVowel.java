import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StreamStringCountVowel {
    public static void main(String[] args) {
        Set<String> set= new HashSet<>(Arrays.asList("zero", "oonee", "two", "three",
                "Navigate", "Element"));
        System.out.println(set.stream()
                .filter(str -> countVow(str) == 1)
                .count());
    }

    public static int countVow(String str) {
        int countV = 0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            char ch = c[i];
            if (countV > 3) return 1;
            if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'y' || ch == 'Y' || ch == 'u' || ch == 'U'
                || ch == 'o' || ch == 'O' || ch == 'j' || ch == 'J' || ch == 'i' || ch == 'I') countV++;
        }
        if (countV > 3) return 1;
        else return 0;
    }
}
