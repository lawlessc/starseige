package RenderHooks;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.IRenderHook;
import com.threed.jpct.Object3D;
import com.threed.jpct.shader.Main;

import Particles.PartEmitter;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 08/05/2016.
 */
public class ParticleRenderHook  implements IRenderHook  {

    PartEmitter.Particle parent;
    PartEmitter emitter;
    GLSLShader particleshader;

    public ParticleRenderHook(PartEmitter.Particle particle,PartEmitter emitter,GLSLShader shader)
    {
        parent=particle;
        particleshader=shader;
        this.emitter= emitter;
    }

    @Override
    public void beforeRendering(int i) {

        if(emitter.glows) {
            particleshader.setUniform("glows", 1);
        }
        else
        {
            particleshader.setUniform("glows",0);
        }


        particleshader.setUniform("rendermode", SingletonObjects.processHandler.RenderMode);


        particleshader.setUniform("colour",parent.baseParticleColour);
        particleshader.setUniform("GlowColor",parent.glowParticleColour);

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
