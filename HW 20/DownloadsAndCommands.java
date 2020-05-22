public class DownloadsAndCommands {
    public static void main(String[] args) {
        Thread commandsThread = new Thread(new CommandThread());
        commandsThread.start();
    }
}
