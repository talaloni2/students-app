package com.example.studentsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsapp.R;
import com.example.studentsapp.adapters.viewholders.StudentRowViewHolder;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListRowAdapter extends RecyclerView.Adapter<StudentRowViewHolder> {
    List<Student> students;

    public StudentListRowAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.students_list_row, parent, false);
        return new StudentRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRowViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student, position);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
