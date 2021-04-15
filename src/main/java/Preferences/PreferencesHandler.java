package Preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Chris on 10/09/2016.
 */
public class PreferencesHandler {


    SharedPreferences settings;
    SharedPreferences.Editor editor;




 public PreferencesHandler(String preferencesLibrary, Activity activity)
    {


        settings = activity.getSharedPreferences(preferencesLibrary, Context.MODE_PRIVATE);
        editor = settings.edit();
        updatePrefencesVariables();
    }




    //Make's a call to update all variables, we override this
    public void updatePrefencesVariables()
    {



    }


}
