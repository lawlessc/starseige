package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_properties.Selectable;
import Entity_types.BaseEntitys.Entity;
import RenderHooks.WormholeRenderHook;
import baseinterfacesclasses.SingletonObjects;
import states.State;


//A hole in through which things fly
//basically an inverse sky sphere for this game.
public class Wormhole extends Entity implements Selectable {

    //skysphereRenderHook srh;
    WormholeRenderHook hook;

    public Wormhole(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference,GLSLShader shader)
    {
        super( pos,  moment,  state,  bodyReference);
        hook = new WormholeRenderHook(this,shader);
        //	body.setOrigin(position);
        	body.setShader(shader);
        	body.setRenderHook(hook);
        SingletonObjects.particleEvents.wormHoleParticles(position);
    }



    @Override
    public void Update() {
        if(current_state != null)
        {
            current_state.Update(this);
        }
        body.clearTranslation();
        body.translate(position);
    }

    @Override
    public void fire() {

    }

    public void Collapse()
    {
        //wormehole collapses or enters a state of Collapse.
    }

    public boolean isSelectable() {
        // TODO Auto-generated method stub
        return true;
    }



    public boolean isCollidable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEthereal() {
        return true;
    }

    @Override
    public SimpleVector collisionPoint() {
        return null;
    }

    @Override
    public float collisionArea() {
        return 0;
    }

    @Override
    public void collisionEffect(Entity x) {

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
        return false;
    }
}
