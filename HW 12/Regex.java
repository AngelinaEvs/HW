import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the domain name:");
        String name = sc.nextLine();
        if (isDomainName(name)) System.out.println("It's the domain name");
        else System.out.println("It's not the domain name");
        System.out.println("Enter the text:");
        String text = sc.nextLine();
        System.out.println(searchDomainNameFromText(text).toString());
        System.out.println("Enter the email:");
        String email = sc.nextLine();
        Map<String, String> levels = getLowerAndTopLevelsOfDomainEmail(email);
        System.out.println(levels.toString());
    }

    private static boolean isDomainName(String name) {
        return Pattern.matches("^([a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]\\.)+[a-zA-Z]{2,6}$", name) && (name.length() < 256 );
    }

    private static List<String> searchDomainNameFromText(String text) {
        List<String> names = new ArrayList<>();
        Matcher matcher = Pattern.compile("([a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]\\.)+[a-zA-Z]{2,6}").matcher(text);
        while (matcher.find()) {
            if (matcher.group().length() < 256) names.add(matcher.group());
        }
        return names;
    }
    private static Map<String, String> getLowerAndTopLevelsOfDomainEmail(String name) {
        Map<String, String> levels = new HashMap<>();
        String regex = "^([a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]\\.)*[a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]@([a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]\\.)+[a-zA-Z]{2,6}$";
        String lower = null;
        String top = null;
        if (Pattern.matches(regex, name)) {
            Matcher matcher = Pattern.compile("@[a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z]").matcher(name);
            if (matcher.find()) {
                lower = matcher.group().substring(1);
            }
            matcher = Pattern.compile("[a-zA-Z]{2,6}$").matcher(name);
            if (matcher.find()) {
                top = matcher.group();
            }
        }
        levels.put(lower, top);
        return levels;
    }
}
