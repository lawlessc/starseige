package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.shader.Main;

import Lights.LightBulb;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 14/05/2016.
 */
public class LightBulbRenderHook implements IRenderHook {

    LightBulb parent;
    GLSLShader shader;

    public LightBulbRenderHook(  LightBulb parent ,  GLSLShader shader)
    {
        this.parent=parent;
        this.shader = shader;
    }

    @Override
    public void beforeRendering(int i) {

        shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
        shader.setUniform("colour",parent.colour);
        shader.setUniform("GlowColor",parent.glowColour);
        
    }

    @Override
    public void afterRendering(int i) {

    }

    @Override
    public void setCurrentObject3D(Object3D object3D) {

    }

    @Override
    public void setCurrentShader(GLSLShader glslShader) {

    }

    @Override
    public void setTransparency(float v) {

    }

    @Override
    public void onDispose() {

    }

    @Override
    public boolean repeatRendering() {
        return false;
    }
}
