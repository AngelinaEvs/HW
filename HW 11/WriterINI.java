import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class WriterINI extends Writer {
    private BufferedWriter out;

    public WriterINI(Writer out) {
        this.out = new BufferedWriter(out);
    }

    public void writeINI(Map<String, String> text) throws IOException {
        Set<Map.Entry<String, String>> set = text.entrySet();
        for (Map.Entry<String, String> stringStringEntry : set) {
            out.write(String.valueOf(stringStringEntry) + '\n');
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        out.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return out.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return out.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return out.append(c);
    }
}
