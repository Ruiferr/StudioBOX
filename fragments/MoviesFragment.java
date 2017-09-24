package movies.flag.pt.moviesapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.adapter.MoviesAdapter;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowPlayingMoviesAsyncTask;
import movies.flag.pt.moviesapp.screens.MainActivityScreen;
import movies.flag.pt.moviesapp.utils.DLog;


public class MoviesFragment extends BaseFragment {

    private ListView listView;
    private SwipeRefreshLayout refresh;


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


        executeRequestExample();

        return v;
    }

    private void findViews(View v) {
        listView = (ListView) v.findViewById(R.id.now_playing_movie_fragment);
        refresh = (SwipeRefreshLayout) v.findViewById(R.id.movie_fragment_swiperefresh);
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

                MoviesAdapter adapter = new MoviesAdapter((MainActivityScreen) getActivity(), movies);

                listView.setAdapter(adapter);

                refresh.setRefreshing(false);

            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");

            }
        }.execute();
    }

}
