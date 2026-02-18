import java.util.Comparator;
public class ComparatorByGPA implements Comparator<Student>{
    public int compare(Student s1, Student s2){
        Double gpa1 = s1.getGPA();
        Double gpa2 = s2.getGPA();
        return gpa1.compareTo(gpa2);
    }
}
