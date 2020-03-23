import java.util.Arrays;

public class LambdaComparator {
    public static void main(String[] args) {
        Student[] students = new Student[4];
        students[0] = new Student(95.0, "Rishat", true);
        students[1] = new Student(8.0, "Alla", false);
        students[2] = new Student(11.0, "Alex", false);
        students[3] = new Student(8.0, "Alex", true);
        ArrayBubbleSortComp.sort(students, (st1, st2) -> (int) (st2.getPoint() - st1.getPoint()));
        System.out.println(Arrays.toString(students));
    }
}
