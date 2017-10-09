package movies.flag.pt.moviesapp.fragments;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.adapter.MoviesAdapter;
import movies.flag.pt.moviesapp.constants.PreferenceIds;
import movies.flag.pt.moviesapp.databases.MovieDatabase;
import movies.flag.pt.moviesapp.helpers.NetworkChangeReceiver;
import movies.flag.pt.moviesapp.helpers.SharedPreferencesHelper;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import movies.flag.pt.moviesapp.screens.MainActivityScreen;
import movies.flag.pt.moviesapp.utils.DLog;


public class MoviesFragment extends BaseFragment {

    private ListView listView;
    private SwipeRefreshLayout refresh;
    private TextView warningTxt;
    private NetworkChangeReceiver networkVerify;
    private static final SimpleDateFormat dateInfo = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private Long currentTime = System.currentTimeMillis();


    public static MoviesFragment newInstance() {
        Bundle args = new Bundle();
        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.movie_fragment, container, false);
        findViews(v);
        addListeners();

        networkVerify = new NetworkChangeReceiver();

        getActivity().registerReceiver(networkVerify, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        executeRequestExample();




        return v;
    }

    private void findViews(View v) {
        listView = (ListView) v.findViewById(R.id.now_playing_movie_fragment);
        refresh = (SwipeRefreshLayout) v.findViewById(R.id.movie_fragment_swiperefresh);
        warningTxt = (TextView) v.findViewById(R.id.movie_fragment_warning);
    }

    private void addListeners() {

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeRequestExample();

            }
        });

    }

    private void executeRequestExample() {

        new GetNowPlayingMoviesAsyncTask(getActivity()) {

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);

                List<Movie> movies = moviesResponse.getMovies();


                MovieDatabase.deleteAll(MovieDatabase.class);

                for (int i = 0 ; i < movies.size() ; i++){
                    Movie returnMov = movies.get(i);

                    new MovieDatabase(returnMov.getId(),returnMov.getTitle(),returnMov.getOverview(),
                            returnMov.getReleaseDate(), returnMov.getPosterPath(), returnMov.getVoteAverage()).save();
                }

                SharedPreferencesHelper.savePreference(PreferenceIds.TIMESTAMP , currentTime);



                MoviesAdapter adapter = new MoviesAdapter((MainActivityScreen) getActivity(), movies);

                listView.setAdapter(adapter);

                refresh.setRefreshing(false);

            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");

                getDatabase();

            }
        }.execute();
    }


    private void getDatabase() {


        List<Movie> movies = new ArrayList<>();
        List<MovieDatabase> findMovie = MovieDatabase.listAll(MovieDatabase.class);

        for (int i = 0 ; i < findMovie.size() ; i ++){
            MovieDatabase selectMovie = findMovie.get(i);

            Movie movie = new Movie(selectMovie.getMovieID(), selectMovie.getMovieTitle(),
                    selectMovie.getMovieOverview(), selectMovie.getMovieReleaseDate(),
                    selectMovie.getMovieImg(), selectMovie.getMovieVote());

            movies.add(movie);

        }


        MoviesAdapter adapter = new MoviesAdapter((MainActivityScreen) getActivity(), movies);


        Timestamp lastTimeRegistered = new Timestamp(currentTime);

        warningTxt.setText(getResources().getString(R.string.last_connection, dateInfo.format(lastTimeRegistered)));


        if (refresh.isRefreshing())
            refresh.setRefreshing(false);

        listView.setAdapter(adapter);

    }
}