import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double cgpa;

    Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
}

class StudentComparator implements Comparator<Student> {
    public int compare(Student first, Student second) {
        if (first.cgpa > second.cgpa) {
            return -1;
        }
        if (first.cgpa < second.cgpa) {
            return 1;
        }
        int nameOrder = first.name.compareTo(second.name);
        if (nameOrder != 0) {
            return nameOrder;
        }
        if (first.id < second.id) {
            return -1;
        }
        if (first.id > second.id) {
            return 1;
        }
        return 0;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);
        int n = input.nextInt();
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i < n; i++) {
            int id = input.nextInt();
            String name = input.next();
            double cgpa = input.nextDouble();
            students.add(new Student(id, name, cgpa));
        }
        Collections.sort(students, new StudentComparator());
        for (Student student : students) {
            System.out.println(student.name);
        }
        input.close();
    }
}
