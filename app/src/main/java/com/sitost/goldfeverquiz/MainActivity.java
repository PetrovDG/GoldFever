package com.sitost.goldfeverquiz;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


/**
 * This app displays a Gold Fever Quiz.
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = ".MESSAGE";

    // Methods to save the variable values when the screen is rotated.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /** Called when the user taps the Start Quiz button */
    public void startQuiz(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, QuizActivity.class);
        EditText editText = (EditText) findViewById(R.id.name_field);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}