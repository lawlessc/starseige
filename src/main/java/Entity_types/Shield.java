package Entity_types;


import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;
import RenderHooks.shieldRenderHook;
import states.State;

public class Shield extends Entity {
	
	shieldRenderHook srh = null;
	public SimpleVector shieldcolour;


	public Shield(Entity parent,SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference  ,GLSLShader shader ,SimpleVector shieldcolour)
	{
		super( pos,  moment,  state,  bodyReference);

		this.shieldcolour=shieldcolour;
		this.Parent_Entity =parent;
		srh = new shieldRenderHook(this,shader);
		body.setOrigin(new SimpleVector(0,0,0));
		body.translate(parent.position);
		body.setShader(shader);
		body.setRenderHook(srh);
	}
	

	@Override
	public void Update() {
		super.Update();
         if(body ==null)
         {
        	  System.out.println("fail body");
         }
		
		if(current_state != null)
		{  
			current_state.Update(this);
		}

		 if(body != null || Parent_Entity ==null)
		 {
			// body.clearTranslation();
			// body.translate(Parent_Entity.position);
		 }

		 if( Parent_Entity.isAlive ==false)
		 {
			 delete();
		 }
	}

	@Override
	public void fire() {
		//Maybe a special case could be made here for shields to destroy incoming missles?
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
		return false;
	}

	@Override
	public SimpleVector collisionPoint() {
		return this.position;
	}

	@Override
	public float collisionArea() {
		return 5;
	}

	@Override
	public void collisionEffect(Entity x) {

	}
}
