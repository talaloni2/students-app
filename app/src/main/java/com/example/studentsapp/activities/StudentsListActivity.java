package com.example.studentsapp.activities;

import static com.example.studentsapp.model.Consts.STUDENT_POSITION_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.studentsapp.R;
import com.example.studentsapp.adapters.StudentListRowAdapter;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentsListActivity extends AppCompatActivity {
    RecyclerView studentsList;
    Button add;
    RecyclerView.LayoutManager layoutManager;
    StudentListRowAdapter adapter;
    AtomicInteger currentlyModifiedStudentPos = new AtomicInteger();
    AtomicBoolean isEditing = new AtomicBoolean(false);
    AtomicBoolean isAdding = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_list);

        studentsList = findViewById(R.id.studentslist_list);
        add = findViewById(R.id.studentslist_add);

        studentsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        studentsList.setLayoutManager(layoutManager);
        adapter = new StudentListRowAdapter(Students.getInstance().getStudents());
        studentsList.setAdapter(adapter);
        adapter.setOnItemClickListener(pos -> {
            isEditing.set(true);
            currentlyModifiedStudentPos.set(pos);
            Log.d("ItemClick", "Clicked item. Redirecting to view single activity");
            Intent intent = new Intent(getApplicationContext(), ViewStudentActivity.class);
            intent.putExtra(STUDENT_POSITION_KEY, pos);
            startActivity(intent);
        });
        add.setOnClickListener(view -> {
            isAdding.set(true);
            Students.getInstance().addStudent(new Student("dsds", "fdsf", true, "dddd", "dfsdfsdfsf"));
            adapter.notifyItemInserted(Students.getInstance().countStudents());
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isEditing.get()) {
            handleEdit();
        }

    }

    private void handleEdit() {
        int modifiedPosition = currentlyModifiedStudentPos.get();
        if (Students.getInstance().getStudent(modifiedPosition).isDeleted()) {
            adapter.notifyItemRemoved(modifiedPosition);
            Students.getInstance().removeStudent(modifiedPosition);
        }
        else adapter.notifyItemChanged(modifiedPosition);
        isEditing.set(false);
    }

    private void handleInsert() {
        isAdding.set(true);
    }
}