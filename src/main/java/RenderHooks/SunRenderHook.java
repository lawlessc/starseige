package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.shader.Main;

import java.io.SyncFailedException;

import Entity_types.Sun;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 06/10/2015.
 */
public class SunRenderHook implements IRenderHook {

    Sun parent;
    GLSLShader shader;

    public SunRenderHook(Sun sun, GLSLShader shader) {

        this.parent=sun;
        this.shader=shader;
        // TODO Auto-generated constructor stub
    }


    @Override
    public void beforeRendering(int i) {
        if(shader != null) {

            shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
          //  System.out.println("Sun RENDERMODE"+ SingletonObjects.processHandler.RenderMode);
        }
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

      //  if(renderIndex ==3) {
//
            return false;
      //  }
    }
}
