package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;
import Particles.PartEmitter;
import RenderHooks.ExplosionRenderHook;
import baseinterfacesclasses.SingletonObjects;
import states.State;

public class ExplosionSpace extends Entity {

   ExplosionRenderHook expRender;
   PartEmitter  sparks;

    public ExplosionSpace(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference ,GLSLShader shader,SimpleVector direction)
    {
        super(  pos,  moment,  state,  bodyReference);
        expRender = new ExplosionRenderHook(this, shader);
        body.setOrigin(new SimpleVector(0,0,0));
        body.translate(position);
        body.setShader(shader);
        body.setRenderHook(expRender);
        sparks=  SingletonObjects.particleEvents.expSparks(position,direction);
       // Main.allGameObjects.INSTANCE.particleEvents.dustParticles(position);
        SingletonObjects.soundManagement.playExplosion();

        SingletonObjects.vibrationEvents.ExplosionVibration();
    }



    @Override
    public void delete()
    {
        sparks.die();
        sparks=null;
        super.delete();
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
        //Explosions are unique and collision effects should be applied, uniquely, perhaps only once on each object
        //1 call
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

    @Override
    public void fire() {
        //does nothing
    }
}
