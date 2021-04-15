package OrbitCalcs;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.FastInverseSquareRoot;


/**
 * Created by lawless on 23/12/2015.
 */
public final class ConePick
{



    public static Result getIntersectionsConeCone(SimpleVector lineStart , SimpleVector linedir ,
                                                  SimpleVector ConeVertex , SimpleVector conedir ,
                                                  float coneAngle)
    {
      // SimpleVector ConeVertex = new SimpleVector(ConeVer);
     //  SimpleVector lineStart = new SimpleVector(lineSt);

        Result res = getIntersectionsWithCone( lineStart ,  linedir ,
                 ConeVertex ,  conedir ,
         coneAngle,  100000);




        return res;
    }



public static Result  getIntersectionsWithCone(SimpleVector lineStart , SimpleVector linedir ,
                                        SimpleVector ConeVertex , SimpleVector conedir ,
                                        float coneAngle, float coneHeight
                                        )
    {
        Result result = new Result();
        SimpleVector direction = new SimpleVector(linedir);

        lineConeIntersectionTwo(lineStart, direction, ConeVertex,conedir ,coneAngle, coneHeight, result);
        SimpleVector lined = new SimpleVector(linedir);
        SimpleVector lined2 = new SimpleVector(linedir);
        switch (result.type)
        {

            case 1:  // point
                lined.scalarMul(result.parameter[0]);
                result.point[0] = lineStart.calcAdd(lined);
                result.point[1] = result.point[0];
                break;
            case 2:  // segment

                lined.scalarMul(result.parameter[0]);
                lined2.scalarMul(result.parameter[1]);

                result.point[0] = lineStart.calcAdd(lined);
                result.point[1] = lineStart.calcAdd(lined2);
                break;
            case 3:  // ray
                lined.scalarMul(result.parameter[0]);
                result.point[0] = lineStart.calcAdd(lined);
                result.point[1] = lined2;
                break;
            case 4:  // ray
                lined.scalarMul(result.parameter[1]);
                result.point[0] = lineStart.calcAdd(lined);
                result.point[1] = new SimpleVector(0,0,0).calcAdd(lined2);
                break;
            case 5:  // line
                result.point[0] = lineStart;
                result.point[1] = lined2;
                break;
            default:  // no intersection
                break;
        }
        return result;
    }



    public static SimpleVector[]   lineConeIntersectionTwo(SimpleVector lineOrigin , SimpleVector lineDirection,
                                                           SimpleVector ConeVertex , SimpleVector conedir ,
                                                           float coneAngle,
                                                           float  coneHeight,
                                                           Result result

    ) {
       // SimpleVector lineDirection = new SimpleVector(linedir);
       // SimpleVector lineOrigin = new SimpleVector(lineStart);

        SimpleVector PmV = lineOrigin.calcSub(ConeVertex);
        float DdU = conedir.calcDot(lineDirection);
        float DdPmV = conedir.calcDot( PmV);
        float UdPmV = lineDirection.calcDot(PmV);
        float PmVdPmV = PmV.calcDot(PmV);
        float cosSqr = coneAngle * coneAngle;
        float c2 = DdU*DdU - cosSqr;
        float c1 = DdU*DdPmV - cosSqr*UdPmV;
        float c0 = DdPmV*DdPmV - cosSqr*PmVdPmV;
        float t;

        if (c2 != 0)
        {
            // c2 != 0
            float discr = c1 * c1 - c0 * c2;
            if (discr < 0)
            {
                // The quadratic has no real-valued roots.  The line does not
                // intersect the double-sided cone.
                 result.intersect = false;
                 result.type = 0;
            }
            else if (discr > 0)
            {
                // The quadratic has two distinct real-valued roots.  However, one
                // or both of them might intersect the negative cone.  We are
                // interested only in those intersections with the positive cone.
                float root =(float) Math.sqrt(discr);

               // float root = FastInverseSquareRoot.Sqrt(discr);
                float invC2 = (1) / c2;
                int numParameters = 0;

                t = (-c1 - root) * invC2;
                if (DdU * t + DdPmV >= 0)
                {
                    result.parameter[numParameters++] = t;
                }

                t = (-c1 + root) * invC2;
                if (DdU * t + DdPmV >= 0)
                {
                    result.parameter[numParameters++] = t;
                }

                if (numParameters == 2)
                {
                    // The line intersects the positive cone in two distinct
                    // points.
                    result.intersect = true;
                    result.type = 2;
                    if (result.parameter[0] > result.parameter[1])
                    {
                        float swp = result.parameter[0];
                        result.parameter[0] = result.parameter[1];
                        result.parameter[1] = swp;
                    }
                }
                else if (numParameters == 1)
                {
                    // The line intersects the positive cone in a single point and
                    // the negative cone in a single point.  We report only the
                    // intersection with the positive cone.
                    result.intersect = true;
                    if (DdU > 0)
                    {
                        result.type = 3;
                        result.parameter[1] = Float.MAX_VALUE;
                    }
                    else
                    {
                        result.type = 4;
                        result.parameter[1] = result.parameter[0];
                        result.parameter[0] = -Float.MAX_VALUE;
                    }
                }
                else
                {
                    // The line intersects the negative cone in two distinct
                    // points, but we are interested only in the intersections
                    // with the positive cone.
                    result.intersect = false;
                    result.type = 0;
                }
            }
            else
            {
                // One repeated real root; the line is tangent to the double-sided
                // cone at a single point.  Report only the point if it is on the
                // positive cone.
                t = -c1 / c2;
                if (DdU * t + DdPmV >= 0)
                {
                    result.intersect = true;
                    result.type = 1;
                    result.parameter[0] = t;
                    result.parameter[1] = t;
                }
                else
                {
                    result.intersect = false;
                    result.type = 0;
                }
            }
        }
        else if (c1 != 0)
        {
            // c2 = 0, c1 != 0; U is a direction vector on the cone boundary
            t = -(0.5f)*c0 / c1;
            if (DdU * t + DdPmV >= 0)
            {
                result.intersect = true;
                if (DdU > 0)
                {
                    result.type = 3;
                    result.parameter[0] = t;
                    result.parameter[1] = Float.MAX_VALUE;
                }
                else
                {
                    result.type = 4;
                    result.parameter[0] = -Float.MAX_VALUE;
                    result.parameter[1] = t;
                }
            }
            else
            {
                result.intersect = false;
                result.type = 0;
            }
        }
        else if (c0 != 0)
        {
            // c2 = c1 = 0, c0 != 0
            result.intersect = false;
            result.type = 0;
        }
        else
        {
            // c2 = c1 = c0 = 0; the line is on the cone boundary.
            result.intersect = true;
            result.type = 5;
            result.parameter[0] = -Float.MAX_VALUE;
            result.parameter[1] = Float.MAX_VALUE;

        }

        if (coneHeight < Float.MAX_VALUE && DdU != 0)
        {
            // Clamp the intersection to the height of the cone.
            float invAdD = (1) / DdU;
            float[] hInterval= {0,0};
            if (DdU > 0)
            {
                hInterval[0] = -DdPmV * invAdD;
                hInterval[1] = (coneHeight - DdPmV) * invAdD;
            }
            else // (DdU < (Real)0)
            {
                hInterval[0] = (coneHeight - DdPmV) * invAdD;
                hInterval[1] = -DdPmV * invAdD;
            }

          //  FIIntervalInterval<Real> iiQuery;
          //  auto iiResult = iiQuery(result.parameter, hInterval);
          //  result.parameter = iiResult.overlap;
          //  result.type = iiResult.numIntersections;
        }
        return null;
    }

}
