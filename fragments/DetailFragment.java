package movies.flag.pt.moviesapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.tasks.DownloadImageAsyncTask;

/**
 * Created by ruiferrao on 14/09/2017.
 */

public class DetailFragment extends BaseFragment {

    private String title;
    private String date;
    private String imageStr;
    private String overview;
    private Double vote;

    private TextView titleLabel;
    private TextView dateLabel;
    private ImageView image;
    private TextView summary;
    private TextView rating;
    private Button share;


    public static DetailFragment newInstance(String title, String date, String imageStr, String overview, Double vote) {
        Bundle args = new Bundle();
        args.putString("titleKey", title);
        args.putString("dateKey", date);
        args.putString("imageKey", imageStr);
        args.putString("overviewKey", overview);
        args.putDouble("voteKey", vote);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        this.title = args.getString("titleKey");
        this.date = args.getString("dateKey");
        this.imageStr = args.getString("imageKey");
        this.overview = args.getString("overviewKey");
        this.vote = args.getDouble("voteKey");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment, container, false);
        findViews(v);
        addListeners();

        new DownloadImageAsyncTask(image).execute(imageStr);

        return v;
    }

    private void findViews(View v) {
        titleLabel = (TextView)v.findViewById(R.id.detail_item_title);
        dateLabel = (TextView)v.findViewById(R.id.detail_item_date);
        image = (ImageView)v.findViewById(R.id.gallery_item_image);
        summary = (TextView)v.findViewById(R.id.detail_item_overview);
        rating = (TextView)v.findViewById(R.id.detail_item_rating);
        share = (Button)v.findViewById(R.id.detail_item_share);

    }

    private void addListeners() {
        titleLabel.setText(title);
        dateLabel.setText(date);
        summary.setText(overview);
        rating.setText(getResources().getString(R.string.rating, vote));

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, title + " " + imageStr);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

    }
}
