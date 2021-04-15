package Events

import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates

class AlienLaserShipEvent: Event() {



    override fun Event() {

        val ent=  SingletonObjects.object_factory.createAlienLaserGunship(SingletonObjects.object_factory.randomPointAtdistance(40.0f),
                SimpleVector(0.0,0.0,0.0) , EntityStates.spaceShipWander)


        ent.Target_Entity= SingletonObjects.main_planet;

        setDone()
    }

}