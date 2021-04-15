package Entity_types;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Light;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;
import RenderHooks.SunRenderHook;
import baseinterfacesclasses.SingletonObjects;
import states.State;

public class Sun extends Entity {


	SunRenderHook srh = null;

	public Sun( SimpleVector pos, SimpleVector moment, State state, Object3D bodyReference , GLSLShader shader)
	{
		super( pos,  moment,  state,  bodyReference);

		this.velocity = new SimpleVector(0,0,0);
		this.steerforce = new SimpleVector(0,0,0);



		srh = new SunRenderHook(this,shader);

		body.setOrigin(new SimpleVector(0,0,0));
		body.translate(position);
		body.setShader(shader);
		body.setRenderHook(srh);


		Light light = new Light(SingletonObjects.world);
		light.enable();
		light.setIntensity(255, 255, 255);
		light.setAttenuation(-1);
		light.setDiscardDistance(-1);

		//light.setPosition(new SimpleVector(200, 0, 0));

		light.setPosition(this.position);
	}


	
	@Override
	public void Update() {
	   body.rotateY( 0.001f);
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
		return null;
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
