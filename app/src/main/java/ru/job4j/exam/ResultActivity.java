package ru.job4j.exam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);
        ArrayList<Integer> userAnswers = getIntent().getIntegerArrayListExtra("userAnswers");
        ArrayList<Question> questions = getIntent().getParcelableArrayListExtra("questions");
        TextView text = findViewById(R.id.results);
        text.setText("");
        for (int i = 0; i != userAnswers.size(); i++){
            String msg = userAnswers.get(i).toString();
            Question question = questions.get(i);
            text.append(question.toString());
            text.append("\n");
            text.append(String.format(" Your answer: %s", msg));
            text.append("\n");


        }
//        if(msg != null){
//            text.setText(msg);
//        }


    }
}
