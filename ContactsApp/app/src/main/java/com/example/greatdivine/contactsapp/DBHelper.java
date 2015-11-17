package com.example.greatdivine.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Great Divine on 11/16/2015.
 */
public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "MyContact.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_COUNTRY = "country";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        //create database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table contacts (id integer primary key, name text, email text,
        //phone text, street text, city text, country text)
        db.execSQL("create table " + CONTACTS_TABLE_NAME + "(" +
                        CONTACTS_COLUMN_ID + " integer primary key autoincrement, " +
                        CONTACTS_COLUMN_NAME + " text, " +
                        CONTACTS_COLUMN_EMAIL + " text, " +
                        CONTACTS_COLUMN_PHONE + " text, " +
                        CONTACTS_COLUMN_STREET + " text, " +
                        CONTACTS_COLUMN_CITY + " text, " +
                        CONTACTS_COLUMN_COUNTRY + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact(String name, String email, String phone, String street, String city, String country)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("country", country);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from contacts where id=" + id + "", null );
        return res;
    }

    public Contact cursorToContact(Cursor cursor)
    {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(0));
        contact.setName(cursor.getString(1));
        contact.setName(cursor.getString(2));
        contact.setName(cursor.getString(3));
        contact.setName(cursor.getString(4));
        contact.setName(cursor.getString(5));
        contact.setName(cursor.getString(6));
        return contact;
    }

    public int numberOfRows()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name,  String email, String phone, String street, String city, String country)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("country", country);
        db.update(
                "contacts",
                contentValues,
                "id = ? ",
                new String[] { Integer.toString(id) }
        );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(
                "contacts",
                "id = ? ",
                new String[] { Integer.toString(id)}
        );
    }

    public ArrayList<Contact> getAllContacts()
    {
        ArrayList<Contact> mList = new ArrayList<Contact>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from contacts", null);
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            long id = res.getLong(res.getColumnIndex(CONTACTS_COLUMN_ID));
            String name = res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME));
            String email = res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL));
            String phone = res.getString(res.getColumnIndex(CONTACTS_COLUMN_PHONE));
            String street = res.getString(res.getColumnIndex(CONTACTS_COLUMN_STREET));
            String city = res.getString(res.getColumnIndex(CONTACTS_COLUMN_CITY));
            String country = res.getString(res.getColumnIndex(CONTACTS_COLUMN_COUNTRY));
            Contact c =  new Contact(id, name, email, phone, street, city, country);
            mList.add(c);
            //mList.add(c.getId()+". "+c.getName()+", " +c.getPhone());
            res.moveToNext();
        }
        return mList;
    }
}
