package com.example.studentsapp.activities;

import static com.example.studentsapp.model.Consts.STUDENT_POSITION_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

public class EditStudentActivity extends AppCompatActivity {

    private int studentPosition;
    private Student student;

    private EditText name;
    private EditText id;
    private EditText phone;
    private EditText address;

    private CheckBox checked;

    private Button save;
    private Button cancel;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        assignExtras();
        assignStudent();

        assignComponents();
        assignListeners();
    }

    private void assignExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new RuntimeException("Got no student for student edit!");
        }
        studentPosition = extras.getInt(STUDENT_POSITION_KEY);
    }

    private void assignStudent() {
        student = Students.getInstance().getStudent(studentPosition);
    }

    private void assignComponents() {
        name = findViewById(R.id.editstudent_namevalue);
        id = findViewById(R.id.editstudent_idvalue);
        phone = findViewById(R.id.editstudent_phonevalue);
        address = findViewById(R.id.editstudent_addressvalue);

        checked = findViewById(R.id.editstudent_ischecked);

        save = findViewById(R.id.editstudent_save);
        cancel = findViewById(R.id.editstudent_cancel);
        delete = findViewById(R.id.editstudent_delete);
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
        delete.setOnClickListener(this::onDeleteClick);
        cancel.setOnClickListener(view -> finish());
    }

    private void onSaveClick(View view) {
        student.setChecked(checked.isChecked());
        student.setAddress(address.getText().toString());
        student.setId(id.getText().toString());
        student.setName(name.getText().toString());
        student.setPhone(phone.getText().toString());
        finish();
    }

    private void onDeleteClick(View view) {
        student.setDeleted(true);
        finish();
    }
}