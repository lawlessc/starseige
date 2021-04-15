package states;

import com.threed.jpct.SimpleVector;

import Entity_properties.Orbiting;
import Entity_types.CarrierRocket;
import Entity_types.BaseEntitys.Entity;
import Entity_types.BaseEntitys.SatBasic;
import baseinterfacesclasses.SingletonObjects;


//Object should proceed from planet to orbit
//Object should start of small but inflate to final size
//object may start as rocket? then open on arrival.
public class Launching_state extends State   {
	
	

	public Launching_state() {
		super();
	}


	

	@Override
	public void Enter(Entity entity) {

		//entity.steerforce=new SimpleVector(0,0,0);

		SatBasic orb= (SatBasic) entity;

		SimpleVector orbpoint = new SimpleVector(orb.orbit.calculatePositionOffset() );

		float length = orbpoint.distance(new SimpleVector(0,0,0));
		float sper= length/1000f;

		      sper=4.005f/sper;
		entity.launchP = sper/1000f;
		//entity.size= entity.launchP*4.0f;
		entity.body.setScale(0.1f);


		SingletonObjects.soundManagement.playLaunch();
	}
	@Override
	public void Exit(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void Update(Entity entity)
	{

	updateTargetPosition( entity);
	moveTowardsTarget(entity);

	 if(entity.launchP >= 1)
	 {
		 entity.Switch_State(EntityStates.orbitState);
		 if(entity instanceof CarrierRocket)
		 {
			 entity.body.setScale(entity.launchP*0.5f);
			 CarrierRocket carrierRocket= (CarrierRocket)entity;
			 carrierRocket.deliverPayload();
			 entity.delete();
		 }
	 }
		if (entity.body != null)
		{
			SimpleVector norm = new SimpleVector(entity.target);

			norm.normalize();
			entity.body.setOrientation(norm, entity.up);
		//	entity.body.setOrigin(entity.position);
		//	entity.position.add(norm);
		}
	}


	void moveTowardsTarget(Entity entity)
	{
		Orbiting orb= (Orbiting) entity;

		float delta= orb.getOrbit().averagedelta;
		//entity.launchP += delta;
		entity.launchP += 0.003f;
		//entity.size= entity.launchP*4.0f;

		entity.body.setScale(entity.launchP*0.5f);

        SimpleVector orbpoint = new SimpleVector(entity.target);
		orbpoint.normalize();
		orbpoint.scalarMul(entity.launchP);
		entity.position = orbpoint;
	}

	void updateTargetPosition(Entity entity)
	{
	Orbiting orb= (Orbiting) entity;

	entity.target = orb.getOrbit().calculatePositionOffset();

	}
	
}
