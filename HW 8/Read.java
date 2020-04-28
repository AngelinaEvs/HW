import java.io.FileInputStream;
import java.io.IOException;

public class Read {
    public static void main(String[] args) throws IOException {
        try (FileInputStream in = new FileInputStream("C:\\Users\\user\\Documents\\Text\\new.txt")) {
            int b = 0;
            int s = 0;
            char ch;
            for (int i = 3; i >= 0; i--) {
                b = in.read() << i * 8 | b;
            }
            for (int i = 1; i >= 0; i--) {
                s = in.read() << i * 8 | s;
            }
            ch = (char) s;
            System.out.println("int: " + b);
            System.out.println("char: " + ch);
        } catch (IOException e) {
            System.err.println("Err");
        }
    }
}

