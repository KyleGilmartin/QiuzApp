package edu.kylegilmartin.qiuzapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.kylegilmartin.qiuzapp.Model.TrueFalse;

public class MainActivity extends AppCompatActivity {



    Button btTrue, btFalse;
    TextView tvQuestion,tvScore;
    ProgressBar progressBar;
    int mIndex, mQuestion,mScore;


    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };
    final int PROGRESS_BAR_FILL = (int)Math.ceil(100./mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btTrue = findViewById(R.id.true_button);
        btFalse = findViewById(R.id.false_button);
        tvQuestion = findViewById(R.id.question_text_view);
        tvScore = findViewById(R.id.score);
        progressBar = findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getmQuestionID();
        tvQuestion.setText(mQuestion);

        btTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAswer(true);
                UpdateQuestion();

            }
        });

        btFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAswer(false);
                UpdateQuestion();
            }
        });
    }

    private void UpdateQuestion() {
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game over");
            alert.setCancelable(false);
            alert.setMessage("You score " + mScore + " points");
            alert.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent net = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(net);

                }

            });
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getmQuestionID();
        tvQuestion.setText(mQuestion);
        progressBar.incrementProgressBy(PROGRESS_BAR_FILL);
        tvScore.setText("Score " + mScore + "/" + mQuestionBank.length);
    }

    private  void CheckAswer(Boolean userSelection){
        boolean correctAwser = mQuestionBank[mIndex].ismAnswer();
        if(userSelection == correctAwser){

            mScore = mScore + 1;
        } else {

        }
    }
}