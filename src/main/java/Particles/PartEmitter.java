/* Created by Andre Bruver */

package Particles;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;
import com.threed.jpct.util.ExtendedPrimitives;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import Entity_types.BaseEntitys.Entity;
import Particles.ParticleBehaviours.ParticleBehaviour;
import RenderHooks.ParticleRenderHook;
import baseinterfacesclasses.SingletonObjects;


public class PartEmitter  implements Observer {


	GLSLShader shader= null;
	public SimpleVector baseColour =new SimpleVector(0,0,0);
	public SimpleVector glowColour =new SimpleVector(0,0,0);
	Object3D attachmentObject;//just a dummy object for attaching to another object

	Random rn= new Random();
	ArrayList<Particle> lst;
	private int quantity; //limit of particles visible
	private String textureName; //the name of texture previous loaded
	private Object3D particlebod;//a body to be used in cases where a particle is an object3d instead of a billboard.
	private float delay; //time to create particle
	private float inc;
	private World world;
	//private World glowWorld;
	public SimpleVector pos; //start position
	private float w; //start width
	private float h; //start height
	private float speed; //movement speed
	private float duration; //delay for change transparency value
	boolean visible; //hide or show
	private SimpleVector maxDir; //max random direction on create
	private SimpleVector dir; //direction
	private boolean scale; //if true particle grow up
	private float maxscale; //max grow up

	public  boolean repeat;// if true particles will be continously emmitted, if false emmitter dies.
    private  boolean firstRun;//true if on the first run.


	private Entity  attachedTo;
	private Boolean isAttached = false;
	public Boolean die =false;
	private Boolean setToDie = false;
	private Boolean randomDirections=false;
	public Boolean glows = false;
	private Boolean randomColours = false;

	public boolean  isOn=true;//we use this for objects that have a particles emitter which isn't always on.
	                          //if the particle system is off existing particles will continue to move but new ones should not be produced.


	private ParticleBehaviour particleBehaviour = null;


	//pos - start position
	//w, h - width and height
	//textureName - The name of the texture
	//quantidade- limit of particles visible
	//delay - time to create particle
	//speed - movement speed
	//duration - delay for change transparency value
	public PartEmitter(SimpleVector pos, SimpleVector direction, float w, float h, String textureName,
					   int quantidade,
					   float delay, float speed, float duration, boolean repeat, float maxscale)
	{

		this.particlebod= ExtendedPrimitives.createSprite(w, h);
		this.shader = SingletonObjects.object_factory.particle_shader;

		this.quantity=quantidade;
		this.textureName=textureName;
		this.delay=delay;

		this.world = SingletonObjects.world;
		this.pos=pos;
		this.w=w;
		this.h=h;
		this.speed=speed;
		this.lst=new ArrayList<Particle>();
		this.duration = duration;
		visible=true;
		this.maxDir=new SimpleVector();
		this.maxDir.x=1.0f;
		this.maxDir.y=1.0f;
		this.maxDir.z=1.0f;
		this.dir=new SimpleVector();
		this.dir.x=direction.x;
		this.dir.y=direction.y;
		this.dir.z=direction.z;
		this.scale=true;
		this.maxscale=2f;

		this.repeat= repeat;
		this.firstRun = true;
	}


	public PartEmitter(Entity attachentEnt, SimpleVector attachmentPoint , SimpleVector direction, float w, float h, String textureName,
					   int quantidade,
					   float delay, float speed, float duration, boolean repeat, float maxscale)
	{


		this.setAttachedTo(attachentEnt,attachmentPoint);

		this.particlebod= ExtendedPrimitives.createSprite(w, h);
		this.shader =SingletonObjects.object_factory.particle_shader;

		this.quantity=quantidade;
		this.textureName=textureName;
		this.delay=delay;

		this.world = SingletonObjects.world;

		this.w=w;
		this.h=h;
		this.speed=speed;
		this.lst=new ArrayList<Particle>();
		this.duration = duration;
		visible=true;
		this.maxDir=new SimpleVector();
		this.maxDir.x=1.0f;
		this.maxDir.y=1.0f;
		this.maxDir.z=1.0f;
		this.dir=new SimpleVector();
		this.dir.x=direction.x;
		this.dir.y=direction.y;
		this.dir.z=direction.z;
		this.scale=true;
		this.maxscale=2f;

		this.repeat= repeat;
		this.firstRun = true;
	}


