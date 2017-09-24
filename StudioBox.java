package movies.flag.pt.moviesapp;

import android.app.Application;
import android.util.Log;

import com.orm.SugarContext;

import movies.flag.pt.moviesapp.helpers.SharedPreferencesHelper;

/**
 * Created by ruiferrao on 21/09/2017.
 */

public class StudioBox extends Application {

    private static final String TAG = StudioBox.class.getSimpleName();

    private static StudioBox instance;

    public static StudioBox getInstance(){
        return instance;
    }

    private int numberOfResumedScreens;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        instance = this;
        SharedPreferencesHelper.init(this);
        SugarContext.init(this);
    }

    public void addOnResumeScreen(){
        numberOfResumedScreens++;
    }

    public void removeOnResumeScreen(){
        numberOfResumedScreens--;
    }

    public boolean applicationIsInBackground(){
        return numberOfResumedScreens == 0;
    }

}

