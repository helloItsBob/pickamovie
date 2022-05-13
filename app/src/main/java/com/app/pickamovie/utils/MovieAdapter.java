package com.app.pickamovie.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pickamovie.R;
import com.app.pickamovie.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final ArrayList<Movie> movies;
    private OnClickListener listener;
    Context context;

    public MovieAdapter(Context context, ArrayList<Movie> movies)
    {
        this.context = context;
        this.movies = movies;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getTitle());

        // changing color to every second item on the list
        if (position % 2 == 1)
        {
            int color = holder.itemView.getResources().getColor(R.color.light_blue);
            holder.title.setBackgroundColor(color);
        }

            // GLIDE
        if (movies.get(position).getPoster() != null) {
            String imageUrl = movies.get(position).getPoster();
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.no_image)
                    .into(holder.poster);
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(context).clear(holder.poster);
            // remove the placeholder (optional);
            //holder.poster.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView poster;

        ViewHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            poster = itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(view -> {
                listener.onClick(movies.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(Movie movie);
    }
}
