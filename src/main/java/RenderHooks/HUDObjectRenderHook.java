package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import MenuObjects.UIObject;

//This sphere will be used to describe a satellite orbit to the player
//It is primarily for setting an orbit.
public class HUDObjectRenderHook implements IRenderHook {
	
	
	
	public GLSLShader HUDObjectShader= null;
	public UIObject parent;
	
	
	public HUDObjectRenderHook(GLSLShader shader)
	{

		this.HUDObjectShader=shader;
	}

    public void setParent(UIObject parent)
    {
        this.parent=parent;
    }
	
	

	@Override
	public void afterRendering(int arg0) {

        HUDObjectShader.setUniform("a_colour",parent.basecolour);

        HUDObjectShader.setUniform("glow",1);
        HUDObjectShader.setUniform("GlowColor",new SimpleVector(1.0f,0,0));

		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRendering(int arg0) {
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
