package baseinterfacesclasses;

import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by lawless on 09/09/2015.
 */
public final class Kepler {

    static double G = 6.67e-11; // Newton’s constant in SI units
    static  double M = 1.99e30; // Mass of the Sun in kg
    static double EM =5.972e24;

   // static double EM =59000;

   // 5.972 × 10^24

    double rAphelion ; // aphelion distance in m
    double vAphelion ; // aphelion speed



    public SimpleVector  getPoint()//needs to take time, Aphelion speed, Aphelion position etc.
    {

        return null;
    }


    public static SimpleVector  CalForceVector(Entity ent, SimpleVector planetPoint,float dT)
    {

        SimpleVector r = planetPoint.calcSub(ent.position);
        r.scalarMul(1590.7f);
        float r_magnitude= (r.x*r.x)+(r.y*r.y)+(r.z*r.z);
        r.normalize();


          r.scalarMul (
                       (float) ((G*(EM *ent.mass)) / ((float)Math.pow(r_magnitude, 2)))
               );


        float vx = r.x/ent.mass;
        float vy = r.y/ent.mass;
        float vz = r.z/ent.mass;

     SimpleVector velocitytoadd = new  SimpleVector( vx,vy ,vz);
     velocitytoadd.scalarMul(dT);

       // velocitytoadd= new SimpleVector(-velocitytoadd.x,-velocitytoadd.y,-velocitytoadd.z);

     return velocitytoadd;
    }







}