	public PartEmitter(SimpleVector pos, SimpleVector direction, float w, float h, Object3D obj, int quantidade,
					   float delay, float speed, float duration, boolean repeat)
	{

		this.particlebod=obj;
		this.shader =SingletonObjects.object_factory.particle_shader;
		this.quantity=quantidade;
		this.delay=delay;
		this.world = SingletonObjects.world;
		this.pos=pos;
		this.w=w;
		this.h=h;
		this.speed=speed;
		this.lst=new ArrayList<Particle>();
		this.duration = duration;
		visible=true;
		this.maxDir=new SimpleVector();
		this.maxDir.x=1.0f;
		this.maxDir.y=1.0f;
		this.maxDir.z=1.0f;
		this.dir=new SimpleVector();
		this.dir.x=direction.x;
		this.dir.y=direction.y;
		this.dir.z=direction.z;
		this.scale=true;
		this.maxscale=2f;
		this.repeat= repeat;
		this.firstRun = true;
	}




	public void setAttachedTo(Entity entity,SimpleVector attachmentPoint)
	{

		entity.addObserver(this);
		attachmentObject = Object3D.createDummyObj();
		attachmentObject.setOrigin(attachmentPoint);

		attachmentObject.clearTranslation();
		attachmentObject.addParent(entity.body);
		attachmentObject.translate(entity.body.getTranslation());

		this.attachedTo=entity;
		this.isAttached = true;
	}

	public void setGlowColour(SimpleVector colour)
	{
		this.glowColour = colour;
	}

	public void setColour(SimpleVector colour)
	{
		this.baseColour = colour;
	}


	public  void setRandomColours(Boolean b)
	{
		this.randomColours = b;
	}
	//Set start position
	public void setPos(SimpleVector pos)
	{
		this.pos=pos;
	}
	//Get start position
	public SimpleVector getPos()
	{
		return pos;
	}
	//Set max random Direction
	//Use a normalize vector
	public void setMaxDir(SimpleVector dir)
	{
		maxDir=dir;
	}
	public SimpleVector getMaxDir()
	{
		return maxDir;
	}
	//Set initial direction
	public void setDir(SimpleVector dir)
	{
		this.dir=dir;
	}
	
	//Get initial direction
	public SimpleVector getDir()
	{
		return dir;
	}
	//Set the time counter to wait to create particle
	public void setDelay(int delay)
	{
		this.delay=delay;
	}
	public float getDelay()
	{
		return delay;
	}
	public float getDuration()
	{
		return duration;
	}
	//Set the time counter to change to the next transparency value
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	//Change the initial size
	public void setSize(float w,float h)
	{
		this.w=w;
		this.h=h;
	}

	public void setRandomDirections(boolean bu)
	{
		randomDirections = bu;
	}
	
	//Get width
	public float getW()
	{
		return w;
	}
	
	//Get height
	public float getH()
	{
		return h;
	}


	public void checkifDie()
	{
		if( (isAttached == true)&&(attachedTo==null))
		{
			die=true;
		}

	}


	public void die()
	{
		die=true;
		SingletonObjects.particleManager.remove(this);
	}

	public void killEmmitter()
	{

		SingletonObjects.particleManager.remove(this);

	}


	public void addOne()
	{

		inc = 0.0f;
		float dx = (float) Math.random();
		if (dx > maxDir.x)
			dx = maxDir.x;
		float dy = (float) Math.random();
		if (dy > maxDir.y)
			dy = maxDir.y;
		float dz = (float) Math.random();
		if (dz > maxDir.z)
			dz = maxDir.z;

		dx = dx * (dir.z);
		dy = dy * (dir.y);
		dz = dz * (dir.z);


		SimpleVector dir = new SimpleVector(dx, dy, dz);
		if(randomDirections)
		{

			dir = new SimpleVector((rn.nextFloat()*2)-1,
					(rn.nextFloat()*2)-1,
					(rn.nextFloat()*2)-1);
		}
		Particle obj = new Particle(dir.normalize(),this,shader);

		if(randomColours)
		{

			SimpleVector color = new SimpleVector(rn.nextFloat(),
					rn.nextFloat(),
					rn.nextFloat());

			obj.baseParticleColour=color;
			obj.glowParticleColour=color;

		}


		lst.add(obj);
	}

