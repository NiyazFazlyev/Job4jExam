package ru.job4j.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity
        implements ConfirmDeleteDialogFragment.ConfirmDeleteDialogListener {

    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                ExamAdapter adapter = (ExamAdapter) this.recycler.getAdapter();
                int index = adapter.getItemCount();
                adapter.addExam(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
                adapter.notifyItemInserted(index);
                return true;
            case R.id.delete_item:
                DialogFragment dialog = new ConfirmDeleteDialogFragment();
                dialog.show(getSupportFragmentManager(), "delete_dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        this.recycler.setAdapter(new ExamAdapter(new ArrayList<>()));
    }

    public class ExamAdapter extends RecyclerView.Adapter<ExamHolder> {
        private final List<Exam> exams;

        public ExamAdapter(List<Exam> exams) {
            this.exams = exams;
        }

        public void addExam(Exam exam) {
            this.exams.add(exam);
        }

        @NonNull
        @Override
        public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }


        @Override
        public int getItemCount() {
            return this.exams.size();
        }


        @Override
        public void onBindViewHolder(@NonNull ExamHolder holder, int i) {
            final Exam exam = this.exams.get(i);
            TextView info = holder.view.findViewById(R.id.info);
            info.setText(exam.getName());
            TextView result = holder.view.findViewById(R.id.result);
            result.setText(String.valueOf(exam.getResult()));
            TextView date = holder.view.findViewById(R.id.date);
            date.setText(String.valueOf(exam.getTime()));
            info.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ExamActivity.this, MainActivity.class);
                            startActivity(intent);
//                            Toast.makeText(
//                                    getApplicationContext(), "You select " + exam,
//                                    Toast.LENGTH_SHORT
//                            ).show();
                        }
                    }
            );
        }

    }

    public class ExamHolder extends RecyclerView.ViewHolder {
        private View view;

        public ExamHolder(@NonNull View view) {
            super(view);
            this.view = itemView;
        }
    }

    private void updateUI() {
        List<Exam> exams = new ArrayList<>();
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        this.recycler.setAdapter(new ExamAdapter(exams));
    }


}
