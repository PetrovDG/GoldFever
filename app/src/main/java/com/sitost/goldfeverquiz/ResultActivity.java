package com.sitost.goldfeverquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.playerName);
        textView.setText(message);

        // Final message with result
        TextView resultText = (TextView) findViewById(R.id.result_text);
        int correctAnswers = getIntent().getIntExtra("CORRECT_ANSWERS", 0);
        resultText.setText(getString(R.string.result, String.valueOf(correctAnswers)));


        // Text switch at number of correct answers
        TextView textComment = (TextView) findViewById(R.id.result_comment);
        if (correctAnswers == 0) textComment.setText(getString(R.string.result0));
        switch (correctAnswers) {
            case 1:
            case 2:
            case 3:
                textComment.setText(getString(R.string.result1_3));
                break;
            case 4:
            case 5:
                textComment.setText(getString(R.string.result4_5));
                break;
            case 6:
            case 7:
                textComment.setText(getString(R.string.result6_7));
                break;

        }


        // Prize image switch at number of correct answers
        ImageView prizeImage = (ImageView) findViewById(R.id.image_prize);
        if (correctAnswers == 0) prizeImage.setImageResource(R.drawable.sad_man);
        switch (correctAnswers) {
            case 1:
            case 2:
            case 3:
                prizeImage.setImageResource(R.drawable.copper_prize_1);
                break;
            case 4:
            case 5:
                prizeImage.setImageResource(R.drawable.silver_tree);
                break;
            case 6:
            case 7:
                prizeImage.setImageResource(R.drawable.gold_prize);
                break;

        }
    }


    /**
     * Called when the user taps the Wall of Fame button
     */
    public void wallOfFame(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, FameActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the Return button
     */
    public void pressReturn(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the Send button
     */
    public void pressSend(View view) {
        // Do something in response to button
        TextView textView = (TextView) findViewById(R.id.playerName);
        String name = textView.getText().toString();

        String correctAnswers = String.valueOf(getIntent().getIntExtra("CORRECT_ANSWERS", 0));

        String resultMessage = createResultSummary(name, correctAnswers);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.send_result));
        intent.putExtra(Intent.EXTRA_TEXT, resultMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private String createResultSummary(String name, String correctAnswers) {


        return name + "\n" + getString(R.string.correct_answers) + " " + correctAnswers + "/7";
    }


}
