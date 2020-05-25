import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Encoding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path to the file:");
        Path path = Paths.get(sc.next()).toAbsolutePath().normalize();
        if (!(Files.exists(path))) System.out.println("This directory is not exist!");
        else {
            File file = new File(path.toString());
            System.out.println("Enter the encoding:" + '\n' + "If you want to print text in CMD, enter \"Cp866\".");
            Charset charset = Charset.forName(sc.next());
            try {
                List<String> listOfLines = Files.readAllLines(file.toPath());
                for (String listOfLine : listOfLines) {
                    System.out.println(new String(listOfLine.getBytes(), charset));
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
