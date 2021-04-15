package Events

import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates


//This is more for testing than anything
class AlienMissleEvent(): Event() {

    override fun Event() {

        SingletonObjects.object_factory.createAlienPlanetTorpedo(SingletonObjects.object_factory.randomPointAtdistance(40.0f),
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

            setDone()
    }
}