package com.app.pickamovie.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pickamovie.R;
import com.app.pickamovie.model.Movie;
import com.app.pickamovie.utils.MovieAdapter;
import com.app.pickamovie.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private int times;

    private Set<Movie> set = new HashSet<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // spinner from resource file
        Spinner spinner = rootView.findViewById(R.id.number_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.numbers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                times = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        ProgressBar progressBar = rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Button getButton = rootView.findViewById(R.id.getMovies);

        RecyclerView movieList = rootView.findViewById(R.id.recyclerView);
        movieList.hasFixedSize();
        movieList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Movie> movies = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), movies);
        movieList.setAdapter(movieAdapter);

        getButton.setOnClickListener(view -> {
            set.clear();
            movies.clear();
            movieViewModel.searchForMovieByRandomId(times);
            progressBar.setVisibility(View.VISIBLE);
        });

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getSearchedMovie().observe(getViewLifecycleOwner(), movie -> {
            set.add(movie);
            movies.clear();
            movies.addAll(set);
            if (set.size() == times) {
                progressBar.setVisibility(View.INVISIBLE);
                movieAdapter.notifyDataSetChanged();
            }
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