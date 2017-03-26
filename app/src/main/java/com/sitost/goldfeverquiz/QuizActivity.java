package com.sitost.goldfeverquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = ".MESSAGE";


    // Number of correct answers counter
    String finalMessage = " ";
    int correctAnswers = 0;
    String isQuestion1Correct = " ";
    String isQuestion2Correct = " ";
    String isQuestion3Correct = " ";
    String isQuestion4Correct = " ";
    String isQuestion5Correct = " ";
    String isQuestion6Correct = " ";
    String isQuestion7Correct = " ";


    // Methods to save the variable values when the screen is rotated.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            correctAnswers = savedInstanceState.getInt("correctAnswers");
        }


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.playerName);
        textView.setText(message);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("correctAnswers", correctAnswers);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        correctAnswers = savedInstanceState.getInt("correctAnswers");
    }


    /**
     * Called when the user taps the Results button
     */
    public void showResults(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ResultActivity.class);
        TextView textView = (TextView) findViewById(R.id.playerName);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("CORRECT_ANSWERS", correctAnswers);
        startActivity(intent);
    }


    /**
     * Called when the user taps the Check your answers button
     */
    public void checkAnswers(View view) {
        // Do something in response to button
        RadioButton radioButton11 = (RadioButton) findViewById(R.id.a11);
        RadioButton radioButton12 = (RadioButton) findViewById(R.id.a12);
        RadioButton radioButton13 = (RadioButton) findViewById(R.id.a13);
        if (radioButton11.isChecked()) {
            correctAnswers += 1;
            isQuestion1Correct = getString(R.string.correct);
        } else if (radioButton12.isChecked() || radioButton13.isChecked()) {
            isQuestion1Correct = getString(R.string.incorrect);
        } else {
            isQuestion1Correct = getString(R.string.no_answer);
        }

        //Check if the second question is correct/incorrect, or is there no answer.
        RadioButton radioButton21 = (RadioButton) findViewById(R.id.a21);
        RadioButton radioButton22 = (RadioButton) findViewById(R.id.a22);
        RadioButton radioButton23 = (RadioButton) findViewById(R.id.a23);
        if (radioButton23.isChecked()) {
            correctAnswers += 1;
            isQuestion2Correct = getString(R.string.correct);
        } else if (radioButton21.isChecked() || radioButton22.isChecked()) {
            isQuestion2Correct = getString(R.string.incorrect);
        } else {
            isQuestion2Correct = getString(R.string.no_answer);
        }

        //Check if the third question is correct/incorrect, or is there no answer.
        RadioButton radioButton31 = (RadioButton) findViewById(R.id.a31);
        RadioButton radioButton32 = (RadioButton) findViewById(R.id.a32);
        RadioButton radioButton33 = (RadioButton) findViewById(R.id.a33);
        if (radioButton32.isChecked()) {
            correctAnswers += 1;
            isQuestion3Correct = getString(R.string.correct);
        } else if (radioButton31.isChecked() || radioButton33.isChecked()) {
            isQuestion3Correct = getString(R.string.incorrect);
        } else {
            isQuestion3Correct = getString(R.string.no_answer);
        }

        //Check if the fourth question is correct/incorrect, or is there no answer.
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.a41);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.a42);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.a43);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.a44);
        if (!checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && !checkBox4.isChecked()) {
            correctAnswers += 1;
            isQuestion4Correct = getString(R.string.correct);
        } else if (!checkBox1.isChecked() && !checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked()) {
            isQuestion4Correct = getString(R.string.no_answer);
        } else {
            isQuestion4Correct = getString(R.string.incorrect);
        }


        //Check if the fifth question is correct/incorrect, or is there no answer.
        RadioButton radioButton51 = (RadioButton) findViewById(R.id.a51);
        RadioButton radioButton52 = (RadioButton) findViewById(R.id.a52);

        if (radioButton51.isChecked()) {
            correctAnswers += 1;
            isQuestion5Correct = getString(R.string.correct);
        } else if (radioButton52.isChecked()) {
            isQuestion5Correct = getString(R.string.incorrect);
        } else {
            isQuestion5Correct = getString(R.string.no_answer);
        }

        //Check if the sixth question is correct/incorrect, or is there no answer.
        EditText editText = (EditText) findViewById(R.id.carat);
        if (editText.getText().toString().equals("24")) {
            correctAnswers += 1;
            isQuestion6Correct = getString(R.string.correct);
        } else if (editText.getText().toString().isEmpty()) {
            isQuestion6Correct = getString(R.string.no_answer);
        } else {
            isQuestion6Correct = getString(R.string.incorrect);
        }
        //Check if the seventh question is correct/incorrect, or is there no answer.
        RadioButton radioButton71 = (RadioButton) findViewById(R.id.a71);
        RadioButton radioButton72 = (RadioButton) findViewById(R.id.a72);
        RadioButton radioButton73 = (RadioButton) findViewById(R.id.a73);
        if (radioButton71.isChecked()) {
            correctAnswers += 1;
            isQuestion7Correct = getString(R.string.correct);
        } else if (radioButton72.isChecked() || radioButton73.isChecked()) {
            isQuestion7Correct = getString(R.string.incorrect);
        } else {
            isQuestion7Correct = getString(R.string.no_answer);
        }

        Button results = (Button) findViewById(R.id.button_results);
        results.setEnabled(true);

        Button answers = (Button) findViewById(R.id.button_check);
        answers.setEnabled(false);

        setMessage();
        displayMessage();

    }


    //Sets the final toast message
    public void setMessage() {
        finalMessage += getString(R.string.correct_answers) + " " + correctAnswers + "/7";
        finalMessage += "\n\n" + getString(R.string.your_answers);
        finalMessage += "\n1)" + isQuestion1Correct;
        finalMessage += "\n2)" + isQuestion2Correct;
        finalMessage += "\n3)" + isQuestion3Correct;
        finalMessage += "\n4)" + isQuestion4Correct;
        finalMessage += "\n5)" + isQuestion5Correct;
        finalMessage += "\n6)" + isQuestion6Correct;
        finalMessage += "\n7)" + isQuestion7Correct;
    }

    //Displays the result to the screen as a toast.
    private Toast mToastToShow;

    public void displayMessage() {

        // Set the toast and duration
        int toastDurationInMilliSeconds = 5000;

        mToastToShow = Toast.makeText(this, finalMessage, Toast.LENGTH_LONG);
        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }

            public void onFinish() {
                mToastToShow.cancel();
            }
        };

        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();
    }


}
