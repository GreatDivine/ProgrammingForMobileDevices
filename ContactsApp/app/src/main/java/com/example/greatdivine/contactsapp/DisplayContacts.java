package com.example.greatdivine.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayContacts extends AppCompatActivity {

    private ContactListAdapter mAdapter;
    private ArrayList<Contact> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        mList = new ArrayList<Contact>();
        mList = MainActivity.GetContactList();

        mAdapter = new ContactListAdapter(
                getApplicationContext(),
                mList
        );

        ListView listV = (ListView) findViewById(R.id.contact_list_view);
        listV.setAdapter(mAdapter);
        registerForContextMenu(listV);
    }

    @Override
     public void onCreateContextMenu(ContextMenu menu, View v,
                                     ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.contact_list_view) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle("Contact Options");
            String[] menuItems = {"Update", "Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }@Override
     public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = {"Update", "Delete"};
        String menuItemName = menuItems[menuItemIndex];
        Contact contact = mList.get(info.position);
        return true;
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
}

