package baseinterfacesclasses;

import com.threed.jpct.SimpleVector;

import Entity_properties.Touchable;

/**
 * Created by lawless on 22/04/2015.
 */
public class RayPickPoints {

   public SimpleVector rayStartPoint = null;
   public SimpleVector rayDirection  = null;

   public Touchable objectHit =null;
   public SimpleVector[] points;//intersection points hit




    public RayPickPoints(SimpleVector startp, SimpleVector direction)
    {

       this.rayStartPoint =   startp;
       this.rayDirection  = direction;
    }


    public RayPickPoints(Touchable obj, SimpleVector[] sv)
    {
        this.objectHit = obj;
        this.points    = sv;
    }


    public void setObjectPoints(Touchable obj, SimpleVector[] sv)
    {

        this.objectHit = obj;
        this.points    = sv;
    }



}
