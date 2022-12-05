package com.example.studentsapp.adapters.viewholders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsapp.R;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.db.Students;

public class StudentRowViewHolder extends RecyclerView.ViewHolder {
    private TextView studentName;
    private TextView studentId;
    private CheckBox checked;

    public StudentRowViewHolder(@NonNull View itemView) {
        super(itemView);
        studentId = itemView.findViewById(R.id.studentlistrow_idtv);
        studentName = itemView.findViewById(R.id.studentlistrow_nametv);
        checked = itemView.findViewById(R.id.studentlistrow_cb);

        checked.setOnClickListener(view -> {
            int pos = (int)checked.getTag();
            Student student = Students.getInstance().getStudent(pos);
            student.setChecked(checked.isChecked());
        });
    }


    public void bind(Student student, int position) {
        studentId.setText(student.getId());
        studentName.setText(student.getName());
        checked.setChecked(student.isChecked());
        checked.setTag(position);
    }

    public TextView getStudentName() {
        return studentName;
    }

    public void setStudentName(TextView studentName) {
        this.studentName = studentName;
    }

    public TextView getStudentId() {
        return studentId;
    }

    public void setStudentId(TextView studentId) {
        this.studentId = studentId;
    }

    public CheckBox getChecked() {
        return checked;
    }

    public void setChecked(CheckBox checked) {
        this.checked = checked;
    }
}
