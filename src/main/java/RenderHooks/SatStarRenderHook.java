package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_properties.Allegiance;
import Entity_types.BaseEntitys.SatBasic;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 14/05/2016.
 */
public class SatStarRenderHook implements IRenderHook {


    SatBasic parent;
    GLSLShader shader;



    public SatStarRenderHook(SatBasic parent , GLSLShader shader)
    {
        this.parent=parent;
        this.shader = shader;

    }


    @Override
    public void beforeRendering(int i) {



        shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
        shader.setUniform("rendermodex", SingletonObjects.processHandler.RenderMode);


       if(!SingletonObjects.processHandler.nightmode)
       {//Regular colour mode
           shader.setUniform("GlowColor", parent.glowColour);
           shader.setUniform("a_colour", parent.colour);
       }
       else
       {
           if(parent.allegiance == Allegiance.Player)
           {
               //  shader.setUniform("friendly", 0);//Go green
               shader.setUniform("GlowColor",new SimpleVector(0.2 ,1.0, 0.0));
               shader.setUniform("a_colour",new SimpleVector(0.2 ,1.0, 0.0));
           }
           else
           {
               shader.setUniform("GlowColor",new SimpleVector(1.0 ,0.0, 0.0));
               shader.setUniform("a_colour",new SimpleVector(1.0 ,0.0, 0.0));
           }
       }

        if(SingletonObjects.processHandler.RenderMode == 1) {
            shader.setUniform("thickness", 2.0f);
        }
        else
        {
            shader.setUniform("thickness", 0.0f);
        }

        shader.setUniform("transparency",1.0f);
        shader.setUniform("threed",0);
        
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
