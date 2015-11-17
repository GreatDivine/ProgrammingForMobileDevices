package com.example.greatdivine.contactsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Great Divine on 11/16/2015.
 */
public class ContactListAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private ArrayList<Contact> movieList;

    public ContactListAdapter(Context c, ArrayList<Contact> contacts)
    {
        super(c, R.layout.contact_item, contacts);
        context = c;
        movieList = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.contact_item, parent, false);

        TextView tName = (TextView)  rowView.findViewById(R.id.item_name);
        String name = movieList.get(position).getName();
        tName.setText(name);

        TextView tEmail = (TextView) rowView.findViewById(R.id.item_email);
        String email = movieList.get(position).getEmail();
        tEmail.setText(email);

        return rowView;
    }
}
