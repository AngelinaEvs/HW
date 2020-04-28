import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteIntChar {
    public static void main(String[] args) {
        try (OutputStream fileOutputStream = new FileOutputStream("C:\\Users\\user\\Documents\\Text\\new.txt")) {
            int num = 10122;
            char a = 'ю';
            for (int i = 3; i >= 0; i--) {
                fileOutputStream.write(num >> i * 8);
            }
            for (int i = 1; i >=0; i--) {
                fileOutputStream.write(a >> i * 8);
            }
        } catch (IOException e) {
            System.err.println("Err");
        }
    }
}