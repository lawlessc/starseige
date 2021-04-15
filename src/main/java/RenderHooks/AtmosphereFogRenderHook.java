package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;


import Entity_types.Planet;
import baseinterfacesclasses.SingletonObjects;

public class AtmosphereFogRenderHook implements IRenderHook {


	Planet parent;
	GLSLShader atmosphereFogShader = null;
	SimpleVector colour;
	String atmosphereString= "atmosphereColour";

	public AtmosphereFogRenderHook(Planet p, GLSLShader atmosphereshader)
	{//40.8, 46.3, 50.6
		colour= new SimpleVector(0.408,0.463,0.506);
		parent=p;
		this.atmosphereFogShader = atmosphereshader;
	}
	


	@Override
	public void beforeRendering(int arg0) {


        atmosphereFogShader.setUniform(atmosphereString,colour);
		atmosphereFogShader.setUniform("rendermodex", SingletonObjects.processHandler.RenderMode);

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

}
