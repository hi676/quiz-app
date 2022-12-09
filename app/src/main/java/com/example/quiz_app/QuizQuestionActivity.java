package com.example.quiz_app;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.quiz_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity {

    // UI components here
    private RadioButton o1, o2, o3, o4;
    private Button next;
    private TextView tvQuestion, progressText;
    private RadioGroup options;
    private ProgressBar progress;

    // other variables here
    private ArrayList<QuestionModel> list;
    private QuestionModel currentQuestion;
    private String name;
    private int total;
    private int score = 0;
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        // create arraylist of questions
        list = new ArrayList<>();
        // get username intent from main activity screen
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        // initialize views using findViewByID
        tvQuestion = (TextView)findViewById(R.id.Question);
        tvQuestion.setText("When was the first Jeopardy game?");
        options = (RadioGroup)findViewById(R.id.Options);
        o1 = (RadioButton)findViewById(R.id.o1);
        o1.setText("1964");
        o2 = (RadioButton)findViewById(R.id.o2);
        o2.setText("1998");
        o3 = (RadioButton)findViewById(R.id.o3);
        o3.setText("1984");
        o4 = (RadioButton)findViewById(R.id.o4);
        o4.setText("2000");
        next = (Button)findViewById(R.id.Next);
        // use helper method to add question content to arraylist
        list.add(new QuestionModel("When was the first Jeopardy game?", "1964", "1998", "1984", "2000", 1));
        addQuestions();
        // get total number of questions
        total = list.size();
        // set progress bar
        progressText = findViewById(R.id.Progress_Bar);
        progressText.setText("Question 1");
        progress = findViewById(R.id.progressBar);
        progress.setProgress(0);
        progress.setMax(100);
        // use helper method to proceed to next question
        submitQuestion(list.get(index));
    }

    /**
     * Method that adds questions to our questions arraylist, using the Question Model constructor
     */
    private void addQuestions(){
        // question 2
        list.add(new QuestionModel("Who was the first host of Wheel of Fortune?", "Pat Sajak", "Chuck Woolery", "Vanna White", "Howie Mandel", 2));
        // question 3
        list.add(new QuestionModel("What was the first game show?", "Wheel of Fortune", "Jeopardy", "Truth or Consequences", "Price is Right", 3));
        // question 4
        list.add(new QuestionModel("When was Rutgers founded?", "1775", "1750", "1805", "1766", 4));
        // question 5
        list.add(new QuestionModel("Who won the most total money on Jeopardy?", "Jerome Vered", "Roger Craig", "James Holzhauer", "Frank Spangenberg", 3));
    }

    /**
     * Check the answer when user clicks submit and move on to next question
     */
    public void submitQuestion(QuestionModel question){
        // if no options have been selected, prompt user to select an answer
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (next.isPressed() && options.getCheckedRadioButtonId()==-1) {
                    Toast.makeText(getBaseContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                }
                else{
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
    }

    /**
     * Display next question. If finished, move onto results screen
     */
    private void showNextQuestion(){

        // clear previous button selections
        options.clearCheck();
        // if you haven't gone through all the questions yet
        // set the question & text to the next question
        index += 1;
        if(index != list.size()){
            tvQuestion.setText(list.get(index).getQuestion());
            o1.setText(list.get(index).getOpt1());
            o2.setText(list.get(index).getOpt2());
            o3.setText(list.get(index).getOpt3());
            o4.setText(list.get(index).getOpt4());
            progress.setProgress(index*100/list.size());
            progressText.setText("Question " + (index+1));
        }
        // if finished with quiz, start Results activity
        if(index == list.size()) {
            Intent intent = new Intent(QuizQuestionActivity.this, ResultActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("totalQuestions", String.valueOf(list.size()));
            intent.putExtra("score", String.valueOf(score));
            startActivity(intent);
            finish();
        }
    }

    /**
     * Checks the answer and increases score if correct
     */
    private void checkAnswer(){
        // see which answer they picked
        int option = -1;
        if(o1.isChecked())
        {
            option = 0;
        }
        else if(o2.isChecked())
        {
            option = 1;
        }
        else if(o3.isChecked())
        {
            option = 2;
        }
        else if(o4.isChecked())
        {
            option = 3;
        }
//        String str = String.valueOf(option) + " " + String.valueOf(list.get(index).getCorrectAnsNum());
//        Toast.makeText(getBaseContext(), str,Toast.LENGTH_SHORT).show();
        // increase score if correct
        if(option == list.get(index).getCorrectAnsNum()) {
            score++;
        }
    }
}