package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.shader.Main;

import Entity_types.Wormhole;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 19/02/2015.
 */
public class WormholeRenderHook implements IRenderHook {



    Wormhole parent;
    GLSLShader shader;



    public WormholeRenderHook(Wormhole p, GLSLShader shader)
    {

        parent=p;
        this.shader=shader;

    }

    @Override
    public void beforeRendering(int i) {
        shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
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
