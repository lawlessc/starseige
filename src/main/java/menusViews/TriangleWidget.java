package menusViews;

import com.threed.jpct.Polyline;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 25/02/2016.
 * Widjet draws polylines from center to object to help spacial awareness
 */
public class TriangleWidget extends  Widget {

    Polyline HorizontalCenterToCursor;
    Polyline PlaneToCursor;
    Polyline CenterToCursor;
    RGBColor baseColour= new RGBColor(121 ,171,220);



    public TriangleWidget(SimpleVector position)
    {

        SimpleVector xneedleStart = new SimpleVector (0,0, 0);
        SimpleVector xneedleEnd = new SimpleVector (position.x, position.y, position.z);
        SimpleVector[] xneedlePoints= new SimpleVector[]{xneedleStart,xneedleEnd};
        SimpleVector planeStart = new SimpleVector (position.x,0, position.z);
        SimpleVector planeEnd = new SimpleVector (position.x, position.y, position.z);
        SimpleVector[] planec= new SimpleVector[]{planeStart,planeEnd};
        SimpleVector horziplaneStart = new SimpleVector (0,0, 0);
        SimpleVector horziplaneEnd = new SimpleVector (position.x, position.y, position.z);
        SimpleVector[] planeh= new SimpleVector[]{horziplaneStart,horziplaneEnd};




        HorizontalCenterToCursor = new Polyline(planeh, baseColour);
        PlaneToCursor  = new Polyline(planec, baseColour);
        CenterToCursor = new Polyline(xneedlePoints, baseColour);




        SingletonObjects.menumanager.addLine(HorizontalCenterToCursor);
        SingletonObjects.menumanager.addLine(PlaneToCursor);
        SingletonObjects.menumanager.addLine(CenterToCursor);


    }


    public  void update(SimpleVector newPosition)
    {

      //  SimpleVector startPos =  new SimpleVector(newPosition);
        SimpleVector xneedleStart = new SimpleVector (0,0, 0);
        SimpleVector xneedleEnd = new SimpleVector (newPosition.x, newPosition.y, newPosition.z);
        SimpleVector[] xneedlePoints= new SimpleVector[]{xneedleStart,xneedleEnd};
        CenterToCursor.update(xneedlePoints);

        SimpleVector planeStart = new SimpleVector (newPosition.x,0, newPosition.z);
        SimpleVector planeEnd = new SimpleVector (newPosition.x, newPosition.y, newPosition.z);
        SimpleVector[] planec= new SimpleVector[]{planeStart,planeEnd};
        PlaneToCursor.update(planec);

        SimpleVector horziplaneStart = new SimpleVector (0,0, 0);

        SimpleVector horziplaneEnd = new SimpleVector (newPosition.x, 0, newPosition.z);
        SimpleVector[] planeh= new SimpleVector[]{horziplaneStart,horziplaneEnd};
        HorizontalCenterToCursor.update(planeh);


    }



    @Override
    public void closeWidget() {


        SingletonObjects.menumanager.addToRemovalList(HorizontalCenterToCursor);
        SingletonObjects.menumanager.addToRemovalList(PlaneToCursor);
        SingletonObjects.menumanager.addToRemovalList(CenterToCursor);


    }
}
