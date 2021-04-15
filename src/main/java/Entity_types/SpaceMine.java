package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.SatBasic;
import OrbitCalcs.OrbitData;
import states.State;

/**
 * Created by Chris on 21/09/2017.
 */

public class SpaceMine extends SatBasic {
    public SpaceMine(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference, GLSLShader shader, OrbitData orbit) {
        super(pos, moment, state, bodyReference, shader, orbit);

        this.colour= new SimpleVector(1,1,1);
        this.glowColour= new SimpleVector(1,0.3,0.1); //should be yellow
        this.payment=0;//no pay for this


    }


    @Override
    public void fire() {
        // Target_Entity
        //This is where we call the laser
        //We call a mine detonation function that damages nearby objects.
        //unlike a regular explosion this should probably produce shrapnel particles?

    }

    @Override
    public Boolean isTargetable() {
        return true;
    }

}
