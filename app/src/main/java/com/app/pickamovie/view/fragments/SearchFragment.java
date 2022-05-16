package com.app.pickamovie.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pickamovie.R;
import com.app.pickamovie.model.Movie;
import com.app.pickamovie.model.MovieType;
import com.app.pickamovie.utils.MovieAdapter;
import com.app.pickamovie.viewModel.MovieViewModel;
import com.app.pickamovie.viewModel.SearchViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private MovieViewModel movieViewModel;
    int pageCounter = 1; // default value
    int totalPages = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =
                inflater.inflate(R.layout.fragment_search, container, false);

        final Button findButton = rootView.findViewById(R.id.buttonFind);
        final EditText editText = rootView.findViewById(R.id.movieSearchText);
        final ImageButton nextButton = rootView.findViewById(R.id.nextPageButton);
        final ImageButton previousButton = rootView.findViewById(R.id.previousPageButton);
        nextButton.setVisibility(View.INVISIBLE);
        previousButton.setVisibility(View.INVISIBLE);

        final TextView searchResults = rootView.findViewById(R.id.resultsFound);
        final TextView pageNumber = rootView.findViewById(R.id.pageNumber);

        final CheckBox checkBoxMovie = rootView.findViewById(R.id.checkBoxMovie);
        final CheckBox checkBoxSeries = rootView.findViewById(R.id.checkBoxSeries);

        ProgressBar progressBar = rootView.findViewById(R.id.progressBarSearch);
        progressBar.setVisibility(View.INVISIBLE);

        RecyclerView movieListRecycler = rootView.findViewById(R.id.recyclerViewSearch);
        movieListRecycler.hasFixedSize();
        movieListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Movie> movies = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movies);
        movieListRecycler.setAdapter(movieAdapter);

        findButton.setOnClickListener(view -> {
            movies.clear();
            progressBar.setVisibility(View.VISIBLE);
            pageCounter = 1;

            if ((!checkBoxMovie.isChecked() && !checkBoxSeries.isChecked()) ||
                    checkBoxMovie.isChecked() && checkBoxSeries.isChecked()) {
                searchViewModel.searchForMovieByWords(editText.getText().toString().trim());
            } else if (checkBoxMovie.isChecked() && !checkBoxSeries.isChecked()) {
                searchViewModel.searchForMovieByWordsAndType(editText.getText().toString().trim(), MovieType.MOVIE.getType());
            } else if (checkBoxSeries.isChecked() && !checkBoxMovie.isChecked()) {
                searchViewModel.searchForMovieByWordsAndType(editText.getText().toString().trim(), MovieType.SERIES.getType());
            }
        });

        nextButton.setOnClickListener(view -> {
            movies.clear();
            pageCounter++;
            progressBar.setVisibility(View.VISIBLE);

            if ((!checkBoxMovie.isChecked() && !checkBoxSeries.isChecked()) ||
                    checkBoxMovie.isChecked() && checkBoxSeries.isChecked()) {
                searchViewModel.searchForMovieByWordsAndPage(editText.getText().toString().trim(), pageCounter);
            } else if (checkBoxMovie.isChecked() && !checkBoxSeries.isChecked()) {
                searchViewModel.searchForMovieByWordsAndPageAndType(editText.getText().toString().trim(), MovieType.MOVIE.getType(), pageCounter);
            } else if (checkBoxSeries.isChecked() && !checkBoxMovie.isChecked()) {
                searchViewModel.searchForMovieByWordsAndPageAndType(editText.getText().toString().trim(), MovieType.SERIES.getType(), pageCounter);
            }
        });

        previousButton.setOnClickListener(view -> {
            movies.clear();
            pageCounter--;
            searchViewModel.searchForMovieByWordsAndPage(editText.getText().toString().trim(), pageCounter);
            progressBar.setVisibility(View.VISIBLE);

            if ((!checkBoxMovie.isChecked() && !checkBoxSeries.isChecked()) ||
                    checkBoxMovie.isChecked() && checkBoxSeries.isChecked()) {
                searchViewModel.searchForMovieByWordsAndPage(editText.getText().toString().trim(), pageCounter);
            } else if (checkBoxMovie.isChecked() && !checkBoxSeries.isChecked()) {
                searchViewModel.searchForMovieByWordsAndPageAndType(editText.getText().toString().trim(), MovieType.MOVIE.getType(), pageCounter);
            } else if (checkBoxSeries.isChecked() && !checkBoxMovie.isChecked()) {
                searchViewModel.searchForMovieByWordsAndPageAndType(editText.getText().toString().trim(), MovieType.SERIES.getType(), pageCounter);
            }
        });

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.getMoviesBySearch().observe(getViewLifecycleOwner(), search -> {
            try {
                movies.clear();
                movies.addAll(search.getSearchMovie());
                Log.i("TOTAL MOVIES", search.getTotalResults());
                searchResults.setText("You searched for - '" + editText.getText() + "': " + search.getTotalResults() + " results found");

                double totalPagesDouble = Double.parseDouble(search.getTotalResults()) / 10;
                if (totalPagesDouble % 10 != 0) {
                    totalPages = (int) totalPagesDouble + 1;
                }
                else if (totalPagesDouble <= 10) {
                    totalPages = 1;
                }

                Log.i("TOTAL PAGES", totalPages + "");
                Log.i("PAGE COUNTER", pageCounter + "");
                pageNumber.setText("<<Page: " + pageCounter + " out of " + totalPages + ">>");


                if (totalPages > pageCounter) {
                    nextButton.setVisibility(View.VISIBLE);
                } else nextButton.setVisibility(View.INVISIBLE);

                if (pageCounter > 1)
                    previousButton.setVisibility(View.VISIBLE);
                else previousButton.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                editText.setText(null);
                searchResults.setText(null);
                nextButton.setVisibility(View.INVISIBLE);
                pageNumber.setText(null);
                Toast.makeText(getContext(), "Please try another key word! :)", Toast.LENGTH_SHORT).show();
                Log.i("EXCEPTION", e.getMessage());
            }

            movieAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        });

        movieAdapter.setOnClickListener(movie -> {
            Toast.makeText(getActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();

            movie.setFavorite(movieViewModel.getFavorite(movie.getId()));
            Bundle bundle = new Bundle();
            bundle.putParcelable("movieInfo", movie);  // Key, value
            NavHostFragment.findNavController(this).navigate(R.id.movie, bundle);
        });

        return rootView;
    }
}