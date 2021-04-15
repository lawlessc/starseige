package MenuObjects;

import com.threed.jpct.Camera;
import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

import baseinterfacesclasses.SingletonObjects;


public class SpatialGlyph implements IRenderHook  {

	public Object3D plane;
	public UIObject parent;

	public World  world;

	GLSLShader shader;
	
	float offsetUp;
	float offsetRight;
	float scale;


	
	//these are to keep the object attached and rotating with the camera
	Camera    camera;// if connected to a camera
	//Object3D subject;

	SimpleVector neworg;

	
	SpatialGlyph(UIObject obj,String glyphname ,GLSLShader shader ,float scale)
	{
		this.parent= obj;
		this.shader=shader;
		this.scale= scale;

		plane = Primitives.getPlane(1, this.scale);
		plane.setOrigin(new SimpleVector(0,0,0));
        plane.setTexture(glyphname);
        plane.setRenderHook(this);
      	plane.setShader(shader);
      	plane.setTransparency(3);
      	plane.setBillboarding(true);

	}



	SpatialGlyph(UIObject obj,GLSLShader shader ,float scale , Object3D object3D)
	{
		this.parent= obj;
		this.shader=shader;
		this.scale= scale;
		plane = object3D;
		plane.setOrigin(new SimpleVector(0,0,0));
		plane.setTexture("textureless");
		plane.setRenderHook(this);
		plane.setShader(shader);
		plane.setTransparency(3);
	}





	public void setBilloboard(Boolean xy)
	{
		plane.setBillboarding(xy);
	}
	

	//set a spatial glyph to be attached to a camera, and a distance in front of it.
	public void setAttachmentCamera(Camera cam , float offsetSide, float offsetUp)
	{
	camera = cam;
	this.offsetRight=offsetSide;
	this.offsetUp=offsetUp;;
		parent.attached = null;
	}
	
	

	public void setAttachmentObject(Camera cam, UIAttachable obj, float offsetSide, float offsetUp)
	{
	camera = cam;
	parent.attached = obj;
	this.offsetRight=offsetSide;
	this.offsetUp=offsetUp;
	}

	public void setAsFree()
	{

		parent.attached = null;
	}


	
	public void update() {



			switch (parent.alligned) {

				case Alignment.TopLeft:
					neworg = SingletonObjects.menumanager.getTopLeft();
					setAllignedCam();
					break;
				case Alignment.TopRight:
					neworg = SingletonObjects.menumanager.getTopRight();
					setAllignedCam();
					break;
				case Alignment.BottomLeft:
					neworg = SingletonObjects.menumanager.getBottomLeft();
					setAllignedCam();
					break;
				case Alignment.BottomRight:
					neworg = SingletonObjects.menumanager.getBottomRight();
					setAllignedCam();
					break;
				case Alignment.Centre:
					neworg = SingletonObjects.menumanager.getCenter();
					setAllignedCam();
					break;
				case Alignment.AttachedToButtonOnscreen:
					neworg = parent.attached.objectPosition();
					setAllignedCam();
					break;
				case Alignment.AttachedToButtonFree:
					neworg = parent.attached.objectPosition();
					setAllignedObject();
					break;

				case Alignment.AttachedToEntity:
					neworg =parent.attached.objectPosition();
					setAllignedObject();
					break;


				case Alignment.Free://Do nothing lol
					//plane.setOrigin(parent.position);
					plane.clearTranslation();
					plane.translate(parent.position);

					break;
			}

	}


	 public void setAllignedCam()
	{
		SimpleVector side = new SimpleVector(camera.getSideVector());


		side.scalarMul(offsetRight* SingletonObjects.menumanager.screenWidth);

		SimpleVector up = new SimpleVector(camera.getUpVector());
		up.scalarMul((-(offsetUp* SingletonObjects.menumanager.screenHeight))   );

		neworg.add(side);
		neworg.add(up);
		plane.clearTranslation();
		plane.translate(neworg);

	}

	public void setAllignedObject()
	{

		//neworg = attached.objectPosition();

		SimpleVector side = new SimpleVector(camera.getSideVector());
		side.scalarMul(offsetRight);

		SimpleVector up = new SimpleVector(camera.getUpVector());
		up.scalarMul(offsetUp);

		neworg.add(side);
		neworg.add(up);

		plane.clearTranslation();
		plane.translate(neworg);
	}
	
	
	
	



	@Override
	public void beforeRendering(int arg0) {
	shader.setUniform("a_colour",parent.basecolour);
	shader.setUniform("glow",parent.glowState);
	//shader.setUniform("GlowColor",parent.basecolour);
	shader.setUniform("thickness",parent.thickness);
	shader.setUniform("transparency",parent.transparency);
	shader.setUniform("threed",parent.isThreeD);

	shader.setUniform("nightmode",SingletonObjects.processHandler.nightmode ? 1 : 0);


		shader.setUniform("SmoothCenter",parent.smoothCenter);
		shader.setUniform("GlowBoundary",parent.glowdistance);



	//	setBilloboard(//transparency);
	// TODO Auto-generated method stub

	}
	


	@Override
	public void afterRendering(int arg0) {

		// TODO Auto-generated method stub
		
	}







	@Override
	public void onDispose() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean repeatRendering() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void setCurrentObject3D(Object3D arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setCurrentShader(GLSLShader arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setTransparency(float arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addToWorld(World world)
	{
		this.world=world;
		world.addObject(plane);
	}


	public void removeFromWorld()
	{

		try {
		world.removeObject(plane);
		}catch (Exception e)
		{

		}
	}

	
	
	
	

}
