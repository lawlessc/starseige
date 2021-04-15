package gameObjects;

import android.content.Context;
import android.content.res.Resources;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.R;

import java.util.Observable;
import java.util.Random;

import Entity_properties.Allegiance;
import Entity_types.BasicMissle;
import Entity_types.Asteroid;
import Entity_types.BatterySatellite;
import Entity_types.Beams.Beam;
import Entity_types.Beams.DamagingRedBeam;
import Entity_types.CarrierRocket;
import Entity_types.BaseEntitys.Entity;
import Entity_types.ExplosionSpace;
import Entity_types.FireWorkExp;
import Entity_types.LaserSatellite;
import Entity_types.LaserShip;
import Entity_types.MissleGunShip;
import Entity_types.Planet;
import Entity_types.BaseEntitys.SatBasic;
import Entity_types.Shield;
import Entity_types.SkySphere;
import Entity_types.SpaceMine;
import Entity_types.Sun;
import Entity_types.Wormhole;
import Lights.LightBulb;
import OrbitCalcs.OrbitData;
import graphicsSetup.ObjectGraphics;
import states.EntityStates;
import states.State;

public class
EntityFactory  extends Observable /*  implements Runnable*/ {


	Random r = new Random();
	 Context context=null;
	 public static ObjectGraphics object_holder = null;
	 static GLSLShader mainShader = null;
	 static GLSLShader satShader = null;
	 static GLSLShader shieldShader = null;
     static GLSLShader glassyShader = null;
     static GLSLShader  cloudShader=null;
     static GLSLShader  ray_scattering_shader=null;
     static GLSLShader  spaceExplosionShader=null;
     static GLSLShader  atmosphere_fog_shader=null;
	 static GLSLShader sunShader = null;
	 static GLSLShader  aurora_shader=null;
	 static GLSLShader  sky_shader=null;
	static GLSLShader  planet_surface_shader=null;
	 public static GLSLShader  particle_shader= null;

	public static GLSLShader  laser_shader= null;


	public static GLSLShader  lightbulb_shader= null;

	//public  static GLSLShader  gameboy_shader=null;
	static GLSLShader  glowShader=null;

	public static GLSLShader  wormhole_shader= null;

     //public static EntityStates states=null;
     

		public EntityFactory(Context context_)
		{    
			super();
			context=context_;
		}
		

	public void  setupFactory(Resources res)
	
	{
		//states = new EntityStates();

		object_holder = new ObjectGraphics(res,context);

		mainShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.vertexshader_offset)), Loader.loadTextFile(res.openRawResource(R.raw.fragmentshader_offset)));
		shieldShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.shield_vertex)), Loader.loadTextFile(res.openRawResource(R.raw.shield_fragment)));
		glassyShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.revert)), Loader.loadTextFile(res.openRawResource(R.raw.refrag)));
		ray_scattering_shader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.atmosphere_vertex)), Loader.loadTextFile(res.openRawResource(R.raw.atmosphere_frag)));
		cloudShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.clouds_vert)), Loader.loadTextFile(res.openRawResource(R.raw.clouds_frag)));
        spaceExplosionShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.explosion_vert)), Loader.loadTextFile(res.openRawResource(R.raw.explosion_frag)));
        atmosphere_fog_shader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.fog_vert)), Loader.loadTextFile(res.openRawResource(R.raw.fog_frag)));
		aurora_shader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.aurora_vert)), Loader.loadTextFile(res.openRawResource(R.raw.aurora_frag)));

		sunShader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.sun_vert)), Loader.loadTextFile(res.openRawResource(R.raw.sun_frag)));

		sky_shader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.skysphere_vert)), Loader.loadTextFile(res.openRawResource(R.raw.skysphere_frag)));

		glowShader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.glow_vert)), Loader.loadTextFile(res.openRawResource(R.raw.glow_frag)));

		//postProcesShader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.postprocess_vert)), Loader.loadTextFile(res.openRawResource(R.raw.postprocess_frag)));

		//darkShader   = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.dark_vert)), Loader.loadTextFile(res.openRawResource(R.raw.dark_frag)));

		//gameboy_shader   = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.gameboy_vert)), Loader.loadTextFile(res.openRawResource(R.raw.gameboy_frag)));

		planet_surface_shader   = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.planet_surface_vert)),
				Loader.loadTextFile(res.openRawResource(R.raw.planet_surface_frag)));


		particle_shader   = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.particle_vert)),
				Loader.loadTextFile(res.openRawResource(R.raw.particle_frag)));


		lightbulb_shader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.light_bulb_vert)),
				Loader.loadTextFile(res.openRawResource(R.raw.light_bulb_frag)));


		wormhole_shader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.wormhole_vert)),
				Loader.loadTextFile(res.openRawResource(R.raw.wormhole_frag)));



		//satShader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.sat_star_vert)),
		//		Loader.loadTextFile(res.openRawResource(R.raw.sat_star_frag)));


		satShader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.sat_star_vert)),
				Loader.loadTextFile(res.openRawResource(R.raw.sat_star_frag)));

		laser_shader  = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.laser_fire_vert)),
				Loader.loadTextFile(res.openRawResource(R.raw.laser_fire_frag)));

	}



    //Let there be light!;
	public Sun createSun()
	{
		SimpleVector pos = new SimpleVector(400,-60,0);
		SimpleVector dir = new SimpleVector(0,0,0);

		Sun sun = new Sun(pos, dir,  null, copy(object_holder.theSun),sunShader);
		sun.allegiance= Allegiance.Untouchable;
		sendToObserver(sun);

		return sun;
	}


	public Planet createPlanet()
	{
	
		 SimpleVector pos = new SimpleVector(0,0,0);
		 SimpleVector dir = new SimpleVector(0,0,0);
		 Planet planet = new Planet(pos, dir,  EntityStates.planet_state, copy(object_holder.planetSurface),
				 copy(object_holder.cloudSphere),
				 copy(object_holder.atmosphere_scatter), copy(object_holder.atmosphere_fog), copy(object_holder.auroraSphere),
				// mainShader, cloudShader,
				 planet_surface_shader,cloudShader,
				 ray_scattering_shader,
				 atmosphere_fog_shader, aurora_shader
		 );

		sendToObserver(planet);
		planet.allegiance=Allegiance.Player;
     return planet;
	}

	
	public SatBasic createSattelite( SimpleVector pos,  SimpleVector dir,  State state,OrbitData data)
	{
		dir.scalarMul(0.5f);

		SatBasic sat;

		sat =  new SatBasic(pos, dir, state , copy(object_holder.sattellite),satShader ,data);
		sat.allegiance= Allegiance.Player;
		sat.orbiting=true;
		sat.size = 4.05f;
		sendToObserver(sat);
		return sat;
}


	public SatBasic createLaserSattelite( SimpleVector pos,  SimpleVector dir,  State state,OrbitData data)
	{
		dir.scalarMul(0.5f);

		LaserSatellite sat;

		sat =  new LaserSatellite(pos, dir, state , copy(object_holder.sattellite_laser),satShader ,data);
		sat.allegiance= Allegiance.Player;
		sat.orbiting=true;
		sat.size = 4.05f;
		sendToObserver(sat);
		return sat;
	}



