package com.example.greatdivine.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static DBHelper m_Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_Db = new DBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public static DBHelper getM_Db() {
        return m_Db;
    }

    public static void AddContact(String m_Name, String m_Email, String m_Phone, String m_Street, String m_City, String m_Country)
    {
        m_Db.insertContact(m_Name, m_Email, m_Phone, m_Street, m_City, m_Country);
    }

    public static ArrayList<Contact> GetContactList()
    {
        return m_Db.getAllContacts();
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
            Context context = getApplicationContext();
            Intent i = new Intent(context, AddContact.class);
            startActivity(i);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_display) {
            Context context = getApplicationContext();
            Intent i = new Intent(context, DisplayContacts.class);
            startActivity(i);
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
}
