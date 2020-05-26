import org.ho.yaml.Yaml;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class YamlStudentOutputStream extends OutputStream {
    private OutputStream out;

    public YamlStudentOutputStream(OutputStream out) {
        this.out = out;
    }

    public void writeStudent(Student student) {
        Yaml.dump(student, out);
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YamlStudentOutputStream that = (YamlStudentOutputStream) o;
        return Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "YamlStudentOutputStream{" +
                "out=" + out +
                '}';
    }
}