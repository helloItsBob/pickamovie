package com.app.pickamovie.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.app.pickamovie.viewModel.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    private Button deleteAllButton;
    private ProgressBar progressBar;

    private RecyclerView movieRecyclerList;
    private ArrayList<Movie> moviesList;
    private MovieAdapter movieAdapter;

    private MovieViewModel movieViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        deleteAllButton = rootView.findViewById(R.id.deleteAllButton);
        progressBar = rootView.findViewById(R.id.progressBarFavorites);
        progressBar.setVisibility(View.INVISIBLE);

        movieRecyclerList = rootView.findViewById(R.id.recyclerViewFavorites);
        movieRecyclerList.hasFixedSize();
        movieRecyclerList.setLayoutManager(new LinearLayoutManager(getContext()));

        moviesList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getActivity(), moviesList);
        movieRecyclerList.setAdapter(movieAdapter);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(getViewLifecycleOwner(), movies -> {
            moviesList.clear();
            if (!movies.isEmpty()) {
                moviesList.addAll(movies);
            }
            movieAdapter.notifyDataSetChanged();
        });

        movieAdapter.setOnClickListener(movie -> {
            Toast.makeText(getActivity(), movie.getTitle(), Toast.LENGTH_SHORT).show();

            movie.setFavorite(movieViewModel.getFavorite(movie.getId()));
            Bundle bundle = new Bundle();
            bundle.putParcelable("movieInfo", movie);  // Key, value
            NavHostFragment.findNavController(this).navigate(R.id.movie, bundle);
        });

        deleteAllButton.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            movieViewModel.deleteAllMovies();
            moviesList.clear();
            progressBar.setVisibility(View.INVISIBLE);
            movieAdapter.notifyDataSetChanged();
        });

        return rootView;
    }
}