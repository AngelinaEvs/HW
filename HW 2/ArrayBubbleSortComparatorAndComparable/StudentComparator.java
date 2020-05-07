import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        //return o1.getPoint().compareTo(o2.getPoint());
        int flag = o1.getPoint().compareTo(o2.getPoint());
        if (flag == 0) {
            return o1.getName().compareTo(o2.getName());
        }
        return flag;
    }
}
