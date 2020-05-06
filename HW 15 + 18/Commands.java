import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;


public class Commands {
    private Path path = Paths.get(".").normalize().toAbsolutePath();
    private Path temporary;
    private Scanner sc = new Scanner(System.in);

    public Commands() throws IOException {
        System.out.print(path + "> ");
        command(sc.nextLine());
    }

    private void command(String command) throws IOException {
        String cmd = command.trim().split("\\s")[0];
        if (command.trim().length() != cmd.length()) {
            if (Files.exists(Paths.get(command.trim().split("\\s")[1]))) {
                    temporary = Paths.get(command.trim().split("\\s")[1]);
                } else {
                    System.out.println("You entered incorrect data. Try again.");
                    System.out.print(path + "> ");
                    command(sc.nextLine());
                }
        }
        switch (cmd.toLowerCase()) {
            case "ls":
                listOfFilesOrDirectoriesInDirectory();
                break;
            case "cd":
                changeTheDirectory();
                break;
            case "cp":
                copyFilesOrDirectory();
                break;
            case "dl":
                deleteFileOrDirectory();
                break;
            case "open":
                openFile();
                break;
            case "exit":
                break;
            default:
                System.out.println("You entered a nonexistent command. Try again.");
                command(sc.nextLine());
        }
    }

    private void listOfFilesOrDirectoriesInDirectory() {
        try {
            System.out.println("Directory: " + temporary.toFile());
            System.out.printf("%s%28s%20s%n", "Mode", "LastWriteTime", "Length Name");
            System.out.printf("%s%28s%20s%n", "----", "-------------", "------ ----" );
            File directory = new File(String.valueOf(temporary));
            FileTime timeOfFilesOrDirectory = Files.getLastModifiedTime(Paths.get(String.valueOf(temporary)));
            String[] filesOfDirectory = directory.list();
            for (int i = 0; i < filesOfDirectory.length; i++) {
                String type;
                if (Files.isDirectory(Paths.get(temporary + "\\"+ filesOfDirectory[i]))) type = "dir";
                else type = "file";
                String length = "";
                if (type.equals("file")) {
                    length = String.valueOf(Files.size(Paths.get(temporary + "\\" + filesOfDirectory[i])));
                }
                System.out.printf("%4s%28s%15s%s%n", type,
                         timeOfFilesOrDirectory.toString().substring(11, 16) + " " +
                         timeOfFilesOrDirectory.toString().substring(0, 10),
                         length, " " + filesOfDirectory[i]);
            }
            System.out.print(path + "> ");
            command(sc.nextLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeTheDirectory() throws IOException {
        if (String.valueOf(temporary).equals("..")) {
            if (path.toString().lastIndexOf('\\') != path.toString().length() - 1) {
                path = Paths.get(path.toString().substring(0, path.toString().lastIndexOf('\\')));
                if (!path.toString().contains("\\")) path = Paths.get(path + "\\");
            }
        }
        else if (temporary.isAbsolute()) path = temporary;
        System.out.print(path + "> ");
        command(sc.nextLine());
    }

    public void deleteFileOrDirectory() {
        try {
            Path directory = Paths.get(String.valueOf(temporary));
            if (Files.exists(directory)) {
                Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException {
                        Files.delete(directory);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } else System.out.println("The specified directory is not empty");
            System.out.print(path + "> ");
            command(sc.nextLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyFilesOrDirectory() {
        try {
            if (Files.isDirectory(temporary)) {
                Files.walkFileTree(temporary, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.copy(file, path.resolve(temporary.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                        return FileVisitResult.CONTINUE;
                    }
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        Files.createDirectories(path.resolve(temporary.relativize(dir)));
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
            else {
                if (Files.exists(temporary)) {
                    Files.copy(temporary, new File(path + "\\" +
                            temporary.toString().substring(temporary.toString().lastIndexOf("\\") + 1)).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            }
            System.out.print(path + "> ");
            command(sc.nextLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void openFile() {
        try {
            if (temporary.toString().length() > 0) {
                if (Files.exists(temporary) && !Files.isDirectory(temporary)) {
                    Desktop.getDesktop().open(temporary.toFile());
                } else System.out.println("You entered incorrect data. Try again.");
            }
            System.out.print(path + "> ");
            command(sc.nextLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}