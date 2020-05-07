import java.awt.*;

public class Cup implements Comparable <Cup> {
    private int volume;
    private Color colour;

    public Cup(int volume, Color colour) {
        this.volume = volume;
        this.colour = colour;
    }

    @Override
    public int compareTo(Cup o) {
        int flag = this.colour.getRGB() - o.colour.getRGB();
        if (flag == 0) {
            return this.volume - o.volume;
        }
        return flag;
    }
}
