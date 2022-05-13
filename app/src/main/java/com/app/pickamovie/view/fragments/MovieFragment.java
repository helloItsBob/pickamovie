package com.app.pickamovie.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.pickamovie.R;
import com.app.pickamovie.model.Movie;
import com.app.pickamovie.viewModel.MovieViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment {

    MovieViewModel movieViewModel;
    boolean flag = true; // true if first icon is visible, false if second one is visible.

    FloatingActionButton fab;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Movie.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieFragment newInstance(String param1, String param2) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        ImageView movieIcon = rootView.findViewById(R.id.movieIcon);
        TextView movieTitle = rootView.findViewById(R.id.movieTitle);
        TextView movieYear = rootView.findViewById(R.id.actualYear);
        TextView movieRating = rootView.findViewById(R.id.movieRating);
        TextView moviePlot = rootView.findViewById(R.id.movieDescription);

        ImageButton imagePlayButton = rootView.findViewById(R.id.imagePlayButton);
        ImageButton imageShareButton = rootView.findViewById(R.id.imageShareButton);

        imagePlayButton.setOnClickListener(view -> {
            String action = Intent.ACTION_VIEW;
            Uri uri = Uri.parse("https://www.youtube.com/results?search_query="
                    + movieTitle.getText().toString() + " " + movieYear.getText().toString());
            Intent intent = new Intent(action, uri);
            startActivity(intent);
        });

        imageShareButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hi!");
            intent.putExtra(Intent.EXTRA_TEXT, "Check this movie out - " +
                    movieTitle.getText().toString() + ".\nIt looks very interesting! :)");
            startActivity(intent);
        });

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Movie receivedMovie = bundle.getParcelable("movieInfo"); // Key

            if (receivedMovie.getPoster().equals("N/A")) {
                Glide.with(this).load(R.drawable.no_image).into(movieIcon);
            } else Glide.with(this).load(receivedMovie.getPoster()).into(movieIcon);

            movieTitle.setText(receivedMovie.getTitle());

            movieYear.setText(receivedMovie.getYear());

            if (receivedMovie.getImdbRating() != null) {
                movieRating.setText("Rating: " + receivedMovie.getImdbRating());
            } else movieRating.setText("N/A");

            try {
                if (receivedMovie.getPlot().equals("N/A")) {
                    moviePlot.setText(R.string.placeholder);
                } else moviePlot.setText(receivedMovie.getPlot());
            } catch (NullPointerException e) {
                Log.i("EXCEPTION", e.getMessage() + "\nSearched movies don't have plot!");
            }

            fab = rootView.findViewById(R.id.fab);
            fab.setOnClickListener(view -> {

                if (flag) {
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.heart_black));
                    Snackbar.make(view, "The movie has been added to your favorites!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    movieViewModel.insert(receivedMovie);
                    flag = false;
                } else {
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.heart));
                    Snackbar.make(view, "The movie has been removed from your favorites!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    movieViewModel.delete(receivedMovie);
                    flag = true;
                }
            });
        }
        return rootView;
    }
}