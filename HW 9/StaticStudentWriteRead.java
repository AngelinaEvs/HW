import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;

public class StaticStudentWriteRead {
    public static void write(Collection<Student> stud, String path) throws FileNotFoundException {
        try (OutputStream out = new FileOutputStream(path)) {
            ByteBuffer buf = ByteBuffer.allocate(Integer.BYTES);
            buf.putInt(stud.size());
            out.write(buf.array());
            out.flush();
            for (Student student : stud) {
                ByteBuffer stbb = ByteBuffer.allocate(Character.BYTES * student.getName().length() +
                        2 * Integer.BYTES + Double.BYTES);
                stbb.putInt(student.getName().length());
                for (int i = 0; i < student.getName().length(); i++) {
                    stbb.putChar(student.getName().charAt(i));
                }
                stbb.putInt(student.getGroup());
                stbb.putDouble(student.getAverageScore());
                out.write(stbb.array());
                out.flush();
                stbb.clear();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Collection<Student> read(String path) {
        Collection<Student> stud = new ArrayList<>();
        String name;
        double averageScore;
        int group;
        try (InputStream in = new FileInputStream(path)) {
            ByteBuffer buf = ByteBuffer.allocate(Double.BYTES);
            for (int i = 0; i < Integer.BYTES; i++) {
                buf.put((byte) in.read());
            }
            buf.rewind();
            int size = buf.getInt();
            buf.clear();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < Integer.BYTES; j++) {
                    buf.put((byte) in.read());
                }
                buf.rewind();
                int nameSize = buf.getInt();
                buf.clear();
                char[] studName = new char[nameSize];
                for (int j = 0; j < nameSize; j++) {
                    buf.put((byte) in.read());
                    buf.put((byte) in.read());
                    buf.rewind();
                    studName[j] = buf.getChar();
                    buf.clear();
                }
                name = new String(studName);
                for (int j = 0; j < Integer.BYTES; j++) {
                    buf.put((byte) in.read());
                }
                buf.rewind();
                group = buf.getInt();
                buf.clear();
                for (int j = 0; j < Double.BYTES; j++) {
                    buf.put((byte) in.read());
                }
                buf.rewind();
                averageScore = buf.getDouble();
                buf.clear();
                stud.add(new Student(name, group, averageScore));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stud;
    }
}
