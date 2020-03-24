import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private double averageScore;
    private String name;
    private int group;

    public Student() {
        this.name = "No info";
    }

    public Student(String name, int group, double averageScore) {
        this.averageScore = averageScore;
        this.name = name;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", averageScore='" + averageScore + '\'' +
                ", group=" + group +
                '}' + "\n";
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group == student.group &&
                Objects.equals(averageScore, student.averageScore) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageScore, name, group);
    }
}
