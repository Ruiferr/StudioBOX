package movies.flag.pt.moviesapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import movies.flag.pt.moviesapp.constants.PreferenceIds;


public final class SharedPreferencesHelper {

    private static final String TAG = SharedPreferencesHelper.class.getSimpleName();

    private static SharedPreferences sharedPreferences;

    public static void init(Context context){
        sharedPreferences = context.
                getSharedPreferences(PreferenceIds.PREFERENCES_FILE_NAME,
                        Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public static void savePreference(String key, boolean value){
        Log.d(TAG, String.format("savePreference key = %s value = %s", key, value));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void savePreference(String key, int value){
        Log.d(TAG, String.format("savePreference key = %s value = %s", key, value));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void savePreference(String key, String value){
        Log.d(TAG, String.format("savePreference key = %s value = %s", key, value));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void removePreference(String key){
        Log.d(TAG, String.format("removePreference key = %s", key));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public static String getStringPreference(String key) {
        return sharedPreferences.getString(key, null);
    }

    public static int getIntPreference(String key, int defaultValue){
        return sharedPreferences.getInt(key, defaultValue);
    }
}