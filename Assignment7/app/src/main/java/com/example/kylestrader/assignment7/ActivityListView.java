package com.example.kylestrader.assignment7;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kyle.strader on 11/9/2015.
 */
public class ActivityListView extends AppCompatActivity {

    private ArrayAdapter<Movie> mAdapter;
    private ArrayList<Movie> mList;

    String m_BaseYear = "2010";
    int m_Number = 20;
    String m_ApiKey = "6c726f5a3c0033217bc5658b42873a99";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view);

        Intent i = getIntent();

        m_BaseYear = i.getStringExtra("year");
        m_Number = Integer.valueOf(i.getStringExtra("number"));

        mList = new ArrayList<Movie>();

        mAdapter = new MovieListAdapter(
                getApplicationContext(),
                mList
        );

        ListView listV = (ListView) findViewById(R.id.list_view);
        listV.setAdapter(mAdapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                String text = "You click on me now";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        //Create a Connectivity Manager
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Check Network State
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Start an asynchronous task in the background
            new FetchMovieTask().execute();
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Network connection NOT available";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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

    private String[] GetMovieDataFromJson(String moviesJsonStr) throws JSONException
    {
        JSONObject movieJson = new JSONObject(moviesJsonStr);
        JSONArray movieArray = movieJson.getJSONArray("results");

        String[] resultStrs = new String[movieArray.length()];

        for(int i = 0; i < movieArray.length(); i++)
        {
            String Title = "";
            String Date = "";
            String Description = "";

            JSONObject movie = movieArray.getJSONObject(i);

            Title = movie.getString("title");
            Date = movie.getString("release_date");
            Description = movie.getString("overview");

            Movie m = new Movie();
            m.m_Description = Description;
            m.m_Release = Date;
            m.m_Title = Title;

            //mAdapter.add(m);
            mList.add(m);
            resultStrs[i] = Title + "," + Date + "," + Description;

        }

        for (String s: resultStrs)
        {
            Log.v("MainActivity", s);
        }

        return resultStrs;
    }

    private class FetchMovieTask extends AsyncTask<Void, Void, String[]>
    {
        public FetchMovieTask() {}
        //Must implement this method
        protected String[] doInBackground(Void... params)
        {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            //Contain the raw JSON response
            String movieJsonStr = null;
            try {
                String sUrl = "https://api.themoviedb.org/3/discover/movie?primary_release_year=" + m_BaseYear + "&sort_by=popularity.desc&api_key=" + m_ApiKey;
                URL url = new URL(sUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                // Read the input stream
                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }
                //Place input stream into a buffered reader
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                movieJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e("MainActivity", "Error ", e);
                return null;
            }
            finally
            {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    }
                    catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
                Log.i("MainActivity", movieJsonStr);

                int numDays = 7;
                String[] resultStrs = new String[numDays];
                try {
                    resultStrs = GetMovieDataFromJson(movieJsonStr);
                } catch (JSONException j) {
                    Log.e("MainActivity", "JSON Exception" + j);
                }
                return resultStrs;
            }
        }

        @Override
        protected void onPostExecute(String[] result)
        {
            if (result != null)
            {
                mAdapter = new MovieListAdapter(getApplicationContext(), mList);

                ListView lv = (ListView) findViewById(R.id.list_view);

                lv.setAdapter(mAdapter);

                for (String s : result)
                {
                    Movie m = new Movie();
                    m.m_Title = s.split(",")[0];
                    m.m_Release = s.split(",")[1];
                    m.m_Description = s.split(",")[1];
                    mAdapter.add(m);
                }
            }
        }

    }
}
