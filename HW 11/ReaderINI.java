import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class ReaderINI extends Reader {
    private BufferedReader in;

    public ReaderINI(Reader in) {
        this.in = new BufferedReader(in);
    }

    public void readINI() throws IOException {
        try {
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Cannot read the ini-file");
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return in.read();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        return in.read(target);
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return in.read(cbuf);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return in.ready();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        in.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        in.reset();
    }
}
