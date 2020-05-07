import java.util.Objects;

public class Square implements Comparable <Square> {
    private Double side_a;

    public Square(Double side_a) {
        this.side_a = side_a;
    }

    @Override
    public int compareTo(Square o) {
        if (this.side_a.compareTo(o.side_a) == 0) {
            return 0;
        }
        else
            return (int) (this.side_a * this.side_a - o.side_a * o.side_a);
    }

    @Override
    public String toString() {
        return "side_a = " + side_a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square rectangle = (Square) o;
        return Double.compare(rectangle.side_a, side_a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side_a);
    }
}
