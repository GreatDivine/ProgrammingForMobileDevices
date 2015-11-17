package com.example.greatdivine.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_display) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            return true;
        }

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

        MainActivity.AddContact(sName, sEmail, sPhone, sStreet, sCity, sCountry);
    }
}


