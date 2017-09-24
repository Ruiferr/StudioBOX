package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;

/**
 * Created by Ricardo Neves on 19/10/2016.
 *
 * Example for getting now playing movies
 */

public abstract class GetNowPlayingMoviesAsyncTask extends ExecuteRequestAsyncTask<MoviesResponse> {

    private static final String PATH = "/movie/now_playing";
    private static final String LANGUAGE_KEY = "language";


    public GetNowPlayingMoviesAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected void addQueryParams(StringBuilder sb) {
        addQueryParam(sb, LANGUAGE_KEY, context.getString(R.string.language));
    }

    @Override
    protected Class<MoviesResponse> getResponseEntityClass() {
        return MoviesResponse.class;
    }
}