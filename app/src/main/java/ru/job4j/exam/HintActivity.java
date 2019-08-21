package ru.job4j.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HintActivity extends AppCompatActivity {
    private final Map<Integer, String> hints = new HashMap<>();

    public HintActivity() {
        this.hints.put(0, "Hint 1");
        this.hints.put(1, "Hint 2");
        this.hints.put(2, "Hint 3");
    }


    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.hint_activity);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
        TextView text = findViewById(R.id.hint);
        ArrayList<Question> questions = getIntent().getParcelableArrayListExtra("questions");
        int position = getIntent().getIntExtra(MainActivity.HINT_FOR, 0);
        text.setText(questions.get(position).getText());
        text.append("\n");
        text.append(this.hints.get(position));
    }

    private void backBtn(View view) {
        onBackPressed();
    }

}
