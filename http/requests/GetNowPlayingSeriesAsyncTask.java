package movies.flag.pt.moviesapp.http.requests;

import android.content.Context;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.http.entities.SeriesResponse;

/**
 * Created by ruiferrao on 14/09/2017.
 */

public abstract class GetNowPlayingSeriesAsyncTask extends ExecuteRequestAsyncTask<SeriesResponse> {

    private static final String PATH = "/tv/popular";
    private static final String LANGUAGE_KEY = "language";



    public GetNowPlayingSeriesAsyncTask(Context context) {
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
    protected Class<SeriesResponse> getResponseEntityClass() {
        return SeriesResponse.class;
    }
}
