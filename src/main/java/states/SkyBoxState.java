package states;



import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;


public class SkyBoxState extends State {


	public SkyBoxState() {
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

		entity.position= SingletonObjects.cam.getPosition();
		entity.body.setOrigin(entity.position);

	}


	

}
