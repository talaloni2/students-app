package com.example.studentsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.studentsapp.R;
import com.example.studentsapp.adapters.StudentListRowAdapter;
import com.example.studentsapp.model.db.Students;

import java.util.concurrent.atomic.AtomicInteger;

public class StudentsListActivity extends AppCompatActivity {
    RecyclerView studentsList;
    RecyclerView.LayoutManager layoutManager;
    StudentListRowAdapter adapter;
    AtomicInteger currentlyModifiedStudentPos = new AtomicInteger();

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
        adapter.setOnItemClickListener(pos -> {
            currentlyModifiedStudentPos.set(pos);
            Log.d("ItemClick", "Clicked item. Redirecting to view single activity");
            Intent intent = new Intent(getApplicationContext(), ViewStudentActivity.class);
            intent.putExtra("studentPosition", pos);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyItemChanged(currentlyModifiedStudentPos.get());
    }
}