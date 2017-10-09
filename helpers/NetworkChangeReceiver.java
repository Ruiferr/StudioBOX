package movies.flag.pt.moviesapp.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class NetworkChangeReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, final Intent intent) {

        if(checkInternet(context))
        {
            Log.d(TAG, "wifi is available");
        }

    }

    public boolean checkInternet(Context context) {
        NetworkHelper serviceManager = new NetworkHelper(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }

}