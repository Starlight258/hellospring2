package tobyspring.hellospring.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Student {
    private int age;
    private String name;
    private double grade;

    public Student(int age, String name, double grade) {
        this.age = age;
        this.name = name;
        this.grade = grade;
    }

    public int getAge() { return age; }
    public String getName() { return name; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return "Student{age=" + age + ", name='" + name + "', grade=" + grade + '}';
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(20, "Kim", 3.5),
                new Student(20, "Lee", 3.7),
                new Student(19, "Park", 3.2),
                new Student(20, "Choi", 3.6)
        );

        // 방법 1: reversed() 사용
        Comparator<Student> comparator1 = Comparator
                .comparing(Student::getAge)
                .thenComparing(Student::getName)
                .reversed() // 전체 비교 로직을 뒤집음
                .thenComparing(Student::getGrade);

        System.out.println("방법 1: reversed() 사용 (전체 비교 로직 뒤집기)");
        students.stream()
                .sorted(comparator1)
                .forEach(System.out::println);

        // 방법 2: 각 필드별로 reverseOrder() 사용
        Comparator<Student> comparator2 = Comparator
                .comparing(Student::getAge)
                .thenComparing(Student::getName, Comparator.reverseOrder())
                .thenComparing(Student::getGrade);

        System.out.println("\n방법 2: reverseOrder() 사용 (각 필드 개별 역순)");
        students.stream()
                .sorted(comparator2)
                .forEach(System.out::println);
    }
}
