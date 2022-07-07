package com.example.quiz_app;

import android.content.Intent;
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
        Button start = findViewById(R.id.Start);
        String name = "";
        // set name variable every time user clicks "start"
        if(start.isPressed()) {
            name = nameInput.getText().toString();
            // If the name field is empty, prompt user to enter name
            if(name.equals("")){
                name = nameInput.getText().toString();
                Toast.makeText(getBaseContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                startQuiz(nameInput);
            }
            else {
                Intent intent = new Intent(this, QuizQuestionActivity.class);
                intent.putExtra("namePrompt", name);
                startActivity(intent);
                finish(); // close current activity
            }
        }
    }
}