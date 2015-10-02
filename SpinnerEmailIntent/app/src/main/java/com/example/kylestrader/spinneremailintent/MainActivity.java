package com.example.kylestrader.spinneremailintent;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    float TotalCost;
    String CompleteOrder;
    ArrayList<String> Drinks;
    ArrayList<String> Prices;
    ArrayList<String> Order;
    ArrayList<String> SynthesizedOrder;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.drink_spinner);

        final ScrollView scrollView = (ScrollView) findViewById(R.id.order_scroll);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drink_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        check = 0;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check = check + 1;
                if (check > 1){
                    Context context = getApplicationContext();
                    Toast.makeText(context, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                    Order.add(parent.getItemAtPosition(position).toString());

                    //Get rid of duplicates
                    Iterator iterator = Order.iterator();
                    while (iterator.hasNext())
                    {
                        String o = (String) iterator.next();
                        if(!SynthesizedOrder.contains(o)) SynthesizedOrder.add(o);
                    }

                    Iterator it = SynthesizedOrder.iterator();
                    TextView tv = (TextView) findViewById(R.id.arraylist);
                    tv.setText("");
                    CompleteOrder = "";

                    TotalCost = 0.0f;
                    while (it.hasNext()) {
                        String name;
                        name = (String)it.next();
                        float cost = findCost(name);
                        int freq = Collections.frequency(Order, name);
                        TotalCost += cost * freq;
                        CompleteOrder += (name + " - $" + cost + " X" + freq + "\n");
                        tv.append(name + " - $" + cost + " X" + freq + "\n");
                    }
                    CompleteOrder += "\n";
                    tv.append("\n");
                    CompleteOrder += "Total Cost: $" + TotalCost;
                    tv.append("Total Cost: $" + TotalCost);
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Drinks = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.drink_array)));
        Prices = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.price_array)));
        Order = new ArrayList<String>();
        SynthesizedOrder = new ArrayList<String>();
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
        if (id == R.id.action_email) {
            sendMail();
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public float findCost(String drinkname)
    {
        Iterator it = Drinks.iterator();
        for (int i = 0; i < Drinks.size(); i++){
            String name = (String)it.next();
            if (name.equals(drinkname)) {
                return Float.parseFloat(Prices.get(i));
            }
        }
        return 0;
    }

    public void sendMail() {

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_SUBJECT, "My Order");

        intent.putExtra(Intent.EXTRA_TEXT, CompleteOrder);

        this.startActivity(Intent.createChooser(intent, "Sending email..."));
    }
}
