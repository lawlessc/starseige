package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.SatBasic;
import OrbitCalcs.OrbitData;
import baseinterfacesclasses.SingletonObjects;
import states.State;

/**
 * Created by Chris on 20/09/2017.
 */

public class BatterySatellite extends SatBasic
{

    public int power_storage = 500;





    public BatterySatellite(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference, GLSLShader shader, OrbitData orbit) {
        super(pos, moment, state, bodyReference, shader, orbit);


        this.colour= new SimpleVector(1,1,1);
        this.glowColour= new SimpleVector(1,1,0.1); //should be yellow
        this.payment=0;//no pay for this

        SingletonObjects.player_money.addToLimit(power_storage);
    }

    @Override
    public void switchToDeathState()
    {
        super.switchToDeathState();

        SingletonObjects.player_money.subtractLimit(power_storage);
    }
}
