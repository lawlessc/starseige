package Particles.ParticleBehaviours;

import Particles.PartEmitter;

/**
 * Created by Chris on 16/09/2016.
 * Encapsulates a particles behaviour over time
 */
public interface ParticleBehaviour {



    abstract void initializer();//write code here for how a particle creates itself


    abstract void update(PartEmitter.Particle particle);



}
