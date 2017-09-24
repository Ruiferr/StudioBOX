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
import movies.flag.pt.moviesapp.http.entities.Serie;
import movies.flag.pt.moviesapp.screens.MainActivityScreen;
import movies.flag.pt.moviesapp.tasks.DownloadImageAsyncTask;


/**
 * Created by ruiferrao on 13/09/2017.
 */

public class SeriesAdapter extends ArrayAdapter<Serie>{

    private MainActivityScreen context;


    public SeriesAdapter(MainActivityScreen context, List<Serie> objects) {
        super(context, 0, objects);

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        final Serie serie = getItem(position);
        View serielayout;
        ViewHolder viewHolder;
        String url = getContext().getString(R.string.image_link) + serie.getPosterPath();

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            serielayout = inflater.inflate(R.layout.serie_item, parent, false);

            viewHolder = new ViewHolder();


            viewHolder.title = (TextView) serielayout.findViewById(R.id.serie_item_title);
            viewHolder.date = (TextView) serielayout.findViewById(R.id.serie_item_date);
            viewHolder.rating = (TextView) serielayout.findViewById(R.id.serie_item_rating);
            viewHolder.image = (ImageView) serielayout.findViewById(R.id.gallery_item_image);
            serielayout.setTag(viewHolder);

        }else{
            serielayout = convertView;
            viewHolder = (ViewHolder) serielayout.getTag();
        }

        viewHolder.title.setText(serie.getName());
        viewHolder.date.setText(getContext().getString(R.string.release_date,serie.getAirDate()));
        viewHolder.rating.setText(getContext().getString(R.string.rating,serie.getVoteAverage()));
        viewHolder.image.setImageBitmap(null);

        new DownloadImageAsyncTask(viewHolder.image).execute(url);


        serielayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.addFragment(DetailFragment.newInstance(serie.getOriginalName(), serie.getAirDate(), getContext().getString(R.string.image_link) + serie.getPosterPath(), serie.getOverview(), serie.getVoteAverage()));

            }
        });



        return serielayout;

    }

    private class ViewHolder{
        public TextView title;
        public TextView date;
        public TextView rating;
        public ImageView image;
    }
}
