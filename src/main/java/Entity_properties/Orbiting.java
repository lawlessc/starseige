package Entity_properties;

import OrbitCalcs.OrbitData;

/**
 * Created by Chris on 01/10/2017.
 */

public interface Orbiting {


     void setOrbit (OrbitData orbit);

     OrbitData getOrbit ();


     boolean isOrbiting();

}
