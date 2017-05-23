package javase01.t02.EnumsAndGenerics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by m-levin on 13.05.2017.
 */
public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private static int studentsAmount;
    private HashMap<Group, Double> groupDoubleHashMap;

    Student(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentId = studentsAmount++;
        groupDoubleHashMap = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static int getStudentsAmount() {
        return studentsAmount;
    }

    public String getLastName() {
        return lastName;

    }

    public HashMap<Group, Double> getGroupDoubleHashMap() {
        return groupDoubleHashMap;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                Objects.equals(getFirstName(), student.getFirstName()) &&
                Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}

class StudentLastNameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}

class StudentFirstNameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}

class StudentIdCompartor implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getStudentId(), o2.getStudentId());
    }
}

class StudentRecord {

    private Student student;
    private double mark;

    StudentRecord(Student student, double mark) {
        this.student = student;
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return Double.compare(that.getMark(), getMark()) == 0 &&
                Objects.equals(getStudent(), that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getMark());
    }

    @Override
    public String toString() {
        return student + " " + mark;
    }
}

class StudentRecordMarkComparator implements Comparator<StudentRecord> {

    @Override
    public int compare(StudentRecord o1, StudentRecord o2) {
        return -Double.compare(o1.getMark(), o2.getMark());
    }
}

class StudentRecordLastNameComparator implements Comparator<StudentRecord> {

    @Override
    public int compare(StudentRecord o1, StudentRecord o2) {
        return o1.getStudent().getLastName().compareTo(o2.getStudent().getLastName());
    }
}

class StudentRecordFirstNameComparator implements Comparator<StudentRecord> {

    @Override
    public int compare(StudentRecord o1, StudentRecord o2) {
        return o1.getStudent().getFirstName().compareTo(o2.getStudent().getFirstName());
    }
}

class StudentRecordIdComparator implements Comparator<StudentRecord> {

    @Override
    public int compare(StudentRecord o1, StudentRecord o2) {
        return Integer.compare(o1.getStudent().getStudentId(), o2.getStudent().getStudentId());
    }
}



