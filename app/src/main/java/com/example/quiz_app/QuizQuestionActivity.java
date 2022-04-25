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
    private TextView tvQuestion, tv_progress;
    private RadioButton o1, o2, o3, o4;
    private RadioGroup options;
    private ProgressBar progress;

    // other variables here
    private ArrayList<QuestionModel> list;
    private QuestionModel currentQuestion;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        // create arraylist of questions
        list = new ArrayList<>();
        // get username intent from main activity screen
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("enterName", name);
        // initialize views using findViewByID

        // use helper method to add question content to arraylist
        addQuestions();
        // get total number of questions
        total = list.size();
        // set progress bar
        ProgressBar progress = findViewById(R.id.progressBar);
        progress.setProgress(0);
        // use helper method to proceed to next question
        submitQuestion(views);
        showNextQuestion();
    }

    /**
     * Method that adds questions to our questions arraylist, using the Question Model constructor
     */
    private void addQuestions(){
        // question 1
        list.add(new QuestionModel("When was the first Jeopardy game?", "1964", "1998", "1984", "2000", 1));
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
    public void submitQuestion(View view){
        // if no options have been selected, prompt user to select an answer
        setContentView(view);

        // obtain user's name using findViewById
        group = findViewById(R.id.Options);
        next = findViewById(R.id.Next);
        if(next.callOnClick()) {
            if (group.getCheckedRadioButtonId() == -1) {
                next.setText("Do not leave the answers blank");
            }
        }
        // use helper methods to check the answer and show the next question
        checkAnswer();
    }

    /**
     * Display next question. If finished, move onto results screen
     */
    private void showNextQuestion(){

        // clear previous button selections
        group = null;
        // if you haven't gone through all the questions yet
        // set the question & text to the next question
        // increase question number
        // set progress bar
        TextView question = findViewById(R.id.Question);
        RadioButton option1 = findViewById(R.id.o1);
        RadioButton option2 = findViewById(R.id.o2);
        RadioButton option3 = findViewById(R.id.o3);
        RadioButton option4 = findViewById(R.id.o4);
        while(index != list.size()){
            question.setText(list.get(index+1).getQuestion());
            option1.setText(list.get(index+1).getOpt1());
            option2.setText(list.get(index+1).getOpt2());
            option3.setText(list.get(index+1).getOpt3());
            option4.setText(list.get(index+1).getOpt4());
            checkAnswer();
        }

        // if finished with quiz, start Results activity
        Button submit = findViewById(R.id.Next);
        if(index == list.size()-1){
            submit.setText("Results");
            Intent intent = new Intent(this, QuizQuestionActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            submit.setText("Submit");
        }
    }

    /**
     * Checks the answer and increases score if correct
     */
    private void checkAnswer(){
        // see which answer they picked
        RadioButton option1 = findViewById(R.id.o1);
        RadioButton option2 = findViewById(R.id.o2);
        RadioButton option3 = findViewById(R.id.o3);
        RadioButton option4 = findViewById(R.id.o4);
        int option = -1;
        if(option1.isChecked())
        {
            option = 1;
        }
        else if(option2.isChecked())
        {
            option = 2;
        }
        else if(option3.isChecked())
        {
            option = 3;
        }
        else if(option4.isChecked())
        {
            option = 4;
        }

        // increase score if correct
        if(option == list.get(index).getCorrectAnsNum()) {
                total++;
        }
        if(option == list.get(index).getCorrectAnsNum()) {
                total++;
        }
        if(option == list.get(index).getCorrectAnsNum()) {
                total++;
        }
        if(option == list.get(index).getCorrectAnsNum()) {
            total++;
        }
    }
}