package graphicsSetup;

import android.content.Context;
import android.content.res.Resources;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureInfo;
import com.threed.jpct.TextureManager;
import com.threed.jpct.shader.ExtendedPrimitives;
import com.threed.jpct.shader.R;

//class should contain original Object3D's for copying
public class ObjectGraphics {
	
	Context context=null;
	public Object3D theSun = null;

	public Object3D planetSurface = null;
	public Object3D cloudSphere = null;
    public Object3D atmosphere_fog = null; //This will appear transparent in center but less so at edge.
	public Object3D atmosphere_scatter = null;
//	public Object3D planetary_ring = null;
	public Object3D auroraSphere=null;

	public Object3D shieldBody =null;
    public Object3D cellestialSphere = null;
   // public Object3D rocket = null;//rock = Loader.load3DS("example/rock.3ds", 15f)[0];

    public Object3D wormhole=null; //basically an inverse sphere
    public Object3D explosionSpace = null;
	public Object3D uiplane= null;

	public Object3D verticalArrow = null;
	public Object3D asteroid=null;

//	public Object3D satte = null;

//	public Object3D orb=null;//This isa flat orb object for use by particles lights etc.

    public Object3D lightBulb=null;
	public Object3D rocketStage=null;

	public Object3D sattellite=null;
	public Object3D sattellite_mine=null;
	public Object3D sattellite_laser=null;
	public Object3D sattellite_battery=null;

	public Object3D laser_beam=null;
	
	public ObjectGraphics(Resources res,Context context){
		this.context=context;
		LoadTextures(res);
		verticalarrowbodycreate(res);
		//sunBodyCreate();
		sunBodyFlatCreate();
		shieldbodycreate(res);
		skyspherecreate(res);
		planetbodycreate(res);
		atmospherecreate(res);
		//rocketbodycreate(res);
	//	wormholecreate(res);
        spaceExplosionCreate(res);
		//setTeapot(res);
		setAsteroid(res);
		lightBulbCreate(res);
		sattelliteCreate( res);
		laserShotCreate( res);
		sattelliteLaserCreate(res);
		sattelliteMineCreate(res);
	}

	public class textureStrings
	{

		//public static  String newgame= "new"


	}

	public void LoadTextures(Resources res)
	{
		TextureManager tm = TextureManager.getInstance();
		tm.addTexture("chunk1", new Texture(res.openRawResource(R.raw.chunk1),true));
		//Texture face = new Texture(res.openRawResource(R.raw.earthtexture));
		//Texture face = new Texture(res.openRawResource(R.raw.earthtexture2));
		//	face.setTextureCompression(true);
		//	tm.addTexture("face", face);

		tm.addTexture("newgame", new Texture(res.openRawResource(R.raw.newgame_sdf),true));
		tm.addTexture("loadgame", new Texture(res.openRawResource(R.raw.loadgame_sdf),true));
		tm.addTexture("quitgame", new Texture(res.openRawResource(R.raw.quitgame_sdf),true));

		tm.addTexture("zero", new Texture(res.openRawResource(R.raw.zero),true));
		tm.addTexture("one", new Texture(res.openRawResource(R.raw.one),true));
		tm.addTexture("two", new Texture(res.openRawResource(R.raw.two),true));
		tm.addTexture("three", new Texture(res.openRawResource(R.raw.three),true));
		tm.addTexture("four", new Texture(res.openRawResource(R.raw.four),true));
		tm.addTexture("five", new Texture(res.openRawResource(R.raw.five),true));
		tm.addTexture("six", new Texture(res.openRawResource(R.raw.six),true));
		tm.addTexture("seven", new Texture(res.openRawResource(R.raw.seven),true));
		tm.addTexture("eight", new Texture(res.openRawResource(R.raw.eight),true));
		tm.addTexture("nine", new Texture(res.openRawResource(R.raw.nine),true));

		tm.addTexture("playbutton", new Texture(res.openRawResource(R.raw.noun_playpng_sdf),true));
		tm.addTexture("pausebutton", new Texture(res.openRawResource(R.raw.noun_pausepng_sdf),true));
		tm.addTexture("nightmode", new Texture(res.openRawResource(R.raw.noun_moon),true));
		tm.addTexture("researchbutton", new Texture(res.openRawResource(R.raw.noun_flaskpng_sdf),true));


		tm.addTexture("arrowup", new Texture(res.openRawResource(R.raw.arrowup),true));
		tm.addTexture("arrowdown", new Texture(res.openRawResource(R.raw.arrowdown),true));
		tm.addTexture("arrowleft", new Texture(res.openRawResource(R.raw.arrowleft),true));
		tm.addTexture("arrowright", new Texture(res.openRawResource(R.raw.arrowright),true));

		//tm.addTexture("testcross", new Texture(res.openRawResource(R.raw.testcrosssdf),true));

		tm.addTexture("disc", new Texture(res.openRawResource(R.raw.discsdf),true));
		tm.addTexture("textureless", new Texture(res.openRawResource(R.raw.textureless)));
		tm.addTexture("texturelessblu", new Texture(res.openRawResource(R.raw.texturelessblu)));

		tm.addTexture("gloworb", new Texture(res.openRawResource(R.raw.gloworb),true));
		tm.addTexture("satstar", new Texture(res.openRawResource(R.raw.satstar),true));
		tm.addTexture("lasertx", new Texture(res.openRawResource(R.raw.laserbasicsdf),true));

		tm.addTexture("diamondglyph", new Texture(res.openRawResource(R.raw.nounrhombusdf),true));
		tm.addTexture("skullglyph", new Texture(res.openRawResource(R.raw.noun_skull),true));
		tm.addTexture("launchglyph", new Texture(res.openRawResource(R.raw.laser_button),true));
		tm.addTexture("laser_button", new Texture(res.openRawResource(R.raw.laser_button),true));
		tm.addTexture("mineglyph", new Texture(res.openRawResource(R.raw.mine_sdf),true));
	}

//	public void rocketbodycreate(Resources res) {
//			rocket= LoadModel.loadModel("rocket_ICBM.3ds", 0.05f, context);
//	}

