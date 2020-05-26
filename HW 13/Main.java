import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Student st1 = new Student("Angelina", 902, 86.0);
        Student st2 = new Student("Rishat", 902, 95.0);

        Collection<Student> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);

        try (JsonStudentOutputStream out = new JsonStudentOutputStream(new FileOutputStream("src\\file1.json"))) {
            out.writeAllStudents(list);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (JsonStudentInputStream in = new JsonStudentInputStream(new FileInputStream("src\\file1.json"))) {
            System.out.println(in.readAllStudents().toString());
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

        try (YamlStudentOutputStream o = new YamlStudentOutputStream(new FileOutputStream("src\\file.yaml"))) {
            o.writeStudent(st1);
            o.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (YamlStudentInputStream i = new YamlStudentInputStream(new FileInputStream("src\\file.yaml"))) {
            System.out.println(i.readStudent().toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
