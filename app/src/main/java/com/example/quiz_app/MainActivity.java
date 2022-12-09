package com.example.quiz_app;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import com.example.quiz_app.R;

public class MainActivity extends AppCompatActivity {

    // Put class variables up here
    private Bundle bundle;
    private EditText nameInput;
    private String name = "";
    private boolean bool;
    // Best practice is to make them private (can only be accessed within the class, or using getters/setters)
    // Each UI component that you want to reference needs a variable

    /**
     * Method used to start an activity. It's the first method to run when the
     * activity begins
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtain user's name using findViewById
        nameInput = findViewById(R.id.enterName);
        startQuiz(nameInput);
    }

    /**
     * Get username here and open Quiz Questions
     * @param view
     */
    public void startQuiz(View view){
        Button start = (Button)findViewById(R.id.Start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set name variable every time user clicks "start"
                if (start.isPressed()) {
                    bool = true;
                    name = nameInput.getText().toString();
                }
                if(bool == true) {
                    if (name.equals("")) {
                        name = nameInput.getText().toString();
                        Toast.makeText(getBaseContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                        startQuiz(nameInput);
                    } else {
                        Intent intent = new Intent(MainActivity.this, QuizQuestionActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        finish(); // close current activity
                    }
                }
            }
        });
    }
}