package Entity_types;


import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;
import RenderHooks.skysphereRenderHook;
import states.State;

public class SkySphere extends Entity {
	
	
	skysphereRenderHook srh;

	public SkySphere(SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference,GLSLShader shader)
	{
		super( pos,  moment,  state,  bodyReference);




		srh = new skysphereRenderHook(this,shader);
		//	body.setOrigin(position);
		body.setShader(shader);
		body.setRenderHook(srh);
	}
	
	


	@Override
	public void Update() {
		if(current_state != null)
		{  
			current_state.Update(this);
		}
	}

	@Override
	public void fire() {

	}


	@Override
	public boolean canFire() {
		return false;
	}

	@Override
	public float firingDistance() {
		return 0;
	}

	@Override
	public Boolean isTargetable() {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public boolean isEthereal() {
		return true;
	}

	@Override
	public SimpleVector collisionPoint() {
		return null;
	}

	@Override
	public float collisionArea() {
		return 0;
	}

	@Override
	public void collisionEffect(Entity x) {

	}
}
