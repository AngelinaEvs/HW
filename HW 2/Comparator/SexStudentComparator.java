import java.util.Comparator;

public class SexStudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        Boolean sex1 = o1.isSex();
        Boolean sex2 = o2.isSex();
        return  sex1.compareTo(sex2);
    }
}
