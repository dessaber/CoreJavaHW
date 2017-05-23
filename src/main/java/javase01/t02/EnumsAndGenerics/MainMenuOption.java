package javase01.t02.EnumsAndGenerics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by m-levin on 13.05.2017.
 */
public interface MainMenuOption {

    public void chooseOption() throws IOException, NumberFormatException;

}

class ChoosingShowGroups implements MainMenuOption {

    @Override
    public void chooseOption() {
        if (SubjectsApp.groupTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No groups exist!");
            System.out.println();
            return;
        } else {
            System.out.println("All registered groups:");
            int i = 0;
            for (Group group : SubjectsApp.groupTreeSet) {
                System.out.println(i + ". " + group);
                i++;
            }
            System.out.println();
        }
    }
}

class ChoosingShowStudents implements MainMenuOption {

    @Override
    public void chooseOption() {
        if (SubjectsApp.studentTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No students exist!");
            System.out.println();
            return;
        } else {
            System.out.println("All registered students:");
            int i = 0;
            for (Student student : SubjectsApp.studentTreeSet) {
                System.out.println(i + ". " + student);
                i++;
            }
            System.out.println();
        }
    }
}

class ChoosingRemoveGroup implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {
        if (SubjectsApp.groupTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No groups exist!");
            System.out.println();
            return;
        } else {
            new ChoosingShowGroups().chooseOption();
            System.out.println("Which group would You like to remove?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            ArrayList<Group> arrayList = new ArrayList<>(SubjectsApp.groupTreeSet);
            while (index < 0 || index >= arrayList.size()) {
                System.out.println("Wrong index!");
                index = Integer.parseInt(reader.readLine());
            }
            Group groupToRemove = arrayList.get(index);
            for (Map.Entry e : groupToRemove.getStudentDoubleHashMap().entrySet()) {
                Student student = (Student) e.getKey();
                if (student.getGroupDoubleHashMap().containsKey(groupToRemove))
                    student.getGroupDoubleHashMap().remove(groupToRemove);
            }
            SubjectsApp.groupTreeSet.remove(groupToRemove);
        }
    }
}

class ChoosingAssignStudent implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {
        if (SubjectsApp.groupTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No groups exist!");
            System.out.println();
            return;
        } else if (SubjectsApp.studentTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No students exist!");
            System.out.println();
        } else {
            new ChoosingShowStudents().chooseOption();
            System.out.println("Which student do you choose?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            ArrayList<Student> studentTreeSet = new ArrayList<>(SubjectsApp.studentTreeSet);
            while (index < 0 || index >= studentTreeSet.size()) {
                System.out.println("Wrong index!");
                index = Integer.parseInt(reader.readLine());
            }
            Student studentToAssign = studentTreeSet.get(index);
            System.out.println("Which group will this student be assigned to?");
            new ChoosingShowGroups().chooseOption();
            ArrayList<Group> groupTreeSet = new ArrayList<>(SubjectsApp.groupTreeSet);
            index = Integer.parseInt(reader.readLine());
            while (index < 0 || index >= groupTreeSet.size()) {
                System.out.println("Wrong index!");
                index = Integer.parseInt(reader.readLine());
            }
            Group groupToExpand = groupTreeSet.get(index);
            System.out.println("Input the student's mark for this group.");
            String markFormat = groupToExpand.isReal() ? "Note that marks for this group are real numbers." : "Note that marks for this group are integer numbers (fraction will be lost).";
            System.out.println(markFormat);
            double mark = groupToExpand.isReal() ? Double.parseDouble(reader.readLine()) : (double) (int) Double.parseDouble(reader.readLine());
            groupToExpand.addStudent(studentToAssign, mark);
            studentToAssign.getGroupDoubleHashMap().put(groupToExpand, mark);
        }
    }
}

class ChoosingAddGroup implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {
        System.out.println("Choose a subject for new group.");
        System.out.println("Here's the list of available options:");
        for (int i = 0; i < SubjectsApp.Subject.values().length; i++)
            System.out.println(i + ". " + SubjectsApp.Subject.values()[i]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int groupIndex = Integer.parseInt(reader.readLine());
        while (groupIndex < 0 || groupIndex >= SubjectsApp.Subject.values().length) {
            System.out.println("Wrong index!");
            groupIndex = Integer.parseInt(reader.readLine());
        }
        System.out.println("Choose format of marks for this group:");
        System.out.println("1 - real;");
        System.out.println("2 - integer. Marks will be shown as real numbers, but during input phase fraction will be lost.");
        int markIndex = Integer.parseInt(reader.readLine());
        while (markIndex < 1 || markIndex > 2) {
            System.out.println("Wrong option!");
            markIndex = Integer.parseInt(reader.readLine());
        }
        Group groupToAdd = new Group(SubjectsApp.Subject.values()[groupIndex]);
        if (markIndex == 1)
            groupToAdd.setReal(true);
        SubjectsApp.groupTreeSet.add(groupToAdd);
    }
}

class ChoosingAddStudent implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {
        System.out.println("Input new student's last name:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName = reader.readLine().trim();
        System.out.println("Input new student's first name:");
        String firstName = reader.readLine().trim();
        SubjectsApp.studentTreeSet.add(new Student(lastName, firstName));
    }
}

class ChoosingRemoveStudentFromGroup implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {

