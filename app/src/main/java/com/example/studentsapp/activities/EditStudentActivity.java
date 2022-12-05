package com.example.studentsapp.activities;

import static com.example.studentsapp.model.Consts.IS_ADDING_STUDENT_KEY;
import static com.example.studentsapp.model.Consts.STUDENT_POSITION_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

public class EditStudentActivity extends AppCompatActivity {

    private int studentPosition;
    private Student student;
    boolean isAdding;

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
        isAdding = extras.getBoolean(IS_ADDING_STUDENT_KEY, false);
    }

    private void assignStudent() {
        if (isAdding){
            student = new Student("", "", false, "", "");
        }
        else student = Students.getInstance().getStudent(studentPosition);
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

        if(isAdding){
            setTitle("Add Student");
            modifyLayoutForAddition();
        }

        populateComponents();
    }

    private void modifyLayoutForAddition() {
        ConstraintLayout constraintLayout = findViewById(R.id.editstudent_mainlayout);
        ConstraintSet constraints = new ConstraintSet();
        constraints.clone(constraintLayout);
        constraints.removeFromHorizontalChain(R.id.editstudent_delete);
        constraints.removeFromHorizontalChain(R.id.editstudent_cancel);
        constraints.removeFromHorizontalChain(R.id.editstudent_save);
        ((ViewGroup)delete.getParent()).removeView(delete);

        constraints.connect(R.id.editstudent_cancel, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        constraints.connect(R.id.editstudent_save, ConstraintSet.LEFT, R.id.editstudent_cancel, ConstraintSet.RIGHT);
        constraints.connect(R.id.editstudent_save, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);

        int[] viewIds = new int[]{R.id.editstudent_cancel, R.id.editstudent_save};
        constraints.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, viewIds, null, ConstraintSet.CHAIN_SPREAD);

        constraints.applyTo(constraintLayout);
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

        if(isAdding){
            Students.getInstance().addStudent(student);
        }

        finish();
    }

    private void onDeleteClick(View view) {
        student.setDeleted(true);
        finish();
    }
}