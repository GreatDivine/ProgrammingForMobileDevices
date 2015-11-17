package com.example.greatdivine.contactsapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void Submit(View view)
    {
        TextView name = (TextView)findViewById(R.id.add_name_field);
        String sName = name.getText().toString();

        TextView email = (TextView)findViewById(R.id.add_email_field);
        String sEmail = email.getText().toString();

        TextView phone = (TextView)findViewById(R.id.add_phone_field);
        String sPhone = phone.getText().toString();

        TextView street = (TextView)findViewById(R.id.add_street_field);
        String sStreet = street.getText().toString();

        TextView city = (TextView)findViewById(R.id.add_city_field);
        String sCity = city.getText().toString();

        TextView country = (TextView)findViewById(R.id.add_country_field);
        String sCountry = country.getText().toString();

        Intent i = new Intent();
        i.putExtra("name", sName);
        i.putExtra("email", sEmail);
        i.putExtra("phone", sPhone);
        i.putExtra("street", sStreet);
        i.putExtra("city", sCity);
        i.putExtra("country", sCountry);

        setResult(1, i);
        finish();
    }
}


