package Entity_types;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_properties.Allegiance;
import Entity_properties.Payload;
import Entity_properties.Selectable;
import Entity_properties.Touchable;
import Entity_types.BaseEntitys.Entity;
import Entity_types.BaseEntitys.SatBasic;
import OrbitCalcs.OrbitData;
import Particles.PartEmitter;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import gameObjects.EntityFactory;
import states.EntityStates;
import states.State;

public class CarrierRocket extends SatBasic implements Selectable , Touchable {


	public int payload;

    public SimpleVector thrusterBaseColour= new SimpleVector(1,0.9,0.4);

	PartEmitter thrustertrail;
	
	
	public CarrierRocket(SimpleVector pos, SimpleVector moment, State state,
						 Object3D bodyReference,GLSLShader satelliteshader, OrbitData orbitData)
	{

		super( pos,  moment,  state,  bodyReference,satelliteshader,orbitData);


		if(orbitData==null)
		{
			//System.out.println("orbitData IS NULL");
		}



		this.colour = new SimpleVector(1,0.8,0.4);
        this.glowColour= new SimpleVector(1,0.8,0.4);
        this.payment=0;


		thrustertrail =    SingletonObjects.particleEvents.rocketThruster( this,new  SimpleVector(0,0,0) );
		thrustertrail.isOn=true;

	}

//	@Override
//	public void collisionEffect(Entity x) {
//		if(x instanceof Planet){
//			//prevents carrier from colliding with homeplanet
//		}
//		else {
//			SolidCollisionEffect.INSTANCE.applyEffect(x);
//		}
//	}


	@Override
	public void delete()
	{

		thrustertrail.die();
		thrustertrail = null;
		super.delete();
	}



	public void deliverPayload()
	{
		Entity _ent = null;
		switch (payload)
		{

			case Payload.Satellite:
				_ent= (Entity) SingletonObjects.object_factory.createSattelite(new SimpleVector(position),new SimpleVector(0,1,0), EntityStates.orbitState, this.orbit );
				_ent.allegiance= Allegiance.Player;
				break;
			case Payload.Explosion:
				_ent= (Entity)SingletonObjects.object_factory.createExplosion(new SimpleVector(position), new SimpleVector(0, 1, 0));

				break;
			case Payload.Firework:
			//	_ent= (Entity) SingletonObjects.object_factory.createFirework(new SimpleVector(position));
				SingletonObjects.object_factory.createFirework(new SimpleVector(position));
				break;
			case Payload.BatterySatellite:
				_ent= (Entity) SingletonObjects.object_factory.createBatterySattelite(new SimpleVector(position),new SimpleVector(0,1,0), EntityStates.orbitState, this.orbit );
				_ent.allegiance= Allegiance.Player;
				break;
			case Payload.Mine:
				_ent= (Entity) SingletonObjects.object_factory.createSpaceMine(new SimpleVector(position),new SimpleVector(0,1,0), EntityStates.orbitState, this.orbit );
				//_ent.allegiance= Allegiance.Player;
				break;
			case Payload.LaserSatellite:
				_ent= (Entity) SingletonObjects.object_factory.createLaserSattelite(new SimpleVector(position),new SimpleVector(0,1,0), EntityStates.orbitState, this.orbit );
				_ent.allegiance= Allegiance.Player;
				break;



		}


		//rocketPropulsion.killEmmitter();

	}

	@Override
	public void Update()
    {

	   super.Update();
       float  randomNum =  (float)Math.random()*2;

       SimpleVector brightness= new SimpleVector(thrusterBaseColour);
       brightness.scalarMul(randomNum);


        this.glowColour =thrusterBaseColour.calcAdd(brightness);
        //this.colour =thrusterBaseColour.calcAdd(brightness);
    }


	public void setPayload(int pl)
	{
		payload = pl;
	}
	



	public boolean isSelectable() {
		// TODO Auto-generated method stub
		return true;
	}

	


	public void onSingleTap(RayPickPoints p ,MotionEvent me) {

	}

	public void onDoubleTap(RayPickPoints p ,MotionEvent me) {

		SingletonObjects.cameraCursor.setFollow(this);


	}

	public void actionDown(RayPickPoints points ,MotionEvent me) {

	}

	public void actionUp() {

	}

	public void actionMove(MotionEvent me, RayPickPoints points) {

	}

	public void setScaleFactor(ScaleGestureDetector m) {

	}

	@Override
	public void fire() {

	}

	@Override
	public Boolean isTargetable() {
		return false;
	} // may make targetable later

	@Override
	public boolean canFire() {
		return false;
	}

	@Override
	public float firingDistance() {
		return 10;
	}


	@Override
	public boolean isEthereal() {
		return false;
	}
}
