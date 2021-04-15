package OrbitCalcs;

import com.threed.jpct.SimpleVector;

import java.util.ArrayList;

import Entity_properties.Orbiting;
import Entity_types.BaseEntitys.Entity;
import states.EntityStates;

/**
 * Created by lawless on 12/12/2015.
 */
public class OrbitData {


   public OrbitData    newOrb;//This is for orbital segue.
   public SimpleVector coneTop;

   public SimpleVector coneBase;
    public float        anglevalue;//20f works ok for now
    public float        conebaseDistance;
    public float pit =  6.28f;//2.0f*3.14f;
    public  float t =0;

   public float averagedelta =0.1f;
   public float averagedeltaOffset =0.0f;
   public boolean functionalOrbit = true;

    public  SimpleVector apocenter;
    public  SimpleVector pericenter;

    public SimpleVector OffsetCenter;


    public  float inclination;
    public  SimpleVector inclinationAxis;
    public  SimpleVector lastPosition;


    SimpleVector pucposition;

    public boolean orbitdirection;//true= prograde  false= retrograde


//    public enum orbitdirection
//    {
//        retrograde,
//        prograde
//    }


   //public  orbitdirection  dir =orbitdirection.prograde; //default




   public  OrbitData(SimpleVector vertex  ,float angle,float inclination,SimpleVector pucposition)
    {
        this.coneTop = vertex;

        this.anglevalue=angle;
        this.inclination=inclination;
        this.pucposition= pucposition;

        coneBase = new SimpleVector(0,0,0);
        coneBase.sub(coneTop);
        coneBase.normalize();

        SimpleVector up = new SimpleVector(0,1,0);
        SimpleVector apo = new SimpleVector(pucposition);
        apo.normalize();
        inclinationAxis = up.calcCross(apo);
        inclinationAxis.normalize();
        orbitPointsAngled();



        //NOTE
        //NOTE
        //NOTE
        //NOTE
        //NOTE
        // I MAY NEED TO MESS WITH THIS UNTIL I GET IT RIGHT.
       if(orbitdirection) {
           t = 0.1f;
       }
       else
       {
           t = 0.9f;
       }
    }


    public void  setOrbitData(SimpleVector vertex  ,float angle,float inclination,SimpleVector pucposition)
    {
        this.coneTop = vertex;

        this.anglevalue=angle;
        this.inclination=inclination;
        this.pucposition= pucposition;

        coneBase = new SimpleVector(0,0,0);
        coneBase.sub(coneTop);
        coneBase.normalize();


        SimpleVector up = new SimpleVector(0,1,0);
        SimpleVector apo = new SimpleVector(pucposition);
        apo.normalize();
        inclinationAxis = up.calcCross(apo);
        inclinationAxis.normalize();

        orbitPointsAngled();
    }


    public OrbitData returnCopy()
    {
        OrbitData xz = new  OrbitData( new SimpleVector( coneTop) , anglevalue, inclination, new SimpleVector( pucposition));

        xz.setDirection(this.getDirection());
        xz.t=t;
        return xz;
    }


    public void setDirection(boolean direction)
    {
        orbitdirection = direction;
    }

    public Boolean getDirection()
    {return orbitdirection;}




    public void setPrograde()
    {
        orbitdirection =true;
    }

    public void setRetrograde()
    {
        orbitdirection = false;
    }


    public boolean isPrograde()
    {
        if(orbitdirection) {
        return  true;
        }
        return  false;
    }


    public void setConeTop(SimpleVector vertex){this.coneTop=vertex;}






    public SimpleVector[] orbitPointsAngled()
    {
        functionalOrbit= true;

        float x, z;
        SimpleVector  lastRes = new SimpleVector();
        ArrayList<SimpleVector> Ovectors_ = new ArrayList<SimpleVector>(100);
        ArrayList<SimpleVector> nonRotated_ = new ArrayList<SimpleVector>(100);

        for (float ti = 0; ti <= pit; ti += 0.01) {

            x = ((float)Math.cos(ti));
            z = ((float)Math.sin(ti)) ;


            SimpleVector linedir = new SimpleVector(-x,0,-z);
            linedir.normalize();
            Result result = ConePick.getIntersectionsConeCone(new SimpleVector(x, 0, z) , linedir ,coneTop
                    ,coneBase,20f);





            SimpleVector[] intersections = result.point;

         //   calculateOrbitData(intersections); // calculate this BEFORE we rotate the points.

            if(result.type ==2 /*|| result.type ==4 || result.type ==3 */) {
                if (intersections != null) {
                    if (intersections[0] != null) {

                        nonRotated_.add(new SimpleVector(intersections[0]));
                        intersections[0].rotateAxis(inclinationAxis,inclination);
                        Ovectors_.add(intersections[0]);
                        lastRes = new SimpleVector(intersections[0]);



                    }
                }
            }
            else
            {
                functionalOrbit = false;
                Ovectors_.add(lastRes);
            }

        }

        SimpleVector vects[] = new SimpleVector[Ovectors_.size() ];
        SimpleVector nonrotvects[] = new SimpleVector[nonRotated_.size() ];

        Ovectors_.toArray(vects);
        nonRotated_.toArray(nonrotvects);
        calculateOrbitData(nonrotvects);

        Ovectors_.toArray(vects);
      // calculateOrbitPeriod(vects);
        return vects;
    }


