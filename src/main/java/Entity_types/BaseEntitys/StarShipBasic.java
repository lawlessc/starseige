package Entity_types.BaseEntitys;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import EffectsOnEntities.SolidCollisionEffect;
import Entity_properties.Collideable;
import Entity_properties.CollisionAvoidance;
import Entity_properties.Selectable;
import Entity_properties.SelfDestructable;
import Entity_properties.Shieldable;
import Entity_properties.Touchable;
import RenderHooks.StarShipRenderHook;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import gameObjects.EntityFactory;
import states.State;

/**
 * Created by Chris on 30/01/2018.
 * Starship classes will be the enemy units of this tower defence, unlike the player peices
 * they are not bound by gravity (because they're magic, i dont have to explaine shit)
 *
 *
 */

public class StarShipBasic extends Entity implements  Selectable, Touchable, SelfDestructable, Collideable, CollisionAvoidance {

    StarShipRenderHook srh = null;

    public Boolean   orbiting= false;

   // SimpleVector L1  = new SimpleVector(3,0,0);


    public SimpleVector colour= new SimpleVector(1,1,1);
    public SimpleVector glowColour= new SimpleVector(0.3,0.3,1.0);

    public SimpleVector desired_veclocity= new SimpleVector(0,0,0);

    public SimpleVector ahead =new SimpleVector(0,0,0);
    public SimpleVector ahead2 =new SimpleVector(0,0,0);
    public float     MAX_SEE_AHEAD =10.5f;
    public float     MAX_AVOID_FORCE =this.max_speed;
   // public SimpleVector avoidance_force =new SimpleVector(0,0,0);



    public float  wanderAngle=0.05f;
    public int    behaviour_mode;

    public int payment=1; // How much energy it pays to have this sattelite in orbit.

    public StarShipBasic(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference , GLSLShader shader ) {


        super(pos, moment, state, bodyReference);

        this.maxHealthPoints=100;
        this.healthPoints=100;

        srh = new StarShipRenderHook(this,shader);
        body.setOrigin(new SimpleVector(0,0,0));


        body.translate(position);
        body.setShader(shader);
        body.setRenderHook(srh);
    }



//    @Override
//    public boolean isShieldable() {
//        // TODO Auto-generated method stub
//        return true;
//    }

//    @Override
//    public void spawnShield() {
//
//
//        SingletonObjects.object_factory.createShield(
//                this,
//                EntityFactory.states.shield_state, new SimpleVector(0.2,0.5,1.0));
//
//
//
//    }

//    @Override
//    public void shieldOff() {
//
//    }

    @Override
    public void Update()
    {
        super.Update();
       // steerforce.cal
       // position.add(steerforce);
        //    body.rotateY( -0.001f);
    }

    @Override
    public void fire() {

    }

    public void shouldIFire()
    {//I am not proud of this

        if((FireCharge >= FireReady) && (Target_Entity != null)) {

            if (Target_Entity.isAlive) {

                if (Target_Entity.position.distance(this.position) < this.firingDistance()) {
                    fire();
                    FireCharge =0;
                } else {

                    //Target_Entity = null;
                }

            } else {
                Target_Entity = null;
            }

        }
        FireCharge++;
    }




    public boolean isSelectable() {
        // TODO Auto-generated method stub
        return true;
    }



    public boolean isCollidable() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEthereal() {
        return false;
    }

    @Override
    public SimpleVector collisionPoint() {
        return new SimpleVector(position);
    }

    @Override
    public float collisionArea() {
        return 0.5f;
    }

    @Override
    public void collisionEffect(Entity x) {
        SolidCollisionEffect.INSTANCE.applyEffect(x);
    }


    public void onSingleTap(RayPickPoints points ,MotionEvent me) {

    }


    public void onDoubleTap(RayPickPoints p ,MotionEvent me) {
        SingletonObjects.cameraCursor.setFollow(this);//  BasicSatMenu menu = new BasicSatMenu(this);
    }


    public void actionDown(RayPickPoints points ,MotionEvent me) {

    }


    public void actionUp() {

    }


    public void actionMove(MotionEvent me, RayPickPoints points) {

    }


    public void setScaleFactor(ScaleGestureDetector m) {

    }


    public void selfDestruct() {

    }

//    @Override
//    public void setOrbit(OrbitData orbit) {
//
//        this.orbit = orbit;
//
//    }
//
//    @Override
//    public OrbitData getOrbit() {
//        return orbit;
//    }
//
//    @Override
//    public boolean isOrbiting() {
//        return orbiting;
//    }

    @Override
    public Boolean isTargetable() {
        return true;
    }

    @Override
    public boolean canFire() {
        return true;
    }

    @Override
    public float firingDistance() {
        return 10;
    }

    @Override
    public boolean hasCollisionAvoidance() {
        return true;
    }
}

