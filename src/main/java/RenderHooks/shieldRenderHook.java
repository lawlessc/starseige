package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.shader.Main;

import Entity_types.Shield;
import baseinterfacesclasses.SingletonObjects;

public class shieldRenderHook  implements IRenderHook {

	
	Shield parent;
	GLSLShader shader;
	
	
	public shieldRenderHook(Shield shield, GLSLShader shader) {
		
		this.parent=shield;
		this.shader=shader;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterRendering(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRendering(int arg0) {
		if(shader != null)
		{
			shader.setUniform("u_time", (float) (System.currentTimeMillis()*0.0001f));
			shader.setUniform("shieldcolour", parent.shieldcolour);
			shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
		}
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