    public SimpleVector[] orbitPointsAngledD()
    {

        functionalOrbit= true;

        float x, z;
        SimpleVector  lastRes = new SimpleVector();
        ArrayList<SimpleVector> Ovectors_ = new ArrayList<SimpleVector>(100);
        ArrayList<SimpleVector> nonRotated_ = new ArrayList<SimpleVector>(100);

        for (float ti = 0; ti <= pit; ti += 0.01) {

            x = ((float)Math.cos(ti));
            z = ((float)Math.sin(ti)) ;


            SimpleVector linedir = new SimpleVector(-x,0,-z);
            linedir.normalize();
            Result result =ConePick.getIntersectionsConeCone(new SimpleVector(x, 0, z) , linedir ,coneTop
                    ,coneBase,20f);





            SimpleVector[] intersections = result.point;


            if(result.type ==2 /*|| result.type ==4 || result.type ==3 */) {
                if (intersections != null) {
                    if (intersections[0] != null) {

                        nonRotated_.add(new SimpleVector(intersections[0]));
                        intersections[0].rotateAxis(inclinationAxis,inclination);
                        Ovectors_.add(intersections[0]);
                        lastRes = new SimpleVector(intersections[0]);



                    }
                }
            }
            else
            {
                functionalOrbit = false;
                Ovectors_.add(lastRes);
            }

        }

        SimpleVector vects[] = new SimpleVector[Ovectors_.size() ];
        SimpleVector nonrotvects[] = new SimpleVector[nonRotated_.size() ];


        Ovectors_.toArray(vects);
        nonRotated_.toArray(nonrotvects);
        calculateOrbitData(nonrotvects);

        return vects;
    }




    public SimpleVector calculateApocenter(SimpleVector[] points)
    {
        SimpleVector center = new SimpleVector(0,0,0) ;
        SimpleVector Apocent = null;

        for (int i = 0; i < points.length; i++)
        {

            if(Apocent != null) {
                float dist = points[i].distance(center);
                float distap = Apocent.distance(center);
                if (dist > distap) {
                    Apocent = points[i];

                }
            }
            else
            {
                Apocent = points[i];
            }



        }

        return Apocent;
    }

    public SimpleVector calculatePericenter(SimpleVector[] points) {

        SimpleVector center = new SimpleVector(0,0,0) ;
        SimpleVector PeriCent = null;
        for (int i = 0; i < points.length; i++)
        {
            if(PeriCent != null) {
                float dist = points[i].distance(center);
                float distap = PeriCent.distance(center);
                if (dist < distap) {
                    PeriCent = points[i];
                }
            }
            else
            {
                PeriCent = points[i];
            }
        }
 return PeriCent;
    }



     public void calculateOrbitData( SimpleVector[] points)
     {
         apocenter= calculateApocenter(points);
         pericenter = calculatePericenter(points);
         calculateOrbitPeriod();

         OffsetCenter  = new SimpleVector(apocenter);
         OffsetCenter.add(pericenter);
         //OffsetCenter.scalarMul(0.8f);
     }



        public float calculateOrbitPeriod()


    {
        SimpleVector center = new SimpleVector(0,0,0) ;
        float sem  = apocenter.distance(center);
              sem  +=pericenter.distance(center);
         //sem = sem/2;
         float a = sem*sem*sem;
     //    float period= (float) Math.sqrt(a);
        averagedelta =pit/a;
       // averagedelta  = 0.002f;
        return 0.0f;
        //return averagedelta;
    }

    public float calculateDeltaChange()
    {
       if(lastPosition != null) {
           float distA = apocenter.distance(lastPosition); //closer to this go slower
           float distP = pericenter.distance(lastPosition);//closer to this go faster

           averagedeltaOffset =  ((distP-distA) * 0.04f)+0.001f;
          // System.out.println("DELTA OFFSET =");
       }
        return 0.0f;
    }




    public void orbitForSegue(OrbitData orb)
    {
        this.newOrb=orb;
    }

    //when a segue is complete this should return true;
    public boolean shiftToNewOrbit()
    {
        int a;
        a=0;
        a+=  changeToMatchConeTop();
        a+=  changeToMatchAngle();
        a+=  changeToMatchDistance();
        a+=  changeToMatchAngleAxis();

        coneBase = new SimpleVector(0,0,0);
        coneBase.sub(coneTop);
        coneBase.normalize();


        if(a >=4)
        {

            pucposition= newOrb.pucposition;
            return  true;
        }

        return false;
    }



