package Entity_types;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_properties.Orbiting;
import Entity_properties.Selectable;
import Entity_properties.Touchable;
import Entity_types.BaseEntitys.Entity;
import OrbitCalcs.OrbitData;
import RenderHooks.SatelliteRenderHook;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import states.State;

/**
 * Created by Chris on 10/04/2016.
 */
public class Asteroid extends Entity implements Selectable, Touchable, Orbiting{



    SatelliteRenderHook srh = null;

    public OrbitData orbit = null;
    public Boolean   orbiting= false;






    public Asteroid
            (SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference , GLSLShader shader , OrbitData orbit) {

        super(pos, moment, state, bodyReference);


        this.orbit=orbit;
        srh = new SatelliteRenderHook(this,shader);
        body.setOrigin(new SimpleVector(0,0,0));
        body.translate(position);


        body.setShader(shader);
        body.setRenderHook(srh);

    }




    public boolean isSelectable() {
        // TODO Auto-generated method stub
        return true;
    }



    public boolean isCollidable() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEthereal() {
        return false;
    }

    @Override
    public SimpleVector collisionPoint() {
        return this.position;
    }

    @Override
    public float collisionArea() {
        return 4;
    }

    @Override
    public void collisionEffect(Entity x) {

    }


    @Override
    public void onSingleTap(RayPickPoints points ,MotionEvent me) {

    }

    @Override
    public void onDoubleTap(RayPickPoints p ,MotionEvent me) {

        SingletonObjects.cameraCursor.setFollow(this);

    }

    @Override
    public void actionDown(RayPickPoints points ,MotionEvent me) {

    }

    @Override
    public void actionUp() {

    }

    @Override
    public void actionMove(MotionEvent me, RayPickPoints points) {

    }

    @Override
    public void setScaleFactor(ScaleGestureDetector m) {

    }

    @Override
    public void setOrbit(OrbitData orbit) {
        orbit=orbit;
    }

    @Override
    public OrbitData getOrbit() {
        return orbit;
    }

    @Override
    public boolean isOrbiting() {
        return orbiting;
    }

    @Override
    public Boolean isTargetable() {
        return true;
    }

    @Override
    public boolean canFire() {
        return false;
    }

    @Override
    public float firingDistance() {
        return 0;
    }

    @Override
    public void fire() {
        //doesn't do anything ,it's an asteroid
    }
}
