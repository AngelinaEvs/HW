import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DownloadSmthFromSite {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter URI:");
            URI uri = new URI(sc.next());
            System.out.println("Enter the path:");
            String path = String.valueOf(Paths.get(sc.next()));
            System.out.println("Enter the file's name:");
            String name = sc.next();
            getSomethingFromSite(uri, path, name);
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    private static File download(URI uri, String path, String name) {
        try {
            URLConnection connection = uri.toURL().openConnection();
            String fileExtension = MimeTypes.getDefaultMimeTypes().forName(connection.getContentType().split(";")[0]).getExtension();
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            Files.copy(in, new File((path + "\\" + name + fileExtension)).toPath());
            return new File(path + "\\" + name + fileExtension);
        } catch (IOException | MimeTypeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void getSomethingFromSite(URI uri, String path, String name) {
        int count = 1;
        try {
            File file = download(uri, path, name);
            String fileExtension = file.getName().substring(file.getName().lastIndexOf('.') + 1);
            if (fileExtension.equals("html") || fileExtension.equals("htm")) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    for (int i = 0; i < line.length(); i++)  {
                        if (line.charAt(i) == '<') {
                            i++;
                            if (line.length() > i + 1 && line.substring(i, i + 2).equals("a ")) {
                                i = i + 2;
                                while (line.length() < i + 4 &&!line.substring(i, i + 4).equals("href")) i++;
                                i = i + 4;
                                while (line.charAt(i) != '\"') {
                                    i++;
                                }
                                StringBuilder sb = new StringBuilder();
                                i++;
                                while (line.charAt(i) != '\"') {
                                    sb.append(line.charAt(i));
                                    i++;
                                }
                                if (!(new URI(sb.toString()).isAbsolute())) sb.insert(0, "https://" + uri.toString().split("//")[1].split("/")[0]);
                                URI u = new URI(sb.toString());
                                download(u, path, "Page" + count);
                                count++;
                                Thread.sleep(100);
                            }
                        }
                    }
                    line = reader.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
