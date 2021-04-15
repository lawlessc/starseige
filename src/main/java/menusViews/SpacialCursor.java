package menusViews;

import android.view.MotionEvent;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import MenuObjects.UIButton;
import MenuObjects.UIPuc;
import baseinterfacesclasses.RayPick;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import graphicsSetup.LoadModel;

/**
 * Created by Chris on 30/03/2016.
 */
public class SpacialCursor extends Widget {
    Spacial pucbutton = new Spacial();
    Vertical vertbutton = new Vertical();

    public SpacialCursor(SimpleVector startPosition) {
        SingletonObjects.hudFactory.createPucButton(pucbutton, new SimpleVector(1, 1, 1),
                new SimpleVector(0.5, 1, 0.5), "disc", startPosition, 5.0f,0.9f);
        pucbutton.size = 4;


        SimpleVector arrowPos = new SimpleVector(startPosition);
        arrowPos.y = arrowPos.y-3;

        SingletonObjects.hudFactory.createPositionInSpaceButton(vertbutton, new SimpleVector(0.6, 0, 0),
                new SimpleVector(0.5, 1, 0.5),
                LoadModel.copy(SingletonObjects.object_factory.object_holder.verticalArrow)
                ,arrowPos, 5.0f,0.5f);
        vertbutton.size = 3;

        pucbutton.setPuc(vertbutton);
        vertbutton.setPuc(pucbutton);
    }

    @Override
    public void closeWidget() {
        SingletonObjects.menumanager.addToRemovalList(pucbutton);
        SingletonObjects.menumanager.addToRemovalList(vertbutton);
    }

    public SimpleVector getPosition()
    {
        return pucbutton.position;
    }

    public class Spacial extends UIPuc
    {
        Vertical verticalb;
        public void setPuc(Vertical verticalButton) {
            this.verticalb = verticalButton;
        }

        @Override
        public  void actionMoveFunction(MotionEvent me, RayPickPoints points) {
            super.actionMoveFunction(me, points);
            verticalb.position.x = position.x;
            verticalb.position.z = position.z;
            OnMove();
        }

        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
            OnSingleTap();
        }

        @Override
        public  void actionDoubleTapFunction(RayPickPoints points) {
            OnDoubleTap();
        }
    }

    public class Vertical extends UIButton {
        public SimpleVector planeOriginVertical = new SimpleVector(0, 1000, 1000);
        public SimpleVector planeNormalVertical = new SimpleVector(1, 0, 0);
        public UIPuc puc;

        public void setPuc(UIPuc puc) {
            this.puc = puc;
        }

        @Override
        public  void actionMoveFunction(MotionEvent me,RayPickPoints points) {


            SimpleVector intersects2 = RayPick.linePlaneIntersection(points.rayDirection, points.rayStartPoint, planeNormalVertical, planeOriginVertical);
            if (intersects2 != null) {
                position.y = intersects2.y;
                puc.planeOriginHorizontal.y = position.y+3;
                puc.position.y =  puc.planeOriginHorizontal.y;
                OnMove();
            }

        }

        @Override
        public void update()
        {
            super.update();

            SimpleVector camdir = new SimpleVector(SingletonObjects.cam.getPosition());
            this.glyph.plane.setOrientation(new SimpleVector(-camdir.x, 0, -camdir.y), new SimpleVector(0, 1, 0));

        }

    }

    //Below functions should be Overriden
    public void OnMove()
    {}
    public void OnDoubleTap()
    {}
    public void OnSingleTap()
    {}
}