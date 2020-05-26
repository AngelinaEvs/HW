import org.ho.yaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Objects;

public class YamlStudentInputStream extends InputStream {
    private InputStream in;

    public YamlStudentInputStream(InputStream in) {
        this.in = in;
    }

    public Student readStudent() throws IOException {
        try {
            return (Student) Yaml.load(in);
        } catch (ClassCastException e) {
            throw new IOException("Unable to read student", e);
        }
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YamlStudentInputStream that = (YamlStudentInputStream) o;
        return Objects.equals(in, that.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(in);
    }

    @Override
    public String toString() {
        return "YamlStudentInputStream{" +
                "in=" + in +
                '}';
    }
}