	public void lightBulbCreate(Resources res)
	{
		lightBulb = com.threed.jpct.util.ExtendedPrimitives.createSprite(0.5f, 0.5f);
		lightBulb.setTexture("gloworb");
		lightBulb.setTransparency(3);
		lightBulb.setCulling(true);
	}

	public void sattelliteCreate(Resources res)
	{
		sattellite = com.threed.jpct.util.ExtendedPrimitives.createSprite(0.5f, 0.5f);

		TextureInfo textures = new TextureInfo(TextureManager.getInstance().getTextureID("satstar"));
		textures.add(TextureManager.getInstance().getTextureID("diamondglyph"), TextureInfo.MODE_BLEND);
		sattellite.setTexture(textures);

		sattellite.setTransparency(3);
		sattellite.setCulling(true);
	}

	public void sattelliteLaserCreate(Resources res)
	{
		sattellite_laser = com.threed.jpct.util.ExtendedPrimitives.createSprite(0.5f, 0.5f);

		TextureInfo textures = new TextureInfo(TextureManager.getInstance().getTextureID("satstar"));
		textures.add(TextureManager.getInstance().getTextureID("laser_button"), TextureInfo.MODE_BLEND);
		sattellite_laser.setTexture(textures);

		sattellite_laser.setTransparency(3);
		sattellite_laser.setCulling(true);
	}


	public void sattelliteMineCreate(Resources res)
	{
		sattellite_mine = com.threed.jpct.util.ExtendedPrimitives.createSprite(0.5f, 0.5f);

		TextureInfo textures = new TextureInfo(TextureManager.getInstance().getTextureID("satstar"));
		textures.add(TextureManager.getInstance().getTextureID("mineglyph"), TextureInfo.MODE_BLEND);
		sattellite_mine.setTexture(textures);

		sattellite_mine.setTransparency(3);
		sattellite_mine.setCulling(true);
	}

	public void sattelliteSolarCreate(Resources res)
	{
		sattellite = com.threed.jpct.util.ExtendedPrimitives.createSprite(0.5f, 0.5f);

		TextureInfo textures = new TextureInfo(TextureManager.getInstance().getTextureID("satstar"));
		textures.add(TextureManager.getInstance().getTextureID("diamondglyph"), TextureInfo.MODE_BLEND);
		sattellite.setTexture(textures);

		sattellite.setTransparency(3);
		sattellite.setCulling(true);
	}




	public void laserShotCreate(Resources res)
	{

		laser_beam = com.threed.jpct.util.ExtendedPrimitives.createPlane(1f,8);//Extra quads seems to eliminate the disappearing issue, but not fully
		laser_beam.setBillboarding(false);
		laser_beam.setTexture("lasertx");
		laser_beam.setTransparency(3);
		laser_beam.setCulling(false);
	}

	public void verticalarrowbodycreate(Resources res) {

		verticalArrow= LoadModel.loadModel("arrow.3ds", 0.5f, context);
		verticalArrow.setCulling(true);
		verticalArrow.setTexture("textureless");
	}

	public void setAsteroid(Resources res)
	{
		asteroid= LoadModel.loadModel("asteroid3ds.3ds", 0.05f, context);
		asteroid.setCulling(true);
		asteroid.setTexture("texturelessblu");
	}







	public void Planes()
	{


	}


//	public void sunBodyCreate()
//	{
//
//		theSun = ExtendedPrimitives.createEllipsoid(new SimpleVector(36.21f,36.21f,36.21f),  128, 1f, 1f);
//
//		theSun.setTexture("textureless");
//		theSun.setTransparency(3);
//		theSun.setCulling(true);
//
//	}


	public void sunBodyFlatCreate()
	{


		theSun = com.threed.jpct.util.ExtendedPrimitives.createSprite(56.21f, 56.21f);



		TextureInfo textures = new TextureInfo(TextureManager.getInstance().getTextureID("gloworb"));
		textures.add(TextureManager.getInstance().getTextureID("diamondglyph"), TextureInfo.MODE_BLEND);
		theSun.setTexture(textures);


		theSun.setTexture(textures);
		theSun.setTransparency(3);
		theSun.setCulling(true);

	}


	public void planetbodycreate(Resources res){

		TextureManager tm = TextureManager.getInstance();

		Texture auroaMap = new Texture(res.openRawResource(R.raw.auroramap),true);
		//Texture ring = new Texture(res.openRawResource(R.raw.planet_ring),true);

		float plsize = 8.01f;//6.1
		float plasize = plsize*1.011f;//6.1
		planetSurface = ExtendedPrimitives.createEllipsoid(new SimpleVector(plsize,plsize,plsize), 128, 1f, 1f); 
		cloudSphere =   ExtendedPrimitives.createEllipsoid(new SimpleVector(plasize,plasize,plasize),  128, 1f, 1f);
	//	planetary_ring = Primitives.getPlane(4, 8);
		auroraSphere  =   ExtendedPrimitives.createEllipsoid(new SimpleVector(8.21f,8.21f,8.21f),  256, 1f, 1f);


		tm.addTexture("auroa", auroaMap);
	    //tm.addTexture("ring", ring);

		//TextureInfo ti = new TextureInfo(TextureManager.getInstance().getTextureID("night_specular"));//use to be "face"

		//TextureInfo cloud_ti = new TextureInfo(TextureManager.getInstance().getTextureID("cloud_face"));
		//cloud_ti.add(TextureManager.getInstance().getTextureID("cloud_normals"), TextureInfo.MODE_BLEND);
		
		//TextureInfo ring_ti = new TextureInfo(TextureManager.getInstance().getTextureID("ring"));


		//planetSurface.setTransparency(3);
		//planetSurface.setCulling(false);

		//planetSurface.setTexture(ti);
		//cloudSphere.setTexture(cloud_ti);
		cloudSphere.setTransparency(3);
		cloudSphere.setCulling(true);
		
	//	planetary_ring.setTexture(ring_ti);
	//	planetary_ring.setTransparency(3);

		auroraSphere.setTexture("auroa");
		auroraSphere.setTransparency(3);
		auroraSphere.setCulling(true);

		//planetSurface.setTransparency(3);
		planetSurface.setSpecularLighting(false);
		planetSurface.setCulling(true);



	}

	public void atmospherecreate(Resources res){
		float tt = 8.1f*1.025f;//1.025f;
		atmosphere_scatter = ExtendedPrimitives.createEllipsoid(new SimpleVector(tt,tt,tt), 128, 1f, 1f);
		atmosphere_scatter.invert();
		atmosphere_scatter.setTransparency(3);

        atmosphere_fog = ExtendedPrimitives.createEllipsoid(new SimpleVector(8.16f,8.16f,8.16f),  128, 1f, 1f);
		//atmosphere_fog.invert();
		atmosphere_fog.setTransparency(3);
    }
	

	
	public void shieldbodycreate(Resources res){
		


		shieldBody = ExtendedPrimitives.createEllipsoid(new SimpleVector(7,7,7), 128, 1f, 1f); 

		shieldBody.setTransparency(3);


	}
	

	public void skyspherecreate(Resources res){
	//	Texture celestial_face = new Texture(res.openRawResource(R.raw.skymap_test));
	//	celestial_face.setTextureCompression(true);

		cellestialSphere = ExtendedPrimitives.createEllipsoid(new SimpleVector(1610,1610,1610), 32, 1f, 1f);
		//TextureManager tm = TextureManager.getInstance();
		//tm.addTexture("cellestial_sphere", celestial_face);
		

	//	TextureInfo cellestial_ti = new TextureInfo(TextureManager.getInstance().getTextureID("cellestial_sphere"));

	//	cellestialSphere.setTexture(cellestial_ti);


		cellestialSphere.invert();
	

		
		cellestialSphere.build();
		//cellestialSphere.strip();
	}


//    public void wormholecreate(Resources res){
//        Texture wormhole_face = new Texture(res.openRawResource(R.raw.skymap_test));//swap this for a different starmap later
//        wormhole_face.setTextureCompression(true);
//        wormhole = ExtendedPrimitives.createEllipsoid(new SimpleVector(7,7,7), 32, 1f, 1f);
//        TextureManager tm = TextureManager.getInstance();
//        tm.addTexture("wormhole_sphere", wormhole_face);
//
//        TextureInfo cellestial_ti = new TextureInfo(TextureManager.getInstance().getTextureID("wormhole_sphere"));
//
//        wormhole.setTexture(cellestial_ti);
//        wormhole.invert();
//        wormhole.build();
//    //    wormhole.strip();
//    }


	
	
	
	public void spaceExplosionCreate(Resources res){
		Texture exp_face = new Texture(res.openRawResource(R.raw.textureless));

		explosionSpace = ExtendedPrimitives.createEllipsoid(new SimpleVector(1,1,1), 32, 1f, 1f);
		TextureManager tm = TextureManager.getInstance();
		tm.addTexture("explosion_space_sphere", exp_face);
		

		TextureInfo exp_ti = new TextureInfo(TextureManager.getInstance().getTextureID("explosion_space_sphere"));

		explosionSpace.setTexture(exp_ti);
		explosionSpace.setTransparency(3);
		explosionSpace.build();

	}

}
	
	


