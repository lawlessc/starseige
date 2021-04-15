package OrbitCalcs;

import com.threed.jpct.SimpleVector;

/**
 * Created by lawless on 03/01/2016.
 */


 public  class Result
    {
        public Boolean intersect;

        // Because the intersection of line and cone with infinite height
        // h > 0 can be a ray or a line, we use a 'type' value that allows
        // you to decide how to interpret the parameter[] and point[] values.
        //   type  intersect  valid data
        //   0     none       none
        //   1     point      parameter[0] = parameter[1], finite
        //                    point[0] = point[1]
        //   2     segment    parameter[0] < parameter[1], finite
        //                    point[0,1] valid
        //   3     ray        parameter[0] finite, parameter[1] maxReal
        //                    point[0] = rayOrigin, point[1] = lineDirection
        //   4     ray        parameter[0] -maxReal, parameter[1] finite
        //                    point[0] = rayOrigin, point[1] = -lineDirection
        //   5     line       parameter[0] -maxReal, parameter[1] maxReal,
        //                    point[0] = lineOrigin, point[1] = lineDirection
        // If the cone height h is finite, only types 0, 1, or 2 can occur.
        public int type;
        public float[] parameter = new float[10];  // Relative to incoming line.
        public SimpleVector[] point=  new SimpleVector[2];


        Result(){};
    };



