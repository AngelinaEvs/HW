import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class StudObjectOutputStream extends OutputStream {
    private ObjectOutputStream out;

    public StudObjectOutputStream(OutputStream out) throws IOException {
        this.out = new ObjectOutputStream(out);
    }

    public void writeStud(Student student) throws IOException {
        try {
            out.writeObject(student);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void writeObject(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public void writeUnshared(Object obj) throws IOException {
        out.writeUnshared(obj);
    }

    public void defaultWriteObject() throws IOException {
        out.defaultWriteObject();
    }

    public ObjectOutputStream.PutField putFields() throws IOException {
        return out.putFields();
    }

    public void writeFields() throws IOException {
        out.writeFields();
    }

    public void reset() throws IOException {
        out.reset();
    }

    public void write(int val) throws IOException {
        out.write(val);
    }

    public void write(byte[] buf) throws IOException {
        out.write(buf);
    }

    public void write(byte[] buf, int off, int len) throws IOException {
        out.write(buf, off, len);
    }

    public void flush() throws IOException {
        out.flush();
    }

    public void close() throws IOException {
        out.close();
    }

    public void writeBoolean(boolean val) throws IOException {
        out.writeBoolean(val);
    }

    public void writeByte(int val) throws IOException {
        out.writeByte(val);
    }

    public void writeShort(int val) throws IOException {
        out.writeShort(val);
    }

    public void writeChar(int val) throws IOException {
        out.writeChar(val);
    }

    public void writeInt(int val) throws IOException {
        out.writeInt(val);
    }

    public void writeLong(long val) throws IOException {
        out.writeLong(val);
    }

    public void writeFloat(float val) throws IOException {
        out.writeFloat(val);
    }

    public void writeDouble(double val) throws IOException {
        out.writeDouble(val);
    }

    public void writeBytes(String str) throws IOException {
        out.writeBytes(str);
    }

    public void writeChars(String str) throws IOException {
        out.writeChars(str);
    }

    public void writeUTF(String str) throws IOException {
        out.writeUTF(str);
    }
}
