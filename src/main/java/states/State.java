package states;

import Entity_types.BaseEntitys.Entity;


public abstract class State
{

	public State()
	{
		// TODO Auto-generated constructor stub
	//	this.entity = entity;
	}
	
	public State(Entity entity)
	{
		// TODO Auto-generated constructor stub
	//	this.entity = entity;
	}

	 public abstract void Enter(Entity entity);
	 
	 public abstract void Exit(Entity entity);
	 
	 public abstract void Update(Entity entity);





	

}