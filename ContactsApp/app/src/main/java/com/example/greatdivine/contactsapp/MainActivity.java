package com.example.greatdivine.contactsapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ContactListAdapter mAdapter;
    private ArrayList<Contact> mList;
    DBHelper m_Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        m_Db = new DBHelper(this);

        mList = new ArrayList<Contact>();
        mList = GetContactList();

        mAdapter = new ContactListAdapter(
                getApplicationContext(),
                mList
        );

        ListView listV = (ListView) findViewById(R.id.contact_list_view);
        listV.setAdapter(mAdapter);
        registerForContextMenu(listV);

        handleIntent(getIntent());

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.contact_list_view) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle("Contact Options");
            String[] menuItems = {"Update", "Delete", "Cancel"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }@Override
     public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = {"Update", "Delete", "Cancel"};
        String menuItemName = menuItems[menuItemIndex];
        Contact contact = mList.get(info.position);

        if (menuItemName == "Update")
        {
            Context c = getApplicationContext();
            Intent i = new Intent(c, AddContact.class);
            startActivityForResult(i, 2);
        }
        if (menuItemName == "Delete") {
            DeleteContact(contact.getId());
            mList = GetContactList();
            mAdapter.clear();
            mAdapter.addAll(mList);
            mAdapter.notifyDataSetChanged();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.contact_search);//.getActionView();
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        return true;
    }

    public DBHelper getM_Db() {
        return m_Db;
    }

    public void addContact(String m_Name, String m_Email, String m_Phone, String m_Street, String m_City, String m_Country)
    {
        m_Db.insertContact(m_Name, m_Email, m_Phone, m_Street, m_City, m_Country);
    }

    public void updateContact(int id, String m_Name, String m_Email, String m_Phone, String m_Street, String m_City, String m_Country)
    {
        m_Db.updateContact(id, m_Name, m_Email, m_Phone, m_Street, m_City, m_Country);
    }

    public ArrayList<Contact> GetContactList()
    {
        return m_Db.getAllContacts();
    }

    public void DeleteContact(int id)
    {
        m_Db.deleteContact(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add)
        {
            Context c = getApplicationContext();
            Intent i = new Intent(c, AddContact.class);
            startActivityForResult(i, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bundle extras = getIntent().getExtras();

        switch(resultCode)
        {
            case 1:
                addContact(
                        extras.getString("name"),
                        extras.getString("email"),
                        extras.getString("phone"),
                        extras.getString("street"),
                        extras.getString("city"),
                        extras.getString("country")
                );
                break;

            case 2:
                updateContact(
                        extras.getInt("id"),
                        extras.getString("name"),
                        extras.getString("email"),
                        extras.getString("phone"),
                        extras.getString("street"),
                        extras.getString("city"),
                        extras.getString("country")
                );

                break;
        }

        mList = GetContactList();
        mAdapter.clear();
        mAdapter.addAll(mList);
        mAdapter.notifyDataSetChanged();
    }
}
