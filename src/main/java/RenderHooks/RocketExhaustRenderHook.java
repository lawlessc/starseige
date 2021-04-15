package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.Beams.Beam;
import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 14/05/2016.
 */
public class RocketExhaustRenderHook implements IRenderHook {

    Entity parent;
    GLSLShader shader;
    SimpleVector center= new SimpleVector(0,0,0);
    SimpleVector camdirection;
    // float inter;

    public RocketExhaustRenderHook(Beam parent , GLSLShader shader)
    {
        this.parent=parent;
        this.shader = shader;
    }

    @Override
    public void beforeRendering(int i) {


        Update();


        shader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);
        shader.setUniform("rendermodex", SingletonObjects.processHandler.RenderMode);

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

    public void Update()
    {
       // camdirection = center.calcSub(parent.direction);


       // camdirection.scalarMul(intersect(SingletonObjects.cam.getPosition() ,camdirection, parent.direction,parent.position ));
        camdirection.add(SingletonObjects.cam.getPosition());
        camdirection.sub(parent.position);

       // parent.body.setOrientation(parent.direction,camdirection);
    }



    public float intersect(SimpleVector point,
                           SimpleVector direction,
                           SimpleVector planeNormal,
                           SimpleVector pointonplane) {

        float denom = planeNormal.calcDot(direction);

        if (Math.abs(denom) > 0.0001f) // your favorite epsilon
        {
            float t = (pointonplane.calcSub(point)).calcDot(planeNormal) / denom;
            if (t >= 0)
                return t; // you might want to allow an epsilon here too
        }
        return 0;
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
