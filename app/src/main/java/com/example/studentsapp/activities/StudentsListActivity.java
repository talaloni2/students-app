package com.example.studentsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.studentsapp.R;
import com.example.studentsapp.adapters.StudentListRowAdapter;
import com.example.studentsapp.model.db.Students;

public class StudentsListActivity extends AppCompatActivity {
    RecyclerView studentsList;
    RecyclerView.LayoutManager layoutManager;
    StudentListRowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_list);

        studentsList = findViewById(R.id.studentslist_list);

        studentsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        studentsList.setLayoutManager(layoutManager);
        adapter = new StudentListRowAdapter(Students.getInstance().getStudents());
        studentsList.setAdapter(adapter);
    }
}