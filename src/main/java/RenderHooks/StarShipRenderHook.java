package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;

import Entity_types.BaseEntitys.StarShipBasic;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 14/05/2016.
 */
public class StarShipRenderHook implements IRenderHook {


    StarShipBasic parent;
    GLSLShader shader;



    public StarShipRenderHook(StarShipBasic parent , GLSLShader shader)
    {
        this.parent=parent;
        this.shader = shader;

    }


    @Override
    public void beforeRendering(int i) {



        shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
        shader.setUniform("rendermodex", SingletonObjects.processHandler.RenderMode);


        shader.setUniform("GlowColor",parent.glowColour);

        shader.setUniform("a_colour",parent.colour);

        if(SingletonObjects.processHandler.RenderMode == 1) {
            shader.setUniform("thickness", 2.0f);
        }
        else
        {
            shader.setUniform("thickness", 0.0f);
        }
        shader.setUniform("transparency",1.0f);
        shader.setUniform("threed",0);
        //	setBilloboard(//transparency);

        
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
