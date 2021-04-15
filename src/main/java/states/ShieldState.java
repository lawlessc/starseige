package states;

import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;


public class ShieldState extends State {

//	Ent
	
	public ShieldState() {
		super();
	}
	

	
	@Override
	public void Enter(Entity entity) {
	}
	@Override
	public void Exit(Entity entity) {
		// TODO Auto-generated method stub
	}

	
	
	@Override
	public void Update(Entity entity)
	{
		  if(entity.body != null )//&& entity.parent !=null)
		  {
			  entity.position = new SimpleVector(entity.Parent_Entity.position);
			//  entity.body.setOrigin(entity.position);
		  }

	 }
	  
	  
	
	}


	


