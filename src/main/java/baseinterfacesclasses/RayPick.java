package baseinterfacesclasses;

import android.view.MotionEvent;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.FastInverseSquareRoot;

import Entity_properties.Selectable;
import Entity_properties.Touchable;

public final class RayPick {

	public static RayPickPoints touch(MotionEvent me,FrameBuffer fb,  float left, float top) {
	  float x = me.getX();
      float y = me.getY();
      x = x - left;
      y = y - top;

      if( SingletonObjects.menumanager == null )
      {
          return null;
      }

	  SimpleVector dir = new SimpleVector(Interact2D.reproject2D3DWS(SingletonObjects.cam, fb, (int)x,(int) y).normalize());
      SimpleVector cam_position= new SimpleVector(SingletonObjects.world.getCamera().getPosition());

      RayPickPoints rayPickPoints = new RayPickPoints(cam_position,dir);
      rayPickPoints.setObjectPoints(null,null);
      rayPickButton(rayPickPoints);

      if(rayPickPoints.objectHit==null) {
        rayPickEntity(rayPickPoints);
      }
     return  rayPickPoints;
	}

    public static void rayPickEntity(RayPickPoints raypoints)
    {// I may need to put a try catch on this to have it quit the loop if its wrong
		for(int k = 0 ; k < SingletonObjects.game_objects.size() ; k++) {
            //if (SingletonObjects.game_objects.get(k).isSelectable()) {
                if (SingletonObjects.game_objects.get(k) instanceof Selectable) {

                SimpleVector[] x = sphereIntersect(
                        raypoints.rayStartPoint,
                        raypoints.rayDirection,
                        new SimpleVector(SingletonObjects.game_objects.get(k).position),// 5.6f
                        (SingletonObjects.game_objects.get(k).size)
                        );
                if (x.length > 0) { //Should i be doing casting here?
                    raypoints.setObjectPoints((Touchable) SingletonObjects.game_objects.get(k),x);
                    k=1000000;// no way the size will ever be this big.
                }
            }
        }
	}

    public static void rayPickButton(RayPickPoints raypoints)
    {
            for(int z = 0 ; z < SingletonObjects.menumanager.menustack.size() ; z++)
            {
                SimpleVector[] x =  sphereIntersect(
                        raypoints.rayStartPoint,
                        raypoints.rayDirection,
                        SingletonObjects.menumanager.menustack.get(z).returnPosition(),
                        SingletonObjects.menumanager.menustack.get(z).size );
                if(x.length >0)
                {
                    raypoints.setObjectPoints(SingletonObjects.menumanager.menustack.get(z),x);
                    z=1000000;// no way the size will ever be this big.
                }
            }
    }

	public static SimpleVector[] sphereIntersect(SimpleVector rayOrigin,SimpleVector rayDirection, SimpleVector sphereOrigin, float sphereRadius ) {

        sphereOrigin.sub(rayOrigin);

//        double c = (double) sphereOrigin.length();
//        double v = (double) sphereOrigin.calcDot(rayDirection);
//        double d = sphereRadius * sphereRadius - (c * c - v * v);
        float c =  sphereOrigin.length();
        float v =  sphereOrigin.calcDot(rayDirection);
        float d = sphereRadius * sphereRadius - (c * c - v * v);

        if (d < 0.0) {
            return new SimpleVector[0];
        }
        //return false;
        float distance = (float) (v - Math.sqrt(d));

      //  float distance =(v - FastInverseSquareRoot.Sqrt(d));

        rayDirection.scalarMul(distance);
        SimpleVector r1 = new SimpleVector(rayDirection.calcAdd(rayOrigin));

        return new SimpleVector[]{r1};
    }


    public static SimpleVector linePlaneIntersection(SimpleVector rayDir,
                                                     SimpleVector rayOrigin, SimpleVector normal, SimpleVector planecoord) {

        SimpleVector RayPointB = new SimpleVector( rayDir);
        RayPointB.scalarMul(100);

        // calculate plane
        float d = normal.calcDot(planecoord);

        if (normal.calcDot(RayPointB) == 0) {
            return null; // avoid divide by zero
        }

        // Compute the t value for the directed line ray intersecting the plane
        float t = (d - (normal.calcDot(rayOrigin))) / (normal.calcDot(RayPointB));
        // scale the ray by t
        SimpleVector newRay = new SimpleVector(RayPointB);
        newRay.scalarMul(t);
        // calc contact point
        SimpleVector contactPoint = rayOrigin.calcAdd(newRay);

        if (t >= 0.0f && t <= 1.0f) {
            return contactPoint; // line intersects plane
        }
        return null;
    }



    public static SimpleVector[]   lineConeIntersection(SimpleVector lineOrigin, SimpleVector lineDirection ,
                                                  SimpleVector ConeVertex , SimpleVector conedir , float coneRadius)
    {

        SimpleVector positiveOffset = new SimpleVector(120,120,120);


        lineOrigin.add(positiveOffset);
        ConeVertex.add(positiveOffset);


        //SimpleVector axis  =  conedir;// coneBaseCentre.calcSub(ConeVertex);
        float axisL= conedir.length();  //axis.length();
        SimpleVector theta = new SimpleVector();
        theta.x = conedir.x / axisL;
        theta.y = conedir.y / axisL;
        theta.z = conedir.z / axisL;


        double m =( Math.pow(coneRadius,2)/Math.pow(axisL,2));
        SimpleVector w = lineOrigin.calcSub(ConeVertex);


        double lineDotTheta=lineDirection.calcDot(theta);
        double lineDotThetaPow=Math.pow(lineDotTheta,2);
        double wDotTheta  = w.calcDot(theta);
        double wDotThetaPow = Math.pow(wDotTheta,2);


        double a  = lineDirection.calcDot(lineDirection) -     m*(lineDotThetaPow)-lineDotThetaPow;
        double b  = 2.f * (lineDirection.calcDot(w)) -           m*lineDotTheta * wDotTheta - lineDotTheta*wDotTheta;
        double c =  w.calcDot(w)           -                   m*wDotThetaPow - wDotThetaPow;

        float discriminant = (float)(Math.pow(b,2) - (4.f * a * c));


        if(discriminant >=0)
        {

            float sqrtDiscrim = (float) Math.sqrt(discriminant );

           // float sqrtDiscrim = FastInverseSquareRoot.Sqrt(discriminant );

            float twoa =  2.f*(float)a;

            float neg = (((float)-b) - sqrtDiscrim)/twoa;
            float pos = (((float)-b) + sqrtDiscrim)/twoa ;

            SimpleVector mi = new SimpleVector(lineDirection);
            mi.scalarMul(neg);

            SimpleVector pi = new SimpleVector(lineDirection);
            pi.scalarMul(pos);




            mi.add(lineOrigin);
            pi.add(lineOrigin);

            lineOrigin.sub(positiveOffset);
            ConeVertex.sub(positiveOffset);

            pi.sub(positiveOffset);
            mi.sub(positiveOffset);

          //  System.out.println("discriminant is" + discriminant);
          //  System.out.println("VECTOR 1" + mi);
//
          //  System.out.println("VECTOR 2" +pi );

            if(pos > neg)
            {
             //   System.out.println("POS>NEG");
                return new SimpleVector[]{mi,pi};
            }
            //System.out.println("POS LESS");
            return new SimpleVector[]{pi,mi};
        }


      //  System.out.println("NOTHING");
        return new SimpleVector[]{new SimpleVector(0,0,0)};
    }







}