	//Call on DrawFrame
	public void Update(float timeDiff) {

		if(isAttached)
		{

			attachmentObject.clearTranslation();
			attachmentObject.translate(attachedTo.body.getTranslation());
			pos = attachmentObject.getTranslation();

		}


		if (!setToDie){

			if (!this.visible)
				return;
		inc+=timeDiff*0.001;

		//this creates new particles when they run out and on initialization
			if(isOn) {
				if (repeat) {


					if ((inc >= delay) && (lst.size() < quantity)) {


						addOne();
					}


				}
				//adds all at once
				if ((!repeat) && firstRun) {
					for (int xi = 0; xi < quantity; xi++) {
						addOne();
					}
					firstRun = false;
				}
			}


		if (lst.size() == quantity) {
			firstRun = false;
		}


		for (int i = 0; i < lst.size(); i++) {
			Particle o = lst.get(i);

			if (o != null) {

				o.Move(timeDiff);


				Object3D dentro = o.getObj();

				int t = dentro.getTransparency();

				if (t == 0) {

					o.Release();
					//o = null;
					lst.remove(i);
				}

				if ((!repeat) && (lst.size() == 0)) {
					die = true;
				}

				checkifDie();

				if (die) {
					killEmmitter();
				}


			}

		}
	}
	}





	public void clear(World world)
	{
		for (int i =0 ; i < lst.size(); i++)
		{
			world.removeObject(lst.get(i).body);

		}
       lst.clear();

		//killEmmitter(); why was i calling this?!
	}

	
	//res=true - the particle will grow
	//Default is true
	public void SetScale(boolean res)
	{
		scale=res;
	}
	
	public boolean isScale()
	{
		return scale;
	}
	
	//Set Max scale of particle is isScale() is true
	public void SetMaxScale(float max)
	{
		maxscale=max;
	}
	
	public float GetMaxScale()
	{
		return maxscale;
	}

	public void glows(boolean b) {

		glows = b;
	}

	@Override
	public void update(Observable observable, Object data) {
		killEmmitter();
	}


	public class Particle
	{
		private Object3D body;
		SimpleVector ppos;
		SimpleVector pdir;
		float inc_duracao;
		public SimpleVector baseParticleColour =baseColour;
		public SimpleVector glowParticleColour =glowColour;
		ParticleRenderHook renderHook = null;

		public Particle(SimpleVector dir,PartEmitter emmitter,GLSLShader shader)
		{

			renderHook = new ParticleRenderHook(this, emmitter, shader);
			this.body =ExtendedPrimitives.createSprite(w, h);
			//this.body = particlebod;
			this.body.setShader(shader);
			this.body.setRenderHook(renderHook);

			this.ppos=pos;
			this.body.setOrigin(ppos);
			this.body.setVisibility(true);
			this.body.setTransparency(200);
			this.body.setTexture(textureName);
			this.body.build();
			this.body.compile();
			world.addObject(this.body);
			inc_duracao=0;
			this.pdir=dir;
		}

		public Particle(SimpleVector dir, Object3D obj,PartEmitter emmitter,GLSLShader shader)
		{

			renderHook = new ParticleRenderHook(this, emmitter, shader);
			this.body = particlebod;//need to copy a new body each time!
			this.body.setShader(shader);
			this.body.setRenderHook(renderHook);

			this.ppos=pos;
			this.body.setOrigin(ppos);
			this.body.setVisibility(true);
			this.body.setTransparency(200);
			//this.body.setTexture(textureName);//texture should have already been set.
			this.body.build();
			this.body.compile();
			world.addObject(this.body);
			inc_duracao=0;
			this.pdir=dir;

		}

		public Object3D getObj()
		{
			return this.body;
		}

		public void Move(float timeDiff)
		{
			if (!visible)
				return;

			int t= body.getTransparency();


		

			inc_duracao++;

			if (inc_duracao> duration)
			{
				inc_duracao=0;
				t--;
				if (t<0)
					t=0;
			}

			if (t<=0)
				t=0;
			body.setTransparency(t);

			if (t==0)
				return;
			ppos= body.getOrigin();
			ppos.x=ppos.x+(pdir.x*speed*timeDiff);
			ppos.y=ppos.y+(pdir.y*speed*timeDiff);
			ppos.z=ppos.z+(pdir.z*speed*timeDiff);
			body.setOrigin(ppos);

			if (scale)
			{
				if (body.getScale()<maxscale)
					body.scale(1.005f);
			}


		}

		public void Release()
		{
			body.clearObject();
			body =null;
		}
	}


}
