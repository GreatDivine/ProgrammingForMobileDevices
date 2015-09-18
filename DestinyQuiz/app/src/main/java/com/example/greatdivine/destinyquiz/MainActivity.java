package com.example.greatdivine.destinyquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
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
        if(questionNum > 5) questionNum = 0;

        switch(questionNum) {
            case 0:
                setContentView(R.layout.activity_main);
                break;
            case 1:case 2:case 3:case 4:case 5:
                setContentView(R.layout.activity_question);
                setQuestion(questionNum);
                break;
        }
    }

    public void onClick(View view) {
        endTurn();
    }

    void setQuestion(int q) {

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton)view).isChecked();

        switch(view.getId()) {
            case R.id.radio_button_1:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 1) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.radio_button_2:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 2) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.radio_button_3:
                if(checked) {
                    if(correctAnswers[questionNum-1] == 3) {
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                        score++;
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.radio_button_4:
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

        endTurn();
    }
}
