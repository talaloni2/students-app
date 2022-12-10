package com.example.studentsapp.activities;

import static com.example.studentsapp.model.Consts.STUDENT_POSITION_KEY;

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
    int studentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        assignExtras();
        assignStudent();

        initializeComponents();

        setListeners();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (student.isDeleted()) {
            finish();
        }

        populateComponents();
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

    private void setListeners() {
        isChecked.setOnClickListener(view -> student.setChecked(isChecked.isChecked()));
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EditStudentActivity.class);
            intent.putExtra(STUDENT_POSITION_KEY, studentPosition);
            startActivity(intent);
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