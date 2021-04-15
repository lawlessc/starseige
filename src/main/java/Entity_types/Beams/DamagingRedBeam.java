package Entity_types.Beams;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;


import EffectsOnEntities.DamagingEffect;
import Entity_types.BaseEntitys.Entity;
import Particles.PartEmitter;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 11/11/2017.
 */

public class DamagingRedBeam extends FiringBeam {

    PartEmitter collisionEmission;// Need to make this default off but have it switch on if beam is hitting something.


    public DamagingRedBeam(Entity parent, Entity target, Object3D bodyReference, GLSLShader shader) {
        super(parent, target, bodyReference, shader);

        this.size=2;

        this.colour = new SimpleVector(1, 1, 1);
        this.glowColour = new SimpleVector(1, 0.5, 0.5);
        SingletonObjects.soundManagement.laserpulse();

        collisionEmission =    SingletonObjects.particleEvents.redLaserSparks( new SimpleVector(this.collisionPoint()),new  SimpleVector(0,0,0) );
        collisionEmission.isOn=true;

    }

    @Override
    public void collisionEffect(Entity x) {
     //Check if this collision actually happens
        DamagingEffect.INSTANCE.applyEffect(x);
        collisionEmission.pos = new SimpleVector(this.collisionPoint());
        collisionEmission.isOn=true;
       // Main.SingletonObjects.INSTANCE.soundManagement.playExplosion();//justto see if it is called
    }

    @Override
    public void delete()
    {

        collisionEmission.die();
        collisionEmission = null;
        super.delete();
    }

    @Override
    public void Update()
    {
        super.Update();

        if(Target_Entity ==null &&  collisionEmission != null)
        {
            collisionEmission.isOn=false;//we do this to kill the emmitter;



        }
        else if(collisionEmission != null)
        {
            collisionEmission.pos = new SimpleVector(this.collisionPoint());
        }

        if( Target_Entity.isAlive == false &&  collisionEmission != null )
        {

            collisionEmission.isOn=false;
            this.delete();
        }





    }


}
