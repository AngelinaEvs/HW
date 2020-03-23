import java.util.Objects;

public class Student {
    private Double point;
    private String name;
    private boolean sex; //true - man, false - woman

    public Student(Double point, String name) {
        this.point = point;
        this.name = name;
    }

    public Student(Double point, String name, boolean sex) {
        this.point = point;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "point=" + point +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sex == student.sex &&
                Objects.equals(point, student.point) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, name, sex);
    }
}
