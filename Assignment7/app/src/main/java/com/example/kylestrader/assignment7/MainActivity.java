package com.example.kylestrader.assignment7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String m_BaseYear = "2010";
    String m_ApiKey = "6c726f5a3c0033217bc5658b42873a99";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        RetrieveMovieList();
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

    public String RetrieveMovieList()
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String MovieJsonStr = "";

        try
        {
            String query = "https://api.themoviedb.org/3/discover/movie?primary_release_year=" + m_BaseYear + "&sort_by=vote_average.desc&api_key=" + m_ApiKey;
            Log.e("URL", query);
            URL url = new URL(query);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if(inputStream == null)
            {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line;

            while((line = reader.readLine()) != null)
            {
                buffer.append(line + "/n");
            }

            if (buffer.length() == 0)
            {
                return null;
            }

            MovieJsonStr = buffer.toString();
        }

        catch (IOException e)
        {
            Log.e("Main Activity", "Error", e);
        }

        finally
        {
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }

            if(reader != null)
            {
              try
              {
                  reader.close();
              }

              catch(final IOException e)
              {
                  Log.e("MainActivity", "Error closing stream", e);
              }
            }
        }

        return null;
    }
}
