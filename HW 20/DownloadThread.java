import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;

public class DownloadThread extends Thread {
    private URI uri;
    private String path;
    private String name;
    private String fileExtension;
    private File file;

    public DownloadThread(URI uri, String path, String name) {
        this.uri = uri;
        this.path = path;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            URLConnection connection = uri.toURL().openConnection();
            if (!Thread.interrupted()) {
                fileExtension = MimeTypes.getDefaultMimeTypes().forName(connection.getContentType().split(";")[0]).getExtension();
                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                file = new File(path + "\\" + name + fileExtension);
                Files.copy(in, file.toPath());
                if (file.delete()) System.out.println("The download is interrupted");
            } else System.out.println("The download is interrupted");
        } catch (IOException | MimeTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    public URI getUri() {
        return uri;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public File getFile() {
        return file;
    }
}
