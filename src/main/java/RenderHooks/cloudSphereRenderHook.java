package RenderHooks;


import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;


import Entity_types.Planet;
import baseinterfacesclasses.SingletonObjects;

public class CloudSphereRenderHook implements IRenderHook {

	Planet parent;
	public GLSLShader cloudshader= null; 
	
	public CloudSphereRenderHook(Planet p, GLSLShader cloudshader)
	{
		parent=p;
		this.cloudshader= cloudshader;
	}
	
	
	@Override
	public void afterRendering(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRendering(int arg0) {
	
		cloudshader.setUniform("u_time", (float) Math.sin(SingletonObjects.runningTime*0.0001f));
		cloudshader.setUniform("heightScale", 0.03f);
		cloudshader.setUniform("uAmbient",new SimpleVector(0,0,0));
		cloudshader.setUniform("uDiffuse",new SimpleVector(1,1,1));
		cloudshader.setUniform("lightPositions",parent.sunpos);
		cloudshader.setUniform("rendermode",SingletonObjects.processHandler.RenderMode);

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

}
