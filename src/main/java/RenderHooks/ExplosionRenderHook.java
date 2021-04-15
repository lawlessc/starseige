package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import Entity_types.ExplosionSpace;
import baseinterfacesclasses.SingletonObjects;


/**
 * Created by lawless on 27/01/2015.
 */
public class ExplosionRenderHook  implements IRenderHook {

    ExplosionSpace parent;
    public GLSLShader explosionshader= null;
    SimpleVector colour = new SimpleVector(1,0.7,0.5);

    public ExplosionRenderHook(ExplosionSpace p,GLSLShader cloudshader)
    {
        parent=p;
        this.explosionshader= cloudshader;
    }


    @Override
    public void afterRendering(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeRendering(int arg0) {

        //explosionshader.setStaticUniform("timeElapsed",1.0f);

        //might be able to scale the object 3d here in the shader and ignore doing anything in object itself
     //   explosionshader.setUniform("u_time", (float) (SiegeSingletonObjects.runningTime*0.0001f));
      //  explosionshader.setUniform("heightScale", 0.03f);
        //explosionshader.setUniform("lightPositions",parent.sunpos);
        explosionshader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);

        explosionshader.setUniform("explosionColour", colour);



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
