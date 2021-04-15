package menusViews;

import com.threed.jpct.Polyline;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import OrbitCalcs.OrbitData;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 24/02/2016.
 */
public class OrbitWidget  extends Widget {


    Polyline OrbitPredictionLine;
    OrbitData orbitData;
    RGBColor  baseColor = new RGBColor(121 ,171,220);
    RGBColor nightgreen= new RGBColor(51 ,244,0);


   public OrbitWidget(OrbitData orbitData)
   {

       baseColor = RGBColor.BLUE;
       this.orbitData=orbitData;

       OrbitPredictionLine = new Polyline(setOrbitP(orbitData),baseColor);
       OrbitPredictionLine.setWidth(10f);




       SingletonObjects.menumanager.addLine(OrbitPredictionLine);
   }




    @Override
    public void closeWidget() {

        SingletonObjects.menumanager.addToRemovalList(OrbitPredictionLine);

    }

   //Call this when orbit is changed to recalculate. DO NOT AT THIS TO CONSTANTLY UPDATING LOOP
    public void Update()//
    {
        OrbitPredictionLine.setWidth(10f);

        OrbitPredictionLine.update(setOrbitP(orbitData));

        if(orbitData.functionalOrbit == false)
        {
            OrbitPredictionLine.setColor(RGBColor.RED);

        }
        else
        {
            OrbitPredictionLine.setColor(baseColor);
        }


        if(SingletonObjects.processHandler.nightmode)
        {
            OrbitPredictionLine.setColor(nightgreen);
            OrbitPredictionLine.setWidth(2f);
        }


    }


    public SimpleVector[] setOrbitP(OrbitData orbitdata)
    {

        SimpleVector orbarr[] = orbitdata.orbitPointsAngledD();
        return orbarr;
    }

















}
