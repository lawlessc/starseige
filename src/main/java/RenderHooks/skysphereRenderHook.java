package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.shader.Main;

import Entity_types.SkySphere;
import baseinterfacesclasses.SingletonObjects;

public class skysphereRenderHook  implements IRenderHook {

	SkySphere parent;
	GLSLShader skyshader;

	public skysphereRenderHook(SkySphere skySphere, GLSLShader shader) {
		
		
		this.parent= skySphere;
		this.skyshader=shader;

		// TODO Auto-generated constructor stub
	}


	@Override
	public void beforeRendering(int arg0) {
			skyshader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
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
