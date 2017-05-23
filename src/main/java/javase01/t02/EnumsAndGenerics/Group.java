package javase01.t02.EnumsAndGenerics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by m-levin on 13.05.2017.
 */
public class Group {

    private static int groupsAmount;
    private int groupId;
    private SubjectsApp.Subject subject;
    private boolean isRealMarks;
    private HashMap<Student, Double> studentDoubleHashMap;

    public Group(SubjectsApp.Subject subject) {
        this.subject = subject;
        this.groupId = groupsAmount++;
        studentDoubleHashMap = new HashMap<>();
    }

    Group(SubjectsApp.Subject subject, boolean isReal) {
        this.subject = subject;
        this.groupId = groupsAmount++;
        studentDoubleHashMap = new HashMap<>();
    }

    public void setSubject(SubjectsApp.Subject subject) {
        this.subject = subject;
    }

    public void setReal(boolean real) {
        isRealMarks = real;
    }

    public static int getGroupsAmount() {
        return groupsAmount;
    }

    public int getGroupId() {
        return groupId;
    }

    public SubjectsApp.Subject getSubject() {
        return subject;
    }

    public boolean isReal() {
        return isRealMarks;
    }

    public HashMap<Student, Double> getStudentDoubleHashMap() {
        return studentDoubleHashMap;
    }

    public void addStudent(Student student, double mark) {
        if (studentDoubleHashMap.containsKey(student)) {
            System.out.println("This student is already assigned to this group!");
            return;
        }
        studentDoubleHashMap.put(student, mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return getGroupId() == group.getGroupId() &&
                isReal() == group.isReal() &&
                getSubject() == group.getSubject();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getSubject(), isReal());
    }

    @Override
    public String toString() {
        String format = isRealMarks ? "(real marks)" : "(integer marks)";
        return "" + subject + "#" + groupId + " " + format;
    }
}

class GroupNameComparator implements Comparator<Group> {

    @Override
    public int compare(Group o1, Group o2) {
        return o1.toString().compareTo(o2.toString());
    }
}

class GroupRecord {

    private Group group;
    private double mark;

    public GroupRecord(Group group, double mark) {
        this.group = group;
        this.mark = mark;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        GroupRecord that = (GroupRecord) o;
        return Double.compare(that.getMark(), getMark()) == 0 &&
                Objects.equals(getGroup(), that.getGroup());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroup(), getMark());
    }

    @Override
    public String toString() {
        return "" + group.getSubject() + "#" + group.getGroupId() + " " + mark + ";";
    }
}

class GroupRecordGroupComparator implements Comparator<GroupRecord> {

    @Override
    public int compare(GroupRecord o1, GroupRecord o2) {
        return o1.getGroup().toString().compareTo(o2.getGroup().toString());
    }
}

class GroupRecordMarkComparator implements Comparator<GroupRecord> {

    @Override
    public int compare(GroupRecord o1, GroupRecord o2) {
        return -Double.compare(o1.getMark(), o2.getMark());
    }
}