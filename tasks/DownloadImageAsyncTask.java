package movies.flag.pt.moviesapp.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import movies.flag.pt.moviesapp.helpers.DownloadHelper;

/**
 * Created by ruiferrao on 14/09/2017.
 */

public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap>{

    private static final String tag = DownloadImageAsyncTask.class.getSimpleName();

    private ImageView image;

    public DownloadImageAsyncTask(ImageView image){
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        String url = params[0];

        return DownloadHelper.downloadImage(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        image.setImageBitmap(bitmap);
    }
}