    //TODO
    public int changeToMatchAngle()
    {


       float diff  = inclination-newOrb.inclination;


      // System.out.println("diff is " + diff);

        if(inclination > newOrb.inclination)
        {
            inclination -=0.01f;

          //  System.out.println("A");
        }
        else if(inclination < newOrb.inclination)
        {
            inclination +=0.01f;
          //  System.out.println("B");
        }

         if( (diff < 0.2f) || (diff > -0.2f))
        {
         //   System.out.println("C");
            inclination =newOrb.inclination;
            return 1;
        }


      return  0;
    }


    public int changeToMatchAngleAxis()
    {

        SimpleVector newaxis= new SimpleVector(newOrb.inclinationAxis);
        newaxis=  newaxis.normalize();
        newaxis.scalarMul(10);


        SimpleVector currentaxis= new SimpleVector(inclinationAxis);
      //  currentaxis.normalize();
        currentaxis=currentaxis.normalize();
        currentaxis.scalarMul(10);


        float dist =  currentaxis.distance(newaxis);

        if(dist> 0.5) {

            SimpleVector direction = newaxis.calcSub(currentaxis);

            direction.normalize();
            currentaxis.add(direction);
          //  currentaxis.normalize();
            currentaxis=currentaxis.normalize();
            inclinationAxis=currentaxis;
        }
        else
        {
            inclinationAxis = newOrb.inclinationAxis;
            return 1;
        }

        return  0;
    }

    public int changeToMatchConeTop()
    {

        float dist =  coneTop.distance(newOrb.coneTop);
        SimpleVector direction;
        if(dist > 0.2)
        {
            direction= coneTop.calcSub(newOrb.coneTop);
            direction.normalize();
            direction.scalarMul(0.005f);
            coneTop.sub(direction);
        }
        else
        {
            coneTop= new SimpleVector(newOrb.coneTop);
            return 1;
        }

        return 0;
    }


    public int changeToMatchDistance()
    {

        float diff  =conebaseDistance - newOrb.conebaseDistance;
        if(conebaseDistance > newOrb.conebaseDistance)
        {
            conebaseDistance -=1;

        }
        else if(conebaseDistance < newOrb.conebaseDistance)
        {
            conebaseDistance +=1;
        }

        else if( diff < 1 || diff > -1)
        {
            conebaseDistance =newOrb.conebaseDistance;
            return 1;
        }


        return  0;
    }


    //This may not belong here.
    public void beginSegue(OrbitData orb , Orbiting ent)
    {
        ent.getOrbit().newOrb=orb;
        Entity x= (Entity) ent ;

        x.Switch_State(EntityStates.orbitSegue);
    }


    public SimpleVector calculatePositionOffset()
    {

      //  calculateDeltaChange();//calculates speed adjustment

        if(orbitdirection)
        {
            if(t <= pit )
            {
                t+=(averagedelta+averagedeltaOffset);
            }
            else
            {
                t=0;
            }
        }
        else
        {
            if(t >= 0 )
            {
                t-=(averagedelta+averagedeltaOffset);
            }
            else
            {
                t=pit;
            }

        }


        //outputOrbitData();

        float x, z;
        x = ((float)Math.cos(t));
        z = ((float)Math.sin(t)) ;


        SimpleVector lins= OffsetCenter.calcAdd(new SimpleVector(x,0,z));

        SimpleVector linedirection = new SimpleVector(-x,0,-z);
        linedirection = linedirection.normalize();
        Result result =ConePick.getIntersectionsConeCone(lins, linedirection ,coneTop
                ,coneBase,anglevalue);
        SimpleVector[] intersections = result.point;


        //System.out.println("NEW2" + newCenter);


       // outputOrbitData();

        intersections[0].rotateAxis(inclinationAxis, inclination);//not sure why but program crasehs here a lot with null pointers
        lastPosition=intersections[0];
        return new SimpleVector(intersections[0]);
    }


    public  SimpleVector getPucposition()
    {
        return new SimpleVector(pucposition);
    }


    public void outputOrbitData()
    {

        System.out.println("apocenter" + apocenter);
        System.out.println("pericenter" + pericenter);
        System.out.println("OffsetCenter" + OffsetCenter);

        System.out.println("anglevalue" + anglevalue);
        System.out.println("averagedelta" + averagedelta);



    }

    //public SimpleVector calculatePosition()
   /* {
        if(dir == orbitdirection.prograde)
        {
            if(t <= pit )
            {
                t+=averagedelta;
            }
            else
            {
                t=0;
            }
        }
        else
        {
            if(t >= 0 )
            {
                t-=averagedelta;
            }
            else
            {
                t=pit;
            }

        }

        float x, z;
        x = ((float)Math.cos(t));
        z = ((float)Math.sin(t)) ;

        SimpleVector linedir = new SimpleVector(-x,0,-z);
        linedir = linedir.normalize();
        Result result =ConePick.getIntersectionsConeCone(new SimpleVector(x, 0, z) ,
                linedir ,coneTop
                ,coneBase,anglevalue);
        SimpleVector[] intersections = result.point;
        intersections[0].rotateAxis(inclinationAxis, inclination);//not sure why but program crasehs here a lot with null pointers
        return new SimpleVector(intersections[0]);
    }*/


}
