package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;


import Entity_types.Planet;
import baseinterfacesclasses.SingletonObjects;

public class AtmosphereRenderHook implements IRenderHook {
	
	
	Planet parent;
	public GLSLShader atmosphereshader= null;



	public AtmosphereRenderHook(Planet p, GLSLShader atmosphereshader)
	{
		
		parent=p;
		this.atmosphereshader = atmosphereshader;
	}
	

	@Override
	public void afterRendering(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRendering(int arg0) {

		SimpleVector camadd = new SimpleVector(SingletonObjects.cam.getPosition());


		atmosphereshader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);

		atmosphereshader.setStaticUniform("v3CameraPos",camadd);


		//  SimpleVector light=  parent.sunpos;
		//  light =light.normalize();
		
		  
		//  atmosphereshader.setStaticUniform("v3LightPos", light);
		  float dist= camadd.distance(new SimpleVector(0,0,0));
		  
		  atmosphereshader.setStaticUniform("fCameraHeight", dist);
		  atmosphereshader.setStaticUniform("fCameraHeight2", dist*dist);
		  float innnerRad=4.05f;
		  atmosphereshader.setStaticUniform("fInnerRadius", innnerRad);
		  atmosphereshader.setStaticUniform("fInnerRadius2", (innnerRad*innnerRad));
		  float outerRad = innnerRad*1.025f;
		  atmosphereshader.setStaticUniform("fOuterRadius", outerRad);
		  atmosphereshader.setStaticUniform("fOuterRadius2", (outerRad*outerRad));
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
