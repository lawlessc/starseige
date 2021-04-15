package menusViews;

import com.threed.jpct.Polyline;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import java.util.ArrayList;

import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 10/02/2016.
 */
public class GridWidget extends Widget {



    Polyline firstRing;
    Polyline secondRing;
    Polyline thirdRing;

    Polyline flatone;
    Polyline flattwo;
    int transparancy= 0;
    float width= 10f;
    RGBColor baseColour= new RGBColor(121 ,171,220);
    RGBColor nightColour= new RGBColor(255 ,176,0);



    public GridWidget()
    {
        firstRing = new Polyline(circlePoints(10), baseColour);
        secondRing = new Polyline(circlePoints(15), baseColour);
        thirdRing = new Polyline(circlePoints(20), baseColour);

        SimpleVector[] xp= new SimpleVector[]{new SimpleVector(-20,0,0),new SimpleVector(20,0,0) };
        SimpleVector[] zp= new SimpleVector[]{new SimpleVector(0,0,-20),new SimpleVector(0,0,20) };


        flatone = new Polyline(xp, baseColour);
        flattwo = new Polyline(zp, baseColour);


         firstRing.setTransparencyMode(transparancy);
         secondRing.setTransparencyMode(transparancy);
         thirdRing.setTransparencyMode(transparancy);
         flatone.setTransparencyMode(transparancy);
         flattwo.setTransparencyMode(transparancy);


        firstRing.setWidth(width);
        secondRing.setWidth(width);
        thirdRing.setWidth(width);
        flatone.setWidth(width);
        flattwo.setWidth(width);


        if(SingletonObjects.processHandler.nightmode)
        {
            firstRing.setColor(nightColour);
            secondRing.setColor(nightColour);
            thirdRing.setColor(nightColour);
            flatone.setColor(nightColour);
            flattwo.setColor(nightColour);

            firstRing.setWidth(2f);
            secondRing.setWidth(2f);
            thirdRing.setWidth(2f);
            flatone.setWidth(2f);
            flattwo.setWidth(2f);



        }



        SingletonObjects.menumanager.addLine(firstRing);
        SingletonObjects.menumanager.addLine(secondRing);
        SingletonObjects.menumanager.addLine(thirdRing);

        SingletonObjects.menumanager.addLine(flatone);
        SingletonObjects.menumanager.addLine(flattwo);

    }



    public  void update()
    {


        if(SingletonObjects.processHandler.nightmode)
        {
             firstRing.setColor(nightColour);
             secondRing.setColor(nightColour);
             thirdRing.setColor(nightColour);
             flatone.setColor(nightColour);
             flattwo.setColor(nightColour);

            firstRing.setWidth(2f);
            secondRing.setWidth(2f);
            thirdRing.setWidth(2f);
            flatone.setWidth(2f);
            flattwo.setWidth(2f);



        }
        else
        {
            firstRing.setColor(baseColour);
            secondRing.setColor(baseColour);
            thirdRing.setColor(baseColour);
            flatone.setColor(baseColour);
            flattwo.setColor(baseColour);

            firstRing.setWidth(10f);
            secondRing.setWidth(10f);
            thirdRing.setWidth(10f);
            flatone.setWidth(10f);
            flattwo.setWidth(10f);

        }




    }

    @Override
    public void closeWidget() {


        SingletonObjects.menumanager.addToRemovalList(firstRing);
        SingletonObjects.menumanager.addToRemovalList(secondRing);
        SingletonObjects.menumanager.addToRemovalList(thirdRing);
        SingletonObjects.menumanager.addToRemovalList(flatone);
        SingletonObjects.menumanager.addToRemovalList(flattwo);

    }








    public SimpleVector[] circlePoints(float radius)
    {
        float pit =  6.28f;//2.0f*3.14f;
        // float radius = 10f;
        float x, z;
        ArrayList<SimpleVector> vectors_ = new ArrayList<SimpleVector>(100);

        for (float t = 0; t <= pit; t += 0.1) {
            x = radius*((float)Math.cos(t));//+ x_0;//centrepoint
            z = radius*((float)Math.sin(t)) ;//+ y_0;

            vectors_.add(new SimpleVector(x,0,z));
        }


        {
            x = radius*((float)Math.cos(0));//+ x_0;//centrepoint
            z = radius*((float)Math.sin(0)) ;//+ y_0;
            vectors_.add(new SimpleVector(x,0,z));
        }



        SimpleVector[] vects = new SimpleVector[vectors_.size() ];
        vectors_.toArray(vects);return vects;
    }



}
