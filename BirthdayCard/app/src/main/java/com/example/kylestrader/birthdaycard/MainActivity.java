package com.example.kylestrader.birthdaycard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    public void create(View view) {
        EditText toField = (EditText) findViewById(R.id.EditTextTo);
        Editable toEditable = toField.getText();
        String recipient = toEditable.toString();

        EditText fromField = (EditText) findViewById(R.id.EditTextFrom);
        Editable fromEditable = fromField.getText();
        String sender = fromEditable.toString();

        setContentView(R.layout.activity_main);

        TextView to_tv = (TextView) findViewById(R.id.to_tv);
        to_tv.setText("Happy Birthday, " + recipient);

        TextView from_tv = (TextView) findViewById(R.id.from_tv);
        from_tv.setText("From " + sender);
    }
}
