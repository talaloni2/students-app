package com.example.studentsapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

public class AddStudentActivity extends AppCompatActivity {

    private Student student;

    private EditText name;
    private EditText id;
    private EditText phone;
    private EditText address;

    private CheckBox checked;

    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        assignStudent();

        assignComponents();
        assignListeners();
    }

    private void assignStudent() {
        student = new Student("", "", false, "", "");
    }

    private void assignComponents() {
        name = findViewById(R.id.addstudent_namevalue);
        id = findViewById(R.id.addstudent_idvalue);
        phone = findViewById(R.id.addstudent_phonevalue);
        address = findViewById(R.id.addstudent_addressvalue);

        checked = findViewById(R.id.addstudent_ischecked);

        save = findViewById(R.id.addstudent_save);
        cancel = findViewById(R.id.addstudent_cancel);

        populateComponents();
    }

    private void populateComponents() {
        name.setText(student.getName());
        id.setText(student.getId());
        phone.setText(student.getPhone());
        address.setText(student.getAddress());

        checked.setChecked(student.isChecked());
    }

    private void assignListeners() {
        save.setOnClickListener(this::onSaveClick);
        cancel.setOnClickListener(view -> finish());
    }

    private void onSaveClick(View view) {
        student.setChecked(checked.isChecked());
        student.setAddress(address.getText().toString());
        student.setId(id.getText().toString());
        student.setName(name.getText().toString());
        student.setPhone(phone.getText().toString());

        Students.getInstance().addStudent(student);

        finish();
    }

}