package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import Entity_types.Planet;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 19/09/2015.
 */
public class AuroraShaderRenderHook   implements IRenderHook {



    Planet parent;
    GLSLShader AuroraShader = null;
    SimpleVector colour = new SimpleVector(0.0,1.0,0.4);



    public AuroraShaderRenderHook(Planet p, GLSLShader aurorashader)
    {

        parent=p;
        this.AuroraShader = aurorashader;
    }


    @Override
    public void beforeRendering(int i) {

        //AuroraShader.setUniform("u_time", (float) (SingletonObjects.runningTime*0.0001f));
       // AuroraShader.setUniform("u_time", (float) (SingletonObjects.runningTimeSeconds*0.01));
        AuroraShader.setUniform("u_time", (SingletonObjects.runningTime*0.001f));
        AuroraShader.setUniform("a_colour",colour);
        AuroraShader.setUniform("glow",1);
        AuroraShader.setUniform("GlowColor",colour);


        AuroraShader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);



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
