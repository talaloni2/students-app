package com.example.studentsapp.activities;

import static com.example.studentsapp.model.Consts.STUDENT_POSITION_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

public class EditStudentActivity extends AppCompatActivity {

    private int studentPosition;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        assignExtras();
        assignStudent();
    }

    private void assignExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new RuntimeException("Got no student for student view!");
        }
        studentPosition = extras.getInt(STUDENT_POSITION_KEY);
    }

    private void assignStudent() {
        student = Students.getInstance().getStudent(studentPosition);
    }
}