package com.example.greatdivine.sendmail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Matrix;
import android.widget.ImageView;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity implements View.OnClickListener {

    EditText editTextEmail, editTextSubject, editTextMessage;
    Button send, attachment;
    String email, message, subject, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.email_address_view);
        editTextSubject = (EditText) findViewById(R.id.subject_view);
        editTextMessage = (EditText) findViewById(R.id.message_view);

        send = (Button) findViewById(R.id.send);
        attachment = (Button) findViewById(R.id.attachment);

        send.setOnClickListener(this);
        attachment.setOnClickListener(this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
        }
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

        return super.onOptionsItemSelected(item);
    }



    public void sendMail(View view) {

        String email = editTextEmail.getText().toString();
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");

        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        if (URI != null) {
            intent.putExtra(Intent.EXTRA_STREAM, URI);
        }

        intent.putExtra(Intent.EXTRA_TEXT, message);

        this.startActivity(Intent.createChooser(intent, "Sending email..."));
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.attachment)) {
            openGallery();
        }
        if (v == findViewById(R.id.send)) {
            sendMail(v);
        }
    }


    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);
    }

}
