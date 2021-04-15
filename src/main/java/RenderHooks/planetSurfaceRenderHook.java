package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import Entity_types.Planet;
import baseinterfacesclasses.SingletonObjects;

public class PlanetSurfaceRenderHook implements IRenderHook {
	Planet parent;
	GLSLShader surfaceshader;

	public PlanetSurfaceRenderHook(Planet p, GLSLShader surfaceshader)
	{
		parent=p;
		this.surfaceshader=surfaceshader;
	}

	@Override
	public void beforeRendering(int arg0) {
		surfaceshader.setUniform("heightScale", 0.003f);

		surfaceshader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);


		float point=SingletonObjects.processHandler.startup_point;

		if(!SingletonObjects.processHandler.nightmode)
		{
		 point=-7.0f;
		}
		surfaceshader.setUniform("nightmode_point", point);
		// myBoolean ? 1 : 0;

		surfaceshader.setUniform("nightmode_started", SingletonObjects.processHandler.nightmode_startup ? 1: 0);


		surfaceshader.setUniform("u_time", (parent.planet_key));
		//surfaceshader.setUniform("u_time", (SingletonObjects.runningTime*0.0001f));

		surfaceshader.setUniform("uAmbient",new SimpleVector(0,0,0));
		surfaceshader.setUniform("uDiffuse",new SimpleVector(1,1,1));
		surfaceshader.setUniform("uSpecular",new SimpleVector(1,1,0.7));
		surfaceshader.setUniform("uSpecIntensity",0.002f);
		surfaceshader.setUniform("uTransparency",0.0f);
		SimpleVector campos= SingletonObjects.cam.getPosition();
		surfaceshader.setUniform("cityLightsColour",new SimpleVector(1.5,1.2,0.6));

		surfaceshader.setUniform("uEyePos",campos);


		parent.body.setTransparency(-1);
		parent.body.setCulling(true);

			if(SingletonObjects.processHandler.nightmode_startup)
		{
		parent.body.setTransparency(3);
		parent.body.setCulling(false);
		}
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
