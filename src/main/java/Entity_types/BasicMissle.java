package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.StarShipBasic;
import Particles.PartEmitter;
import baseinterfacesclasses.SingletonObjects;
import states.State;

/**
 * Created by Chris on 25/09/2017.
 *
 * basic planet torpedo, will try to not get shot but hit the planet
 *
 */

public class BasicMissle extends StarShipBasic {


    public PartEmitter thrustertrail;

    public BasicMissle(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference, GLSLShader shader) {
        super(pos, moment, state, bodyReference, shader);

        FireReady= 100;//
        FireCharge=100;
        mass=1.0f;

        this.colour= new SimpleVector(1,1,1);
        this.glowColour= new SimpleVector(0.3,1.0,0.1); //should be yellow
        this.payment=0;//no pay for this


        thrustertrail =    SingletonObjects.particleEvents.rocketThruster( this,new  SimpleVector(0,0,0) );
        thrustertrail.isOn=true;

        max_speed=0.05f;


    }

    @Override
    public void delete()
    {

        thrustertrail.die();
        thrustertrail = null;
        super.delete();
    }



    @Override
    public void fire()
    {
        // Target_Entity
        //This is where we call the laser
     ///  Main.allGameObjects.INSTANCE.object_factory.createRedLaser(this,Target_Entity);

        //The firing of this should create a planet surface explosion
    }


    @Override
    public void shouldIFire()
    {
          //check here if distance from planet is the distance of the surface?


//
//        if((FireCharge >= FireReady) && (Target_Entity != null)) {
//
//            if (Target_Entity.isAlive) {
//
//                if (Target_Entity.position.distance(this.position) < this.firingDistance()) {
//                    fire();
//                    FireCharge =0;
//                } else {
//
//                    Target_Entity = null;
//                }
//
//            } else {
//                Target_Entity = null;
//            }
//
//        }
//        FireCharge++;
    }


    @Override
    public boolean hasCollisionAvoidance() {
        return false;
    }





    @Override
    public float firingDistance() {
        return 40;
    }


}