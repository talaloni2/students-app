package com.example.studentsapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

public class ViewStudentActivity extends AppCompatActivity {

    private TextView id;
    private TextView name;
    private TextView phone;
    private TextView address;
    private CheckBox isChecked;
    private Button edit;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        assignStudent();

        initializeComponents();

        setListeners();

    }

    private void assignStudent() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new RuntimeException("Got no student for student view!");
        }
        student = Students.getInstance().getStudent(extras.getInt("studentPosition"));
    }

    private void setListeners() {
        edit.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), )
        });
    }

    private void initializeComponents() {
        id = findViewById(R.id.viewstudent_idvalue);
        name = findViewById(R.id.viewstudent_namevalue);
        phone = findViewById(R.id.viewstudent_phonevalue);
        address = findViewById(R.id.viewstudent_addressvalue);
        isChecked = findViewById(R.id.viewstudent_ischecked);
        edit = findViewById(R.id.viewstudent_edit);
        isChecked.setEnabled(false);
        populateComponents();
    }

    private void populateComponents() {
        id.setText(student.getId());
        name.setText(student.getName());
        phone.setText(student.getPhone());
        address.setText(student.getAddress());
        isChecked.setChecked(student.isChecked());
    }
}