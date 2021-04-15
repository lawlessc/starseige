package states;


import Entity_types.BaseEntitys.Entity;

public class OrbitingState extends State {


	public OrbitingState() {
		super();
	}
	

	@Override
	public void Enter(Entity entity) {
		entity.affectedBygravity=true;
	}
	@Override
	public void Exit(Entity entity) {
	
		
	}

	@Override
	public void Update(Entity entity)
	{
		

	}



}
