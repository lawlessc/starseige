package Entity_types.BaseEntitys;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import EffectsOnEntities.SolidCollisionEffect;
import Entity_properties.Collideable;
import Entity_properties.Orbiting;
import Entity_properties.Selectable;
import Entity_properties.SelfDestructable;
import Entity_properties.Shieldable;
import Entity_properties.Touchable;
import OrbitCalcs.OrbitData;
import RenderHooks.SatStarRenderHook;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import gameObjects.EntityFactory;
import menusViews.BasicSatMenu;
import states.EntityStates;
import states.State;

/**
 * Created by Chris on 18/02/2016.
 */
public  class SatBasic extends Entity implements  Selectable , Touchable, SelfDestructable ,Collideable, Orbiting {


    //SatelliteRenderHook srh = null;
    SatStarRenderHook srh = null;

    public OrbitData orbit = null;
    public Boolean   orbiting= false;



    public SimpleVector colour= new SimpleVector(1,1,1);
    public SimpleVector glowColour= new SimpleVector(0.3,0.3,1.0);
    //int cost= 10;//The cost to b
    public int payment=1; // How much energy it pays to have this sattelite in orbit.


    //PartEmitter rocketPropulsion;
   // SolidCollisionEffect solidCollisionEffect;


    public SatBasic(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference ,GLSLShader shader , OrbitData orbit) {


        super(pos, moment, state, bodyReference);
        this.orbit=orbit;

        this.maxHealthPoints=100;
        this.healthPoints=100;

       // srh = new SatelliteRenderHook(this,shader);
        srh = new SatStarRenderHook(this,shader);
        //body.setOrigin(position);
        body.setOrigin(new SimpleVector(0,0,0));
      //  lightpoint1.setOrigin(new SimpleVector(-1,-7,0));

        body.translate(position);
        body.setShader(shader);
        body.setRenderHook(srh);

     //   rocketPropulsion= Main.allGameObjects.INSTANCE.particleEvents.rocketThruster(this,L1);
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
//                    this,
//                EntityStates.shield_state, new SimpleVector(0.2,0.5,1.0));
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
    //    body.rotateY( -0.001f);
    }

    @Override
    public void fire() {

    }





//    public void energyPayment()
//    {
//        if(this.allegiance == Allegiance.Player)
//        Main.allGameObjects.INSTANCE.player_money.addMoney(payment);
//    }

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
        return 0;
    }

    @Override
    public void collisionEffect(Entity x) {
        SolidCollisionEffect.INSTANCE.applyEffect(x);
    }


    public void onSingleTap(RayPickPoints points ,MotionEvent me) {

    }


    public void onDoubleTap(RayPickPoints p ,MotionEvent me) {

        SingletonObjects.cameraCursor.setFollow(this);

        BasicSatMenu menu = new BasicSatMenu(this);


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

    @Override
    public void setOrbit(OrbitData orbit) {

            this.orbit = orbit;

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
}
