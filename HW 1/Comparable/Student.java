public class Student implements Comparable <Student> {
    private Double point;
    private String name;

    public Student(Double point, String name) {
        this.point = point;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        int flag = this.point.compareTo(o.point);
        if (flag == 0) {
            flag = this.name.compareTo(o.name);
        }
        return flag;
    }
}
