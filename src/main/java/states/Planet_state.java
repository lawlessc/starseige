package states;

import Entity_types.BaseEntitys.Entity;
import Entity_types.Planet;


//Object should proceed from planet to orbit
public class Planet_state extends State   {

	public Planet_state() {
		super();
	}
	
	
	
	
	
	@Override
	public void Enter(Entity entity) {
		

	}
	@Override
	public void Exit(Entity entity) {

	}

	
	
	@Override
	public void Update(Entity entity)
	{
		Planet thisone = (Planet)entity;
		thisone.body.rotateY( -0.001f);
		thisone.cloudSphere.rotateY( -0.001f);
		//thisone.cloudSphere.rotateY(0.001f);
	}
	
	
	
	

	
	
}