        if (SubjectsApp.groupTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No groups exist!");
            System.out.println();
            return;
        } else if (SubjectsApp.studentTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No students exist!");
            System.out.println();
            return;
        } else {
            new ChoosingShowGroups().chooseOption();
            System.out.println("Which group would you like to modify?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int groupIndex = Integer.parseInt(reader.readLine());
            ArrayList<Group> arrayList = new ArrayList<>(SubjectsApp.groupTreeSet);
            while (groupIndex < 0 || groupIndex >= arrayList.size()) {
                System.out.println("Wrong index!");
                groupIndex = Integer.parseInt(reader.readLine());
            }
            Group groupToModify = arrayList.get(groupIndex);
            if (groupToModify.getStudentDoubleHashMap().size() == 0)
                System.out.println("No students assigned!");
            else {
                System.out.println("Here's the list of all students of the group:");
                ArrayList<Student> studentTreeSet = new ArrayList<>();
                int i = 0;
                for (Map.Entry e : groupToModify.getStudentDoubleHashMap().entrySet()) {
                    Student student = (Student) e.getKey();
                    studentTreeSet.add(student);
                    System.out.println(i + ". " + student);
                    i++;
                }
                System.out.println("Which student would you like to remove?");
                int studentIndex = Integer.parseInt(reader.readLine());
                while (studentIndex < 0 || studentIndex >= studentTreeSet.size()) {
                    System.out.println("Wrong index!");
                    studentIndex = Integer.parseInt(reader.readLine());
                }
                Student studentToRemove = studentTreeSet.get(studentIndex);
                studentToRemove.getGroupDoubleHashMap().remove(groupToModify);
                groupToModify.getStudentDoubleHashMap().remove(studentToRemove);
            }
        }
    }
}

class ChoosingRemoveStudent implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {

        if (SubjectsApp.studentTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No students exist!");
            System.out.println();
            return;
        } else {
            new ChoosingShowStudents().chooseOption();
            System.out.println("Which student would you like to remove?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            ArrayList<Student> arrayList = new ArrayList<>(SubjectsApp.studentTreeSet);
            while (index < 0 || index >= arrayList.size()) {
                System.out.println("Wrong index!");
                index = Integer.parseInt(reader.readLine());
            }
            Student studentToRemove = arrayList.get(index);
            for (Group group : studentToRemove.getGroupDoubleHashMap().keySet()) {
                group.getStudentDoubleHashMap().remove(studentToRemove);
            }
            SubjectsApp.studentTreeSet.remove(studentToRemove);
        }
    }
}

class ChoosingShowGroupInfo implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {

        if (SubjectsApp.groupTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No groups exist!");
            System.out.println();
            return;
        }  else if (SubjectsApp.studentTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No students exist!");
            System.out.println();
            return;
        } else {
            new ChoosingShowGroups().chooseOption();
            System.out.println("Which group would you like to know more about?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            ArrayList<Group> arrayList = new ArrayList<>(SubjectsApp.groupTreeSet);
            while (index < 0 || index >= arrayList.size()) {
                System.out.println("Wrong index!");
                index = Integer.parseInt(reader.readLine());
            }
            if (arrayList.get(index).getStudentDoubleHashMap().size() == 0)
                System.out.println("No students assigned!");
            else {
                TreeSet<StudentRecord> studentRecordTreeSet = new TreeSet<>(new StudentRecordMarkComparator().thenComparing(new StudentRecordLastNameComparator())
                                                                                                             .thenComparing(new StudentRecordFirstNameComparator())
                                                                                                             .thenComparing(new StudentRecordIdComparator()));
                for (Map.Entry e : arrayList.get(index).getStudentDoubleHashMap().entrySet()) {
                    studentRecordTreeSet.add(new StudentRecord((Student) e.getKey(), (Double) e.getValue()));
                }
                for (StudentRecord studentRecord : studentRecordTreeSet)
                    System.out.println(studentRecord);
            }
            System.out.println();
        }
    }
}

class ChoosingShowStudentInfo implements MainMenuOption {

    @Override
    public void chooseOption() throws IOException, NumberFormatException {

        if (SubjectsApp.groupTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No groups exist!");
            System.out.println();
            return;
        }  else if (SubjectsApp.studentTreeSet.size() == 0) {
            System.out.println();
            System.out.println("No students exist!");
            System.out.println();
            return;
        } else {
            new ChoosingShowStudents().chooseOption();
            System.out.println("Which student would you like to know more about?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            ArrayList<Student> arrayList = new ArrayList<>(SubjectsApp.studentTreeSet);
            while (index < 0 || index >= arrayList.size()) {
                System.out.println("Wrong index!");
                index = Integer.parseInt(reader.readLine());
            }
            if (arrayList.get(index).getGroupDoubleHashMap().size() == 0)
                System.out.println("No groups assigned!");
            else {
                TreeSet<GroupRecord> groupRecordTreeSet = new TreeSet<>(new GroupRecordMarkComparator().thenComparing(new GroupRecordGroupComparator()));
                for (Map.Entry e : arrayList.get(index).getGroupDoubleHashMap().entrySet()) {
                    groupRecordTreeSet.add(new GroupRecord((Group) e.getKey(), (Double) e.getValue()));
                }
                for (GroupRecord groupRecord : groupRecordTreeSet)
                    System.out.println(groupRecord);
            }
            System.out.println();
        }
    }
}
