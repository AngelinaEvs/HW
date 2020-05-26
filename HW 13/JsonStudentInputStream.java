import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class JsonStudentInputStream extends InputStream {
    private BufferedReader in;
    private JSONParser parser;

    public JsonStudentInputStream(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.parser = new JSONParser();
    }

    public Student readStudent() throws IOException {
        try {
            JSONObject JSONStudent = (JSONObject) parser.parse(in);
            return new Student((String) JSONStudent.get("name"), (int) (long) JSONStudent.get("group"), (double) JSONStudent.get("average score"));
        } catch (IOException | ParseException e) {
            throw new IOException("Unable to read student", e);
        }
    }

    public Collection<Student> readAllStudents () throws IOException, ParseException {
        List<Student> students = new LinkedList<>();
        JSONArray JStudents = (JSONArray) parser.parse(in);
        for (Object jStudent : JStudents) {
            JSONObject JSONStudent = (JSONObject) jStudent;
            Student student = new Student((String) JSONStudent.get("name"), (int) (long) JSONStudent.get("group"), (double) JSONStudent.get("average score"));
            students.add(student);
        }
        return students;
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
        JsonStudentInputStream that = (JsonStudentInputStream) o;
        return Objects.equals(in, that.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(in);
    }

    @Override
    public String toString() {
        return "JsonStudentReader{" +
                "in=" + in +
                '}';
    }
}