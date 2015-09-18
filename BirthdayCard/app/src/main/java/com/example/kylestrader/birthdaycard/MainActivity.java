package com.example.kylestrader.birthdaycard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        if (id == R.id.action_send) {
            //take screenshot
            Bitmap myBitmap = takeScreenshot();
            //save screenshot to the device
            String filePath = saveScreenshot(myBitmap);

            //send email with the screenshot as an attachment
            sendMail(filePath);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public Bitmap takeScreenshot()
    {
        View view = findViewById(android.R.id.content);
        view.setDrawingCacheEnabled(true);

        Bitmap myBitmap = view.getDrawingCache();
        return myBitmap;
    }

    public String saveScreenshot(Bitmap bitmap) {
        String filePath = Environment.getExternalStorageDirectory() +
                File.separator + "Pictures/screenshot.png";
        File imagePath = new File(filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("MainActivity", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return filePath;
    }

    public void sendMail(String path) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Happy Birthday");
        Uri attachment = Uri.parse("file://" + path);
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else
        {
            //give me a toast
            Context context = getApplicationContext();
            String text = "No Email app Available";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    public void create(View view) {
        EditText toField = (EditText) findViewById(R.id.EditTextTo);
        Editable toEditable = toField.getText();
        String recipient = toEditable.toString();

        EditText fromField = (EditText) findViewById(R.id.EditTextFrom);
        Editable fromEditable = fromField.getText();
        String sender = fromEditable.toString();

        setContentView(R.layout.activity_card_render);

        TextView to_tv = (TextView) findViewById(R.id.to_tv);
        to_tv.setText("Happy Birthday, " + recipient);

        TextView from_tv = (TextView) findViewById(R.id.from_tv);
        from_tv.setText("From " + sender);
    }
}
