package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.SatBasic;
import OrbitCalcs.OrbitData;
import baseinterfacesclasses.SingletonObjects;
import states.State;

/**
 * Created by Chris on 25/09/2017.
 */

public class LaserSatellite extends SatBasic {

    public LaserSatellite(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference, GLSLShader shader, OrbitData orbit) {
        super(pos, moment, state, bodyReference, shader, orbit);

        FireReady= 100;//
        FireCharge=100;
    }


    @Override
    public void fire()
    {
        SingletonObjects.object_factory.createRedLaser(this,Target_Entity);
    }

    @Override
    public float firingDistance() {
        return 40;
    }


}