package com.example.kylestrader.fivethings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Page_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page_3, menu);
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

    public void goToPage4(View view) {
        Context context = getApplicationContext();
        Intent i = new Intent(context, Page_4.class);
        startActivity(i);
    }

    public void goToPage2(View view) {
        Context context = getApplicationContext();
        Intent i = new Intent(context, Page_2.class);
        startActivity(i);
    }

    public void goToCover(View view) {
        Context context = getApplicationContext();
        Intent i = new Intent(context, Cover.class);
        startActivity(i);
    }
}
