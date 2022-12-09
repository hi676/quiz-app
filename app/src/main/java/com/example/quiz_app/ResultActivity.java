package com.example.quiz_app;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    // UI component variables
    private TextView percentage;
    private TextView fraction;
    private Button playagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // initialize UI components
        percentage = (TextView)findViewById(R.id.Percentage);
        fraction = (TextView)findViewById(R.id.Fraction);
        playagain = (Button)findViewById(R.id.PlayAgain);
        // set username and score
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String totalquestions = intent.getStringExtra("totalQuestions");
        String score = intent.getStringExtra("score");
        String fractStr = "You answered " + score + "/" + totalquestions + " right";
        String percStr = "You got " + String.format("%.2f", (Integer.parseInt(score)/Double.parseDouble(totalquestions))*100) + "%";;
        percentage.setText(percStr);
        fraction.setText(fractStr);
        restart(playagain);
    }

    /**
     * Restarts the quiz so you can play another round
     * @param view
     */
    public void restart(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set name variable every time user clicks "start"
                if (view.isPressed()) {
                    Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}