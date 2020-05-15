import javax.sound.midi.*;
import java.util.Scanner;

public class PlayCommands {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel[] midiCh = synthesizer.getChannels();
            System.out.println("Enter the instrument(0 - 128): ");
            Instrument[] instruments = synthesizer.getAvailableInstruments();
            for (int j = 0; j < 129; j++) {
                System.out.println(j + " is " + instruments[j].toString().split(":\\s")[1].split(" [bank]")[0]);
            }
            int instrument = sc.nextInt();
            midiCh[4].programChange(instrument);
            System.out.println("You entered the " + instruments[instrument].toString().split(":\\s")[1].split(" [bank]")[0]);
            System.out.println("Enter the note: ");
            String sound = sc.next();
            play(midiCh, sound);
            int[] melody = {53, 48, 53, 48, 53, 52, 52, 52, 48, 52, 48, 52, 53, 53, 53, 48, 53, 48, 53, 52, 52, 52, 48, 52, 48, 52, 53};
            playMelody(midiCh, melody);
            synthesizer.close();
        } catch (MidiUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void play(MidiChannel[] midiCh, String sound) {
        switch (sound) {
            case ("1"):
                playSound(midiCh, 48);
                break;
            case ("2"):
                playSound(midiCh, 49);
                break;
            case ("3"):
                playSound(midiCh, 50);
                break;
            case ("4"):
                playSound(midiCh, 51);
                break;
            case ("5"):
                playSound(midiCh, 52);
                break;
            case ("6"):
                playSound(midiCh, 53);
                break;
            case ("7"):
                playSound(midiCh, 54);
                break;
            case ("8"):
                playSound(midiCh, 55);
                break;
            case ("9"):
                playSound(midiCh, 56);
                break;
            case ("+"):
                playSound(midiCh, 57);
                break;
            case ("-"):
                playSound(midiCh, 58);
                break;
            case ("*"):
                playSound(midiCh, 59);
                break;
            case ("/"):
                break;
            default:
                System.out.println("Try again.");
                play(midiCh, new Scanner(System.in).next());
                break;
        }
    }

    private static void playSound(MidiChannel[] midiCh, int number) {
        try {
            midiCh[4].noteOn(number, 80);
            Thread.sleep(500);
            midiCh[4].noteOff(number);
            play(midiCh, new Scanner(System.in).next());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void playMelody(MidiChannel[] midiCh, int[] melody) {
        try {
            for (int value : melody) {
                midiCh[4].noteOn(value, 80);
                Thread.sleep(500);
                midiCh[4].noteOff(value);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