//	public Beam createLaser(Entity parent, Entity target)
//	{
//
//		FiringBeam laser;
//
//		laser =  new FiringBeam(parent, target , copy(object_holder.laser_beam),laser_shader);
//		laser.allegiance= Allegiance.None;
//		//laser.size = 7.05f;
//		sendToObserver(laser);
//		return laser;
//	}


	public Beam createRedLaser(Entity parent, Entity target)
	{

		DamagingRedBeam laser;


//		position =new SimpleVector((Startpoint.x +Endpoint.x)*0.5f,
//				(Startpoint.y +Endpoint.y)*0.5f,
//				(Startpoint.z+Endpoint.z)*0.5f);


		laser =  new DamagingRedBeam(parent, target , copy(object_holder.laser_beam),laser_shader);
		laser.allegiance= Allegiance.None;
		sendToObserver(laser);
		return laser;
	}


//	public Beam createTestLaser(SimpleVector startpoint, SimpleVector endPoint)
//	{
//
//		Beam laser;
//
//		laser =  new Beam(startpoint, endPoint,copy(object_holder.laser_beam),laser_shader);
//		laser.allegiance= Allegiance.None;
//		laser.size = 7.05f;
//		sendToObserver(laser);
//		return laser;
//	}



	public BatterySatellite createBatterySattelite( SimpleVector pos,  SimpleVector dir,  State state,OrbitData data)
	{
		dir.scalarMul(0.5f);



		BatterySatellite sat;

		sat =  new BatterySatellite(pos, dir, state , copy(object_holder.sattellite),satShader ,data);
		sat.allegiance= Allegiance.Player;
		sat.orbiting=true;
		sat.size = 4.05f;
		sendToObserver(sat);
		return sat;
	}




	public  CarrierRocket createCarrierRocket(State state , int payload, OrbitData orbitData)
	{


	CarrierRocket carrier =  new CarrierRocket(new SimpleVector(0,0,0),new SimpleVector(0,0,0) , state ,
			copy(object_holder.sattellite),satShader,orbitData);

		carrier.setPayload(payload);
		//carrier.setOrbit(orbitData);
		carrier.allegiance= Allegiance.Player;
		carrier.orbiting=false;
		carrier.size=4.05f;
		sendToObserver(carrier);
		return  carrier;
	}

	public SpaceMine createSpaceMine( SimpleVector pos,  SimpleVector dir,  State state,OrbitData data)
	{
		dir.scalarMul(0.5f);

		SpaceMine sat;

		sat =  new SpaceMine(pos, dir, state , copy(object_holder.sattellite_mine),satShader ,data);
		sat.allegiance=  Allegiance.Player;
		sat.orbiting=true;
		sat.size = 4.05f;
		sendToObserver(sat);
		return sat;
	}

	public BasicMissle createAlienPlanetTorpedo(SimpleVector pos, SimpleVector dir, State state)
	{
		dir.scalarMul(0.5f);

		BasicMissle torpedo;

		torpedo =  new BasicMissle(pos, dir, state , copy(object_holder.sattellite),satShader );
		torpedo.allegiance= Allegiance.Aliens;
		torpedo.orbiting=true;
		torpedo.size = 4.05f;
		sendToObserver(torpedo);
		return torpedo;
	}

	public MissleGunShip createAlienMissleGunship( SimpleVector pos,  SimpleVector dir,  State state)
	{
		dir.scalarMul(0.5f);

		MissleGunShip gunShip;

		gunShip =  new MissleGunShip(pos, dir, state , copy(object_holder.sattellite),satShader );
		gunShip.allegiance= Allegiance.Aliens;
		gunShip.orbiting=true;
		gunShip.size = 4.05f;
		sendToObserver(gunShip);
		return gunShip;
	}

	public LaserShip createAlienLaserGunship(SimpleVector pos, SimpleVector dir, State state)
	{
		dir.scalarMul(0.5f);

		LaserShip laserShip;

		laserShip =  new LaserShip(pos, dir, state , copy(object_holder.sattellite),satShader );
		laserShip.allegiance= Allegiance.Aliens;
		laserShip.orbiting=true;
		laserShip.size = 4.05f;
		sendToObserver(laserShip);
		return laserShip;
	}


	public Asteroid createAsteroid( SimpleVector pos,  SimpleVector dir,  State state,OrbitData data)
	{


		dir.scalarMul(0.5f);
//		CarrierRocket sat;
//		sat =  new CarrierRocket(pos, dir, state , copy(object_holder.rocket),mainShader ,data);

		Asteroid sat;
		sat =  new Asteroid(pos, dir, state , copy(object_holder.asteroid),mainShader ,data);
        sat.allegiance= Allegiance.Unocupied;
		sat.orbiting=true;
		sat.size = 4.05f;
		sendToObserver(sat);
		return sat;
	}


	public LightBulb createLightBulb(SimpleVector attachmentPoint,Entity parent)
	{

		LightBulb bulb = new LightBulb(new SimpleVector(0,10,0),new SimpleVector(0,0,0),
				null, //STATE
				copy(object_holder.lightBulb),
				attachmentPoint,parent,lightbulb_shader);


      ///NEED TO ADD SHADER AND RENDER HOOK

		sendToObserver(bulb);
		return bulb;
	}



	public void explosionWithOrbitDebris(OrbitData data,State state)
	{
		//Create explosionObject, if two sats collide should this be called twice?




		//Create Debris
		  //Randomly create one or two peices.
		  //Each one should use variation of parent objects orbit.
	}






	//If explosion is not direction just use 0,0,0
	public   ExplosionSpace createExplosion(SimpleVector position,SimpleVector direction)
	{
		ExplosionSpace explosion;
		explosion =
		new ExplosionSpace(position, new SimpleVector(0.7, 0, 0.0), EntityStates.explosionState,
				copy(object_holder.explosionSpace),spaceExplosionShader, direction );
		explosion.allegiance=Allegiance.Untouchable;
		sendToObserver(explosion);
		return  explosion;
	}

    //this is technically not an entity, just an orphan particle effect right now
	public   FireWorkExp createFirework(SimpleVector position)
	{
		FireWorkExp firework;
		firework =
				new FireWorkExp(position);
		//firework.allegiance=Allegiance.Untouchable;
		//sendToObserver(explosion);
		return  firework;
	}




	
	
	public    void createShield(final Entity parent,final State state,final SimpleVector colour)
	{
		
new Thread(new Runnable() {
    public void run() {
    
    	Shield shield =  new Shield(parent,parent.position,new SimpleVector(0.0,0,0.0) , state ,
				copy(object_holder.shieldBody) ,shieldShader,colour);
		sendToObserver(shield);
    }
}).start();

	}
	
	public   SkySphere createSkySphere()
	{
	 SkySphere skysphere;
		skysphere = new SkySphere(new SimpleVector(0,0,0),new SimpleVector(0.0,0,0.0) , EntityStates.skybox_state ,
				copy(object_holder.cellestialSphere),sky_shader);

		skysphere.allegiance=Allegiance.Untouchable;
		sendToObserver(skysphere);
        return  skysphere;
	}

    public Wormhole createWormhole(SimpleVector position)
    {
        Wormhole wormhole;
        wormhole = new Wormhole(position,new SimpleVector(0,0,0.0) , null ,
				           copy(object_holder.wormhole),wormhole_shader);
        sendToObserver(wormhole);

		return wormhole;
    }


	public static Object3D copy(Object3D bluePrint) {
		Object3D copy=new Object3D(bluePrint, true);
		copy.shareCompiledData(bluePrint);
		copy.shareTextureData(bluePrint);
		
		return copy;
	}

    public   void sendToObserver(Entity entity)
    {
        setChanged();
        notifyObservers(entity);
    }

    public SimpleVector randomPointAtdistance(float distance)
	{
		SimpleVector ret = new SimpleVector(0,0,0);

		ret.x = + (r.nextFloat()*2)-1 ;
		ret.y = + (r.nextFloat()*2)-1;
		ret.z = + (r.nextFloat()*2)-1;

		ret = ret.normalize();
		ret.scalarMul(distance);

        return ret;
	}

	public SimpleVector randomPointAtdistanceNearPoint(float distance, SimpleVector newp)
	{
        SimpleVector ret = new SimpleVector(0,0,0);

		ret.x = + (r.nextFloat()*2)-1 ;
		ret.y = + (r.nextFloat()*2)-1;
		ret.z = + (r.nextFloat()*2)-1;

		ret = ret.normalize();
		ret.scalarMul(distance);

		ret.add(newp);
		return ret;
	}


}
