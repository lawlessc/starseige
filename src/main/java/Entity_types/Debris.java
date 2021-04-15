package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_properties.Orbiting;
import Entity_types.BaseEntitys.Entity;
import OrbitCalcs.OrbitData;
import RenderHooks.SatelliteRenderHook;
import states.State;
/**
 * Created by Chris on 18/02/2016.
 */
public class Debris extends Entity implements  Orbiting{

    SatelliteRenderHook srh = null;
    public OrbitData orbit = null;
    public Boolean   orbiting= false;




    public Debris(SimpleVector pos, State state, Object3D bodyReference,GLSLShader shader
            , OrbitData orbitData)
    {
        super( pos,  new SimpleVector(0, 0, 0),  state,  bodyReference);


        this.orbit=orbitData;
        srh = new SatelliteRenderHook(this,shader);
        body.setOrigin(new SimpleVector(0,0,0));
        body.translate(position);
        body.setShader(shader);
        body.setRenderHook(srh);
        this.Switch_State(state);
    }


    @Override
    public void setOrbit(OrbitData orbit) {
        this.orbit =orbit;
    }

    @Override
    public OrbitData getOrbit() {
        return orbit;
    }

    @Override
    public boolean isOrbiting() {
        return orbiting;
    }

    @Override
    public boolean canFire() {
        return false;
    }

    @Override
    public float firingDistance() {
        return 0;
    }

    @Override
    public Boolean isTargetable() {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isEthereal() {
        return false;
    }

    @Override
    public SimpleVector collisionPoint() {
        return this.position;
    }

    @Override
    public float collisionArea() {
        return 1;
    }


    @Override
    public void collisionEffect(Entity x) {

    }

    @Override
    public void fire() {

    }
}
