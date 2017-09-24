package movies.flag.pt.moviesapp.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by ruiferrao on 14/09/2017.
 */

public class DownloadHelper {

    public static Bitmap downloadImage(String url){
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        return bitmap;
    }

}
