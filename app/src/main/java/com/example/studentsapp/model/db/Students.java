package com.example.studentsapp.model.db;

import com.example.studentsapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private static final Students instance = new Students();

    private List<Student> students;

    private Students() {
        students = new ArrayList<>();
        for(int i=0; i<20; i++) {
            students.add(new Student("id"+i, "Charmander"+i, false));
        }
    }

    public static Students getInstance() {
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(int pos) {
        return students.get(pos);
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
