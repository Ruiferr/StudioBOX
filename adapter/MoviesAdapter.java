package movies.flag.pt.moviesapp.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.fragments.DetailFragment;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.screens.MainActivityScreen;
import movies.flag.pt.moviesapp.tasks.DownloadImageAsyncTask;
import movies.flag.pt.moviesapp.utils.DLog;


/**
 * Created by ruiferrao on 05/09/2017.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {

    private MainActivityScreen context;

    public MoviesAdapter(MainActivityScreen context, List<Movie> objects) {
        super(context, 0, objects);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DLog.e("view", "position = " + position);

        final Movie movie = getItem(position);
        View movielayout;
        ViewHolder viewHolder;
        String url = getContext().getString(R.string.image_link) + movie.getPosterPath();

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            movielayout = inflater.inflate(R.layout.movie_item, parent, false);

            viewHolder = new ViewHolder();


            viewHolder.title = (TextView) movielayout.findViewById(R.id.movie_item_title);
            viewHolder.date = (TextView) movielayout.findViewById(R.id.movie_item_date);
            viewHolder.rating = (TextView) movielayout.findViewById(R.id.movie_item_rating);
            viewHolder.image = (ImageView) movielayout.findViewById(R.id.gallery_movie_image);
            movielayout.setTag(viewHolder);

        }else{
            movielayout = convertView;
            viewHolder = (ViewHolder) movielayout.getTag();
        }

        viewHolder.title.setText(movie.getTitle());
        viewHolder.date.setText(getContext().getString(R.string.release_date,movie.getReleaseDate()));
        viewHolder.rating.setText(getContext().getString(R.string.rating,movie.getVoteAverage()));
        viewHolder.image.setImageBitmap(null);

        new DownloadImageAsyncTask(viewHolder.image).execute(url);


        movielayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.addFragment(DetailFragment.newInstance(movie.getTitle(),
                        movie.getReleaseDate(),
                        getContext().getString(R.string.image_link) + movie.getPosterPath(),
                        movie.getOverview(), movie.getVoteAverage()));

            }
        });

        return movielayout;

    }

        private class ViewHolder{
        public TextView title;
        public TextView date;
        public TextView rating;
        public ImageView image;
    }
}
