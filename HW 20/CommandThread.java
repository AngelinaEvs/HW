import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

public class CommandThread implements Runnable {
    private Scanner sc = new Scanner(System.in);
    private DownloadThread downloadThread;

    @Override
    public void run() {
        try {
            System.out.println("Enter the command:");
            commands(sc.next());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private void commands(String command) throws URISyntaxException, IOException {
        switch (command) {
            case "start":
                download();
                break;
            case "ch":
                checkPercents();
                break;
            case "stop":
                stop();
                break;
            default:
                System.out.println("Try again.");
                System.out.println("Enter the command:");
                commands((new Scanner(System.in)).next());
        }
    }

    private void download() throws URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter URI:");
        URI uri = new URI(sc.next());
        System.out.println("Enter the path:");
        String path = String.valueOf(Paths.get(sc.next()));
        System.out.println("Enter the file's name:");
        String name = sc.next();
        downloadThread = new DownloadThread(uri, path, name);
        downloadThread.start();
        System.out.println("Enter the command:");
        commands(sc.next());
    }

    private void checkPercents() throws URISyntaxException, IOException {
        double lengthFile = downloadThread.getUri().toURL().openConnection().getContentLength();
        double nowLengthFile = 0;
        if (downloadThread.getFileExtension() != null) {
            nowLengthFile = downloadThread.getFile().length();
        }
        System.out.println(( nowLengthFile / lengthFile) * 100 + "%");
        System.out.println("Enter the command:");
        commands(sc.next());
    }

    private void stop() throws URISyntaxException, IOException {
        if (downloadThread != null) {
            downloadThread.interrupt();
            downloadThread.getFile().delete();
            System.out.println("Thread is stopped");
            System.out.println("Enter the command:");
            commands(sc.next());
        } else System.out.print("Thread is null");
    }
}
