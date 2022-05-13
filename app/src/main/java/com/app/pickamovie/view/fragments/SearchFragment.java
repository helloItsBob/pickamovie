package com.app.pickamovie.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pickamovie.R;
import com.app.pickamovie.model.Movie;
import com.app.pickamovie.utils.MovieAdapter;
import com.app.pickamovie.viewModel.SearchViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =
                inflater.inflate(R.layout.fragment_search, container, false);

        final Button button = rootView.findViewById(R.id.buttonFind);
        final EditText editText = rootView.findViewById(R.id.movieSearchText);

        ProgressBar progressBar = rootView.findViewById(R.id.progressBarSearch);
        progressBar.setVisibility(View.INVISIBLE);

        RecyclerView movieListRecycler = rootView.findViewById(R.id.recyclerViewSearch);
        movieListRecycler.hasFixedSize();
        movieListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Movie> movies = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movies);
        movieListRecycler.setAdapter(movieAdapter);

        button.setOnClickListener(view -> {
            movies.clear();
            searchViewModel.searchForMovieByWords(editText.getText().toString());
            progressBar.setVisibility(View.VISIBLE);
        });

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        searchViewModel.getMoviesBySearch().observe(getViewLifecycleOwner(), search -> {
            movies.addAll(search.getSearchMovie());
            movieAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        });

        movieAdapter.setOnClickListener(movie -> {
            Toast.makeText(getActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putParcelable("movieInfo", movie);  // Key, value
            NavHostFragment.findNavController(this).navigate(R.id.movie, bundle);
        });

        return rootView;
    }
}