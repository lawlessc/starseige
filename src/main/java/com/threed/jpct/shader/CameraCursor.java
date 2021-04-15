package com.threed.jpct.shader;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;

import Entity_properties.Touchable;
import Entity_types.BaseEntitys.Entity;
import Entity_types.SkySphere;
import baseinterfacesclasses.RayPick;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;

//controls the camera, act's as a cursor too THE CAMERA is a cursor
public class CameraCursor  {

    private Entity toFollowSelected = null;//The entity the Camera should center on and follow.
    private Touchable touched = null; //I will need to add touching/ drag reachts to glyphs for this to work
    SimpleVector camCenter = new SimpleVector(0, 0, 0);

    private float horizontalAngle = 90;
    private float verticalAngle = 90;
    private float scaleFactor = 5.f;

    private MODE currentMode =MODE.STARTUP;

    private float touchTurn = 0;
    private float touchTurnUp = 0;
    private float xpos = -1;
    private float ypos = -1;

    public enum MODE {
        STARTUP,//we ignore input
        CAMERAORBIT,//Allow rotation about an object
        OBJECT //Camera scaling should be locked. preferably to a predefined distance suitable for object and menu
    }

   public  CameraCursor() {
    //    pickcmd = new RayPick(referencetomain);
       currentMode =MODE.STARTUP;
    }

    public void cameraFollow() {

        if (toFollowSelected == null) {
            SingletonObjects.cam.setPosition(positionRotation(new SimpleVector(0, 0, 0)));
            SingletonObjects.cam.lookAt(new SimpleVector(0, 0, 0));
        } else {
            //System.out.println("following obj="+SingletonObjects.cam.getPosition());
            //this needs to be fixed, to use an offset, or there will be floating point errors.
            SingletonObjects.cam.setPosition(positionRotation(new SimpleVector(toFollowSelected.position)));
            SingletonObjects.cam.lookAt(new SimpleVector(toFollowSelected.position));
            // System.out.println("campos  obj="+SingletonObjects.cam.getPosition());
            //We look at an object.
            if (toFollowSelected.isAlive == false || toFollowSelected instanceof SkySphere) {
                toFollowSelected = null;
            }
        }
    }

    public void setFollow(Entity ent) {
        toFollowSelected = ent;
    }
    public void setHorizontal(float h) {
        horizontalAngle = h;
    }

    public void setScaleFactor(ScaleGestureDetector m) {

        if(currentMode !=null) {
            switch (currentMode) {
                case CAMERAORBIT:
                    scaling(m);
                    break;
                case OBJECT:
                    touched.setScaleFactor(m);
                    break;
            }

        }
    }

    public void scaling(ScaleGestureDetector m)
    {

        scaleFactor *= m.getScaleFactor();
        scaleFactor = Math.max(0.02f, Math.min(scaleFactor, 56.0f));
    }

    public void setVertical(float v) {
        verticalAngle = v;
    }

    public SimpleVector positionRotation(SimpleVector focalPoint) {
        SimpleVector direction = new SimpleVector(
                Math.cos(verticalAngle) * Math.sin(horizontalAngle),
                Math.sin(verticalAngle),
                Math.cos(verticalAngle) * Math.cos(horizontalAngle)
        );
        direction.scalarMul(400 / (scaleFactor));
        focalPoint.sub(direction);
        return focalPoint;
    }

    ///THIS AND OTHERS LIKE IT NEED TO BE MOVED TO A SEPERATE CAMERA CLASS CONTAINED HERE.
    public void setOrbit()
    {
        if (touchTurn != 0) {
            if (touchTurn > 0) {
                horizontalAngle += 0.05;
            } else if (touchTurn < 0) {
                horizontalAngle -= 0.05;
            }
            setHorizontal(horizontalAngle);
            touchTurn = 0;
        }

        if (touchTurnUp != 0) {
            if (touchTurnUp > 0) {
                verticalAngle += 0.05;
            } else if (touchTurnUp < 0) {
                verticalAngle -= 0.05;

            }
        }
    }

    public void setMode(RayPickPoints returnObj)
    {
           if ((returnObj.objectHit) == null)//race condition?
           {
               currentMode = currentMode.CAMERAORBIT;
           } else {
               currentMode = currentMode.OBJECT;
               touched = returnObj.objectHit;
           }
    }

    public void onDoubleTap(MotionEvent me, FrameBuffer fb, float left, float top) {

        RayPickPoints returnObj = RayPick.touch(me, fb,left ,top);
        if(returnObj !=null) {
            setMode(returnObj);////////////////////////////////////////////////

            switch (currentMode) {
                case CAMERAORBIT:
                    //   System.out.println("CAMERAORBIT");
                    break;
                case OBJECT:
                    touched.onDoubleTap(returnObj,me);
//                if(touched instanceof Entity) {
//                    Entity ObjectHit = (Entity) returnObj.objectHit;
//                    //    setFollow(ObjectHit);
//                    //    }
//                    //   System.out.println("ENTITY");
//                    //   break;
//                    SimpleVector expDirection = returnObj.points[0];
//                    expDirection = ObjectHit.position.calcSub(expDirection);
//                    expDirection.normalize();
//                    expDirection.x = -expDirection.x;
//                    expDirection.y = -expDirection.y;
//                    expDirection.z = -expDirection.z;
//                    Main.SingletonObjects.object_factory.createExplosion(((RayPickPoints) returnObj).points[0], expDirection);
//                }
                    break;
            }
        }

    }

    public void onSingleTapConfirmed(MotionEvent me, FrameBuffer fb, float left, float top) {


        RayPickPoints returnObj = RayPick.touch(me, fb,left ,top);
        if(returnObj !=null) {
            setMode(returnObj);////////////

            switch (currentMode) {
                case CAMERAORBIT:
                    //System.out.println("CAMERAORBIT");
                    break;
                case OBJECT:
                    touched.onSingleTap(returnObj,me);
                    break;
            }
        }

    }

    public void actionDown(MotionEvent me,FrameBuffer fb, float left, float top)
    {
        RayPickPoints returnObj = RayPick.touch(me, fb,left ,top);
        if(returnObj !=null) {
            setMode(returnObj);////////////

            switch (currentMode) {
                case CAMERAORBIT:
                    downAction(me);
                    break;
                case OBJECT:
                    touched.actionDown(returnObj,me);
                    break;
            }
        }
    }

    public void downAction(MotionEvent me)
    {
        xpos = me.getX();
        ypos = me.getY();
    }

    public void actionUp(MotionEvent me,FrameBuffer fb)
    {
        switch (currentMode) {
            case CAMERAORBIT:
                upAction();
                break;
            case OBJECT:
                touched.actionUp();
                currentMode = currentMode.CAMERAORBIT;
                touched = null;
                break;
        }
    }

    public void upAction()
    {
        xpos = -1;
        ypos = -1;
        touchTurn = 0;
        touchTurnUp = 0;
    }


    public void actionMove(MotionEvent me,FrameBuffer fb, float left, float top)
    {
        RayPickPoints returnObj = RayPick.touch(me, fb,left ,top);
        if(returnObj !=null) {
            switch (currentMode) {
                case CAMERAORBIT:
                    rotationAction(me);
                    break;
                case OBJECT:
                    touched.actionMove(me, returnObj);
                    break;
            }
        }
    }

    public void rotationAction(MotionEvent me)
    {
        float xd = me.getX() - xpos;
        float yd = me.getY() - ypos;
        xpos = me.getX();
        ypos = me.getY();
        touchTurn = xd / 100f;
        touchTurnUp = yd / 100f;
    }

//    public void rotationAction(MotionEvent me)
//    {
//        float xd = me.getX() - xpos;
//        float yd = me.getY() - ypos;
//        xpos = me.getX();
//        ypos = me.getY();
//        touchTurn = xd / -100f;
//        touchTurnUp = yd / -100f;
//    }

    public void rotationActionByPlanet(MotionEvent me)
    {
        float xd = me.getX() - xpos;
        float yd = me.getY() - ypos;
        xpos = me.getX();
        ypos = me.getY();
        touchTurn = xd / 100f;
        touchTurnUp = yd / 100f;
    }


    //for rotating orbit sphere for now.
    void startDrag(){}
    void duringDrag(){}
    void stopDrag(){}
}
