package Entity_properties;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import baseinterfacesclasses.RayPickPoints;

/**
 * Created by lawless on 09/08/2015.
 */
public interface Touchable {

    void  onSingleTap(RayPickPoints points,MotionEvent me);
    void  onDoubleTap(RayPickPoints points,MotionEvent me);
    void  actionDown(RayPickPoints points,MotionEvent me);
    void  actionUp();
    void  actionMove(MotionEvent me, RayPickPoints points);

    void setScaleFactor(ScaleGestureDetector m);
}
