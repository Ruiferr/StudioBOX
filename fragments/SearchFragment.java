package movies.flag.pt.moviesapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.adapter.MoviesAdapter;
import movies.flag.pt.moviesapp.http.entities.Movie;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;
import movies.flag.pt.moviesapp.http.requests.GetNowSearchAsyncTask;
import movies.flag.pt.moviesapp.screens.MainActivityScreen;
import movies.flag.pt.moviesapp.utils.DLog;


public class SearchFragment extends BaseFragment{

    private static final String TEXT_ARG = "textArg";

    private ListView searchList;
    private ImageButton searchArrow;
    private EditText searchOption;


    public static SearchFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(TEXT_ARG, text);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment, container, false);
        findViews(v);
        addListeners();

        Bundle args = getArguments();
        String text = args.getString(TEXT_ARG);

        executeRequestExample(text);

        return v;
    }

    private void findViews(View v) {
        searchList = (ListView)v.findViewById(R.id.now_playing_search_fragment);
        searchArrow = (ImageButton)v.findViewById(R.id.main_search_arrow);
        searchOption = (EditText)v.findViewById(R.id.main_search_txt);
    }

    private void addListeners() {

        searchArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = searchOption.getText().toString();
                executeRequestExample(text);

            }
        });


    }


    private void executeRequestExample(String text) {

        new GetNowSearchAsyncTask(getActivity(), text){

            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                DLog.d(tag, "onResponseSuccess " + moviesResponse);

                List<Movie> movies = moviesResponse.getMovies();

                MoviesAdapter adapter = new MoviesAdapter((MainActivityScreen) getActivity(), movies);

                searchList.setAdapter(adapter);


            }

            @Override
            protected void onNetworkError() {
                DLog.d(tag, "onNetworkError ");
                Toast.makeText(getActivity(), "There is no internet connection!",Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

}
