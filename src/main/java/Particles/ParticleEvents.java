package Particles;

import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 29/08/2015.
 */
public final class ParticleEvents {


    ParticleManager manager;

    public ParticleEvents(ParticleManager manager)
    {
     this.manager = manager;
    }




    public PartEmitter rocketThruster(Entity parent,SimpleVector attachmentPoint)
    {

        PartEmitter emitter;

        emitter = new PartEmitter(parent,attachmentPoint, new SimpleVector(0,0,0),0.3f,0.3f ,
                "gloworb",25,0.00025f,0.03f,0.02f, true,0.6f);

        emitter.glows(false);
        emitter.setRandomDirections(true);
        emitter.setGlowColour(new SimpleVector(1f,1f,1f));
        emitter.setColour(new SimpleVector(1f,1f,1f));

        SingletonObjects.particleManager.Add(emitter);


     return  emitter;
    }



    public PartEmitter  expSparks(SimpleVector position, SimpleVector direction)
    {

        PartEmitter emitter;

        emitter = new PartEmitter(new SimpleVector(position), new SimpleVector(0,0,0),0.1f,0.1f ,
                "gloworb",6,0,0.3f,0.01f, false,0.5f);
        emitter.glows(true);
        emitter.setRandomDirections(true);

        emitter.setGlowColour(new SimpleVector(1f,1f,0.1f));
        emitter.setColour(new SimpleVector(1f,1f,1f));
        SingletonObjects.particleManager.Add(emitter);
        return emitter;
    }



    public  PartEmitter redLaserSparks(SimpleVector position, SimpleVector direction)
    {

        PartEmitter emitter;

        emitter = new PartEmitter(new SimpleVector(position), new SimpleVector(0,0,0),0.1f,0.1f ,
                "gloworb",4,0,0.4f,0.01f, true,0.5f);
        emitter.glows(true);
        emitter.setRandomDirections(true);
        emitter.repeat=true;
        emitter.isOn=false;//this is created before it is used
        emitter.setGlowColour(new SimpleVector(1f,0.5f,0.5f));
        emitter.setColour(new SimpleVector(1.0f,0.1f,0.2f));
        SingletonObjects.particleManager.Add(emitter);
        return emitter;
    }




    public void fireWorkParticles(SimpleVector position)
    {

        PartEmitter emitter;

        emitter = new PartEmitter(new SimpleVector(position), new SimpleVector(0,0,0),0.6f,0.6f ,"gloworb",10,0,4f,0.01f, false,0.5f);
        emitter.glows(true);
        emitter.setRandomDirections(true);
        emitter.setRandomColours(true);


        SingletonObjects.particleManager.Add(emitter);


    }

    public void dustParticles(SimpleVector position)
    {

        PartEmitter emitter;

        emitter = new PartEmitter(new SimpleVector(position), new SimpleVector(0,0,0),0.7f,0.7f ,"gloworb",4,0,4f,0.1f, false,0.5f);
        emitter.glows(false);
      //  emitter.setRandomDirections(true);
     //   emitter.setGlowColour(new SimpleVector(1f,0.5f,0.1f));
        emitter.setColour(new SimpleVector(0.502,0.502,0.502));
        SingletonObjects.particleManager.Add(emitter);


    }


    public void wormHoleParticles(SimpleVector position)
    {


        PartEmitter emitter;

        emitter = new PartEmitter(new SimpleVector(position), new SimpleVector(0,0,0),0.2f,0.2f ,"gloworb",5,200,4f,0.02f, true,0.5f);
        emitter.glows(true);
        emitter.setRandomDirections(true);
        emitter.setGlowColour(new SimpleVector(0.580, 0.100, 0.827));
        emitter.setColour(new SimpleVector(	0.580, 0.000, 0.827));
        SingletonObjects.particleManager.Add(emitter);


    }

    public void PlanetLightningParticles(SimpleVector position)
    {

    }


    public void plasmaExplosionParticles(SimpleVector position)
    {

    }

    public void sheildParticles(SimpleVector position)
    {

    }


    public void debris(SimpleVector position, SimpleVector direction)
    {
        PartEmitter emitter;
        emitter = new PartEmitter(new SimpleVector(position), new SimpleVector(0,0,0),0.5f,0.5f ,"chunk1",2,0,4f,1, false,0.5f);

        SingletonObjects.particleManager.Add(emitter);
    }


    public void shipExplosionParticles(SimpleVector position)
    {//could use multiple emmiters here of object3d's of ship pieces.

    }





    public void shipTrailParticles(SimpleVector position, Entity parent)
    {

    }

    public void satelliteDeployParticles(SimpleVector position) //this could consist of rocket parts and sections that quickly float away
    {

    }





}
