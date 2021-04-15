package MenuObjects;

import android.view.MotionEvent;

import com.threed.jpct.SimpleVector;

import baseinterfacesclasses.RayPick;
import baseinterfacesclasses.RayPickPoints;

/**
 * Created by lawless on 04/10/2015.
 */
public class UIPuc extends UIButton {


    public SimpleVector planeOriginHorizontal = new SimpleVector(1000,0,1000);


    public SimpleVector planeNormHorizontal= new SimpleVector(0,1,0);




    enum Planes {
       Horizontal,
        Vertical

    }

    Planes plane;
    public Boolean  lockPlane = false;


    public UIPuc()
    {
    }

    public void setupPUC(SpatialGlyph glyph,SimpleVector position,
                                  SimpleVector mainColour, SimpleVector pressedColour,float
                         collision_box_size)
    {
        this.pressedColour=pressedColour;
        this.mainColour  = mainColour;
        this.basecolour  = mainColour;
        this.position    = position;

        this.attachedtoObject= false;
        this.glyph   = glyph;

        this.size  = collision_box_size;
        this.thickness = 1.6f;

        this.alligned = Alignment.Free;
        this.disabledFor = 0;
        this.glyph.setBilloboard(false);
        this.glyph.plane.setOrientation(new SimpleVector(0, 1, 0), new SimpleVector(1, 0, 0));
        this.glyph.plane.setCulling(false);

        fadeInNow = true;
        transparency=0f;

    }

    @Override
    public void actionMoveFunction(MotionEvent me, RayPickPoints returnObj) {


           SimpleVector intersects = RayPick.linePlaneIntersection(returnObj.rayDirection, returnObj.rayStartPoint, planeNormHorizontal, planeOriginHorizontal);
           if (intersects != null) {
               position = intersects;
            //   planeOriginVertical.x = position.x;
           }

    }


    @Override
    public void actionUpFunction() {
        super.actionUpFunction();
        lockPlane=false;

    }






}
