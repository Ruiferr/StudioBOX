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
import movies.flag.pt.moviesapp.adapter.SeriesAdapter;
import movies.flag.pt.moviesapp.http.entities.Serie;
import movies.flag.pt.moviesapp.http.entities.SeriesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowPlayingSeriesAsyncTask;
import movies.flag.pt.moviesapp.screens.MainActivityScreen;
import movies.flag.pt.moviesapp.utils.DLog;


public class SeriesFragment extends BaseFragment{

    private ListView listView;
    private SwipeRefreshLayout refresh;



    public static SeriesFragment newInstance() {
        Bundle args = new Bundle();
        SeriesFragment fragment = new SeriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.series_fragment, container, false);
        findViews(v);
        addListeners();

        executeRequestExample();

        return v;
    }

    private void findViews(View v) {
        listView = (ListView)v.findViewById(R.id.now_playing_serie_fragment);
        refresh = (SwipeRefreshLayout) v.findViewById(R.id.series_fragment_swiperefresh);
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
        // Example to request get now playing movies
        new GetNowPlayingSeriesAsyncTask(getActivity()){

            @Override
            protected void onResponseSuccess(SeriesResponse seriesResponse) {
                DLog.d(tag, "onResponseSuccess " + seriesResponse);

                List<Serie> series = seriesResponse.getSeries();

                SeriesAdapter adapter = new SeriesAdapter((MainActivityScreen) getActivity(), series);

                listView.setAdapter(adapter);

                refresh.setRefreshing(false);
            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");
                // Here i now that some error occur when processing the request,
                // possible my internet connection if turned off
            }
        }.execute();
    }

}
