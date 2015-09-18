package com.example.greatdivine.destinyquiz;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int questionNum = 0;
    int[] correctAnswers = {1, 2, 3, 4, 3};
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void endTurn() {
        questionNum++;
        if(questionNum > 6) {
            questionNum = 0;
            score = 0;
        }

        switch(questionNum) {
            case 0:
                setContentView(R.layout.activity_main);
                break;
            case 1:
                setContentView(R.layout.activity_question);
                setQuestion(questionNum);
                break;
            case 2:case 3:case 4:case 5:
                setQuestion(questionNum);
                break;
            case 6:
                setContentView(R.layout.activity_score);
                setEnding();
        }
    }

    public void onClick(View view) {
        endTurn();
    }

    void setQuestion(int q) {
        TextView qv = (TextView) findViewById(R.id.question);
        TextView qsv = (TextView) findViewById(R.id.content);
        ImageView iv = (ImageView) findViewById(R.id.q_img);
        ProgressBar pb = (ProgressBar) findViewById(R.id.progress_bar);
        RadioButton rb1 = (RadioButton) findViewById(R.id.rb1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.rb2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.rb3);
        RadioButton rb4 = (RadioButton) findViewById(R.id.rb4);
        switch(q){
            case 1:
                iv.setImageResource(R.drawable.img_1);
                qv.setText(R.string.Question_1);
                qsv.setText(R.string.Question_1_Content);
                pb.setProgress(0);
                rb1.setText(R.string.Q1_A0);
                rb2.setText(R.string.Q1_A1);
                rb3.setText(R.string.Q1_A2);
                rb4.setText(R.string.Q1_A3);
                break;
            case 2:
                iv.setImageResource(R.drawable.img_2);
                qv.setText(R.string.Question_2);
                qsv.setText(R.string.Question_2_Content);
                pb.setProgress(1);
                rb1.setText(R.string.Q2_A0);
                rb2.setText(R.string.Q2_A1);
                rb3.setText(R.string.Q2_A2);
                rb4.setText(R.string.Q2_A3);
                break;
            case 3:
                iv.setImageResource(R.drawable.img_3);
                qv.setText(R.string.Question_3);
                qsv.setText(R.string.Question_3_Content);
                pb.setProgress(2);
                rb1.setText(R.string.Q3_A0);
                rb2.setText(R.string.Q3_A1);
                rb3.setText(R.string.Q3_A2);
                rb4.setText(R.string.Q3_A3);
                break;
            case 4:
                iv.setImageResource(R.drawable.img_4);
                qv.setText(R.string.Question_4);
                qsv.setText(R.string.Question_4_Content);
                pb.setProgress(3);
                rb1.setText(R.string.Q4_A0);
                rb2.setText(R.string.Q4_A1);
                rb3.setText(R.string.Q4_A2);
                rb4.setText(R.string.Q4_A3);
                break;
            case 5:
                iv.setImageResource(R.drawable.img_5);
                qv.setText(R.string.Question_5);
                qsv.setText(R.string.Question_5_Content);
                pb.setProgress(4);
                rb1.setText(R.string.Q5_A0);
                rb2.setText(R.string.Q5_A1);
                rb3.setText(R.string.Q5_A2);
                rb4.setText(R.string.Q5_A3);
                break;
        }
    }

    void setEnding() {
        TextView tv = (TextView) findViewById(R.id.score);
        tv.setText(score + "/5");

        TextView tsv = (TextView) findViewById(R.id.scoreResult);
        ImageView iv = (ImageView) findViewById(R.id.scoreImg);
        if (score >= 4) {
            tsv.setText(R.string.Pass);
            iv.setImageResource(R.drawable.img_win);
        }
        else {
            tsv.setText(R.string.Fail);
            iv.setImageResource(R.drawable.img_lose);
        }
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton)view).isChecked();

        switch(view.getId()) {
            case R.id.rb1:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 1) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.rb2:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 2) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.rb3:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 3) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.rb4:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 4) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
                    break;
                }
        }

        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        rg.clearCheck();

        endTurn();
    }
}
