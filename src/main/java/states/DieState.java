package states;

import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;


//Object should proceed from planet to orbit
public class DieState extends State   {
	
	
	//Entity entity;

	//Satellite reference;
	
	int transparency= 150;
	
	public DieState() {
		super();

	}
	
	
	
	
	
	@Override
	public void Enter(Entity entity) {
		SingletonObjects.object_factory.createExplosion(entity.getPosition(),new SimpleVector(0,0,0));
		entity.delete();
	}
	@Override
	public void Exit(Entity entity) {
		// TODO Auto-generated method stub
	}

	
	
	@Override
	public void Update(Entity entity)
	{
		
		//entity.lifecount++;
		
		
	//	entity.body.setTransparency(transparency -entity.lifecount);
	//	entity.body.scale(entity.lifecount *0.01f);
		//entity.body.scale(scale);
		//Satellite reference=(Satellite)entity;
		
		
	//  if(  (System.nanoTime() -  entity.timeDelta) >= 20000000)
	//  {
	//  }
}
	
	
	
	

	
	
}
