package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.MoviesResponse;

/**
 * Created by ruiferrao on 15/09/2017.
 */

public abstract class GetNowSearchAsyncTask extends ExecuteRequestAsyncTask<MoviesResponse>{

    private static final String PATH = "/search/movie";
    private static final String LANGUAGE_KEY = "language";
    public String QUERY = "avengers";

    public GetNowSearchAsyncTask(Context context, String text) {
        super(context);
        this.QUERY = text;
    }

    @Override
    protected String getPath() {
        return PATH;

    }

    @Override
    protected void addQueryParams(StringBuilder sb) {
        addQueryParam(sb, LANGUAGE_KEY, context.getString(R.string.language));
        addQueryParam(sb, "query", QUERY);
    }

    @Override
    protected Class<MoviesResponse> getResponseEntityClass() {
        return MoviesResponse.class;
    }

}
