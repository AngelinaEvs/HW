import javax.sound.midi.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicalFile {
    public static void main(String[] args) throws InterruptedException, MidiUnavailableException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> notes = new ArrayList<>();
        enterNotes(sc, notes);
        System.out.println(saveNotesInFile(sc, notes));
        System.out.println(playNotesFile(sc));
    }

    private static void enterNotes(Scanner sc, ArrayList<Integer> notes) {
        try {
            printNoteTable();
            System.out.println("Enter notes from the table by completing your input with a non-numeric character ('*' - for example) : ");
            String s = "";
            while (s.isEmpty()) {
                if (sc.hasNextInt()) {
                    int note = sc.nextInt();
                    if (note >= 0 && note <= 131) {
                        notes.add(note);
                    } else throw new IllegalArgumentException("You entered incorrect data");
                } else s = sc.next();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String saveNotesInFile(Scanner sc, ArrayList<Integer> notes) {
        if (notes.size() == 0) return "Empty notes are entered!";
        System.out.println("Enter the path to the directory:");
        Path directory = Paths.get(sc.next()).toAbsolutePath().normalize();
        if (!(Files.exists(directory))) return "This directory is not exist!";
        System.out.println("Enter the name of file:");
        String name = sc.next();
        Path path = Paths.get(directory + "\\" + name + ".txt");
        if (Files.exists(path)) return "This file with the name is already exist.";
        try (FileWriter out = new FileWriter(new File(path.toString()))) {
            for (Integer note : notes) {
                out.write(String.valueOf(note));
                out.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File is created";
    }

    private static String playNotesFile(Scanner sc) {
        try {
            System.out.println("Enter the path to the file:");
            Path path = Paths.get(sc.next()).toAbsolutePath().normalize();
            if (!(Files.exists(path))) return "This directory is not exist!";
            File file = new File(path.toString());
            ArrayList<Integer> notesFromFile = new ArrayList<>();
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                while ((line = in.readLine()) != null) {
                    notesFromFile.add(Integer.parseInt(line));
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (notesFromFile.size() == 0) return "This file is empty!";
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel[] midiChannels = synthesizer.getChannels();
            System.out.println("Enter the instrument(0 - 128): ");
            Instrument[] instruments = synthesizer.getAvailableInstruments();
            for (int j = 0; j < 129; j++) {
                System.out.println(j + " is " + instruments[j].toString().split(":\\s")[1].split(" [bank]")[0]);
            }
            int instrument = sc.nextInt();
            System.out.println("You entered the " + instruments[instrument].toString().split(":\\s")[1].split(" [bank]")[0]);
            System.out.println("Ок!");
            for (int value : notesFromFile) {
                midiChannels[4].noteOn(value, 80);
                Thread.sleep(500);
                midiChannels[4].noteOff(value);
            }
            synthesizer.close();
            System.out.println("You entered the " + instruments[instrument].toString().split(":\\s")[1].split(" [bank]")[0]);
            return "The music is played =)";
        } catch (MidiUnavailableException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private static void printNoteTable() {
        System.out.format("%6s%17s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%1s",
                "octave", "octave", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "\n");
        System.out.format("%6s%17s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%1s", "------", "----------------",
                "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                -1, "-", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                0, "subcounteroctave", 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                1, "counteroktave", 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                2, "big", 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                3, "small", 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                4, "first", 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                5, "second", 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                6, "third", 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                7, "fourth", 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                8, "fifth", 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                9, "-", 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, "\n");
    }
}
