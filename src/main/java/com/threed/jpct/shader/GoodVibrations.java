package com.threed.jpct.shader;

import android.os.Vibrator;
/**
 * Created by Chris on 28/01/2018.
 */

public class GoodVibrations {


    Boolean vibrations_allowed= true;
    Vibrator v;// = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


    public GoodVibrations(Vibrator vi)
    {

        v= vi;

    }

    public void setVibrationOn(Boolean b)
    {
        vibrations_allowed =b;
    }




    public void ExplosionVibration()
    {
        v.vibrate(50);
    }




}
