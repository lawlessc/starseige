package Entity_types;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

import EffectsOnEntities.SolidCollisionEffect;
import Entity_properties.Selectable;
import Entity_properties.Shieldable;
import Entity_properties.Touchable;
import Entity_types.BaseEntitys.Entity;
import RenderHooks.AtmosphereFogRenderHook;
import RenderHooks.AtmosphereRenderHook;
import RenderHooks.AuroraShaderRenderHook;
import RenderHooks.CloudSphereRenderHook;
import RenderHooks.PlanetSurfaceRenderHook;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import states.State;

public class Planet  extends Entity implements Shieldable , Selectable, Touchable {

	static Object3D atmosphereSphere= null;
    static Object3D foggingSphere= null;
	static Object3D auroraSphere = null;
	public static Object3D cloudSphere = null;

	//static Object3D colouredAura = null;

	public PlanetSurfaceRenderHook ssf;

	public CloudSphereRenderHook csr;
	public AtmosphereRenderHook asr;
    public AtmosphereFogRenderHook fog;
	public AuroraShaderRenderHook auroraRenderHookShader;

	//public AuroraShaderRenderHook colouredAuraRenderHookShader;

//	public SimpleVector LandTint= new SimpleVector(0,0,0);
//	public SimpleVector WaterTint= new SimpleVector(0,0,0);
//	public SimpleVector AtmosphereTint= new SimpleVector(0,0,0);

	public SimpleVector sunpos;

	//public float population_million;
	public float planet_key;

	
	public Planet(SimpleVector pos, SimpleVector moment, State state,
				  Object3D bodyReference,Object3D cloudSphere,Object3D atmosphereSphere,Object3D fogSphere,
				  Object3D auroraSphere ,GLSLShader surfaceshader,
				  GLSLShader cloudshader,GLSLShader atmosphereshader,
				  GLSLShader fogShader,GLSLShader auroraShader


	)
	{
		super( pos, moment,  state,  bodyReference);

		//planet_key= (System.currentTimeMillis()+((float) Math.random()))*100.00000001f; //May need to add a random number here also
		planet_key= (float) Math.random()*100.00000001f; //May need to add a random number here also


		//planetTint = new SimpleVector((0.5),(0.5),(0.5));
		size = 4.05f;
		this.cloudSphere =cloudSphere;
		this.atmosphereSphere =atmosphereSphere;
		this.foggingSphere = fogSphere;
		this.auroraSphere = auroraSphere;

		ssf = new PlanetSurfaceRenderHook(this,surfaceshader);
		csr = new CloudSphereRenderHook(this,cloudshader);
		asr = new AtmosphereRenderHook(this,atmosphereshader);
		fog = new AtmosphereFogRenderHook(this,fogShader);
		auroraRenderHookShader = new AuroraShaderRenderHook(this,auroraShader);



		this.cloudSphere.setOrigin(new SimpleVector(0,0,0));
		this.cloudSphere.translate(position);

		this.cloudSphere.setShader(cloudshader);
		this.cloudSphere.setRenderHook(csr);

		this.atmosphereSphere.setOrigin(new SimpleVector(0,0,0));
		this.atmosphereSphere.translate(position);
		this.atmosphereSphere.setShader(atmosphereshader);
		this.atmosphereSphere.setRenderHook(asr);



		this.foggingSphere.setOrigin(new SimpleVector(0,0,0));
		this.foggingSphere.translate(position);
		this.foggingSphere.setShader(fogShader);
		this.foggingSphere.setRenderHook(fog);

		this.auroraSphere.setOrigin(new SimpleVector(0,0,0));
		this.auroraSphere.translate(position);
		this.auroraSphere.setShader(auroraShader);
		this.auroraSphere.setRenderHook(auroraRenderHookShader);

		sunpos= new SimpleVector(20,0,0);

		body.setOrigin(new SimpleVector(0,0,0));
		body.translate(position);
		body.setShader(surfaceshader);
		body.setRenderHook(ssf);
	}


	@Override
	public void add_to_world(World world) 
	{
		world.addObjects(body,atmosphereSphere, foggingSphere,auroraSphere,cloudSphere);
	}

	@Override
	public void fire() {
		//
	}


	@Override
	public void Update() {
		super.Update();

		cloudSphere.clearTranslation();
		cloudSphere.translate(position);
		atmosphereSphere.clearTranslation();
		atmosphereSphere.translate(position);
		foggingSphere.clearTranslation();
		foggingSphere.translate(position);
		auroraSphere.clearTranslation();
		auroraSphere.translate(position);


	}

	@Override
	public void delete()
	{
		setChanged();
		notifyObservers();
		System.out.println(this.getClass() + "class type");

		isAlive=false;//This is needed to stop some objects (the camera) staying latched.
		SingletonObjects.game_objects.remove(this);

		SingletonObjects.world.removeObject(body);

		SingletonObjects.world.removeObject(atmosphereSphere);
		SingletonObjects.world.removeObject(foggingSphere);
		SingletonObjects.world.removeObject(auroraSphere);
		SingletonObjects.world.removeObject(cloudSphere);


		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public boolean isShieldable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void spawnShield() {

//		Main.SingletonObjects.object_factory.createShield(
//				this,
//				EntityFactory.states.shield_state, new SimpleVector(0.1,1,0.4));

	}

	@Override
	public void shieldOff() {

	}

	@Override
	public boolean isSelectable() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEthereal() {
		return false;
	}

	@Override
	public SimpleVector collisionPoint() {
		return position;
	}

	@Override
	public float collisionArea() {
		return 4.005f;
	}//half of the diameter


	@Override
	public void collisionEffect(Entity x) {
		if(x instanceof CarrierRocket){
			//prevents carrier from colliding with homeplanet
		}
		else {
			SolidCollisionEffect.INSTANCE.applyEffect(x);
		}
	}


	@Override
	public void onSingleTap(RayPickPoints p ,MotionEvent me) {
      SingletonObjects.cameraCursor.setFollow(this);
	}

	@Override
	public void onDoubleTap(RayPickPoints p,MotionEvent me) {


	}


	public void actionDown(RayPickPoints points,MotionEvent me) {
		SingletonObjects.cameraCursor.downAction(me);
	}


	public void actionUp() {
		SingletonObjects.cameraCursor.upAction();
	}

	public void actionMove(MotionEvent me, RayPickPoints points) {


		SingletonObjects.cameraCursor.rotationActionByPlanet(me);
	}


	public void setScaleFactor(ScaleGestureDetector m) {
		SingletonObjects.cameraCursor.scaling(m);
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
		return true;
	}
}
