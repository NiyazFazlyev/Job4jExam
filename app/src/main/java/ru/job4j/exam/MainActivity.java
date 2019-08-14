package ru.job4j.exam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int count = 0;
    private int position = 0;
    public static final String HINT_FOR = "hint_for";
    List<Integer> userAnswers = new ArrayList<>();
    List<Integer> correctAnswers = new ArrayList<>();

    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        findViewById(R.id.next).setEnabled(false);
        final TextView text = findViewById(R.id.question);
        Question question = this.questions.get(position);
        text.setText(question.getText());
        RadioGroup variants = findViewById(R.id.variants);
        variants.clearCheck();
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = questions.get(position);
        userAnswers.add(position, id);
        Toast.makeText(this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fillForm();
        RadioGroup variants = findViewById(R.id.variants);
        variants.setOnCheckedChangeListener(this::changeRadioGroup);
        Button next = findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);
        Button hint = findViewById(R.id.hint);
        hint.setOnClickListener(this::hintBtn);
        //        Log.d(TAG, "onCreate");
    }

    private void nextBtn(View view){
        //showAnswer();
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        userAnswers.add(position, id);
        position++;
        if (position == questions.size()){
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putIntegerArrayListExtra("userAnswers", (ArrayList<Integer>) userAnswers);
            intent.putParcelableArrayListExtra("questions", new ArrayList<>(questions));
            startActivity(intent);
        }else{
            fillForm();
        }

    }

    private void previousBtn(View view){
        position--;
        fillForm();
    }

    private void  hintBtn(View view){
//        startActivity(new Intent(MainActivity.this, HintActivity.class));
        Intent intent = new Intent(MainActivity.this, HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        intent.putParcelableArrayListExtra("questions", new ArrayList<>(questions));
        startActivity(intent);
    }

    private void changeRadioGroup(RadioGroup radioGroup, int i){
        Button next = findViewById(R.id.next);
        next.setEnabled(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        count++;
        Log.d(TAG, String.valueOf(count));

    }


}
