package Entity_types;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.shader.Main;

import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 11/05/2016.
 */
public class FireWorkExp  {

    public FireWorkExp(SimpleVector pos)
    {
        SingletonObjects.particleEvents.fireWorkParticles(pos);
        SingletonObjects.soundManagement.playExplosion();
    }




}
