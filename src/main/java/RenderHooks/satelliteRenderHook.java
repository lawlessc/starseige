package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;

import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;

public class SatelliteRenderHook implements IRenderHook{
	
	
	
    Entity parent;
    GLSLShader satteliteshader;
	
	
	public SatelliteRenderHook(Entity p, GLSLShader satteliteshader)
	{
		
		parent=p;
		this.satteliteshader=satteliteshader;
		
	}

	@Override
	public void afterRendering(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRendering(int arg0) {
		if(satteliteshader != null)
		{
		satteliteshader.setUniform("heightScale", 0.05f);
		satteliteshader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);

		}
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
