package com.example.kylestrader.assignment7;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kylestrader.assignment7.Movie;

import java.util.ArrayList;

public class MovieListAdapter extends ArrayAdapter<Movie>
{
    private Context context;
    private ArrayList<Movie> movieList;

    public MovieListAdapter(Context c, ArrayList<Movie> movies)
    {
        super(c, R.layout.list_item, movies);
        context = c;
        movieList = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView rName = (TextView)  rowView.findViewById(R.id.movie_title);
        String title = movieList.get(position).getM_Title();

        rName.setText(movieList.get(position).getM_Title());

        TextView pTime = (TextView) rowView.findViewById(R.id.movie_release);
        pTime.setText(movieList.get(position).getM_Release());
        Log.v("movie", pTime.getText().toString());
        return rowView;
    }
}
