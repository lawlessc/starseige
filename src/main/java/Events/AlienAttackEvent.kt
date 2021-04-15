package Events

import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates


class AlienAttackEvent(): Event() {



    /*This event will add multiple amounts of alien ships and waitpoints before more waves*/

    override fun Event() {

        SingletonObjects.object_factory.createAlienPlanetTorpedo(SingletonObjects.object_factory.randomPointAtdistance(40.0f),
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

        setDone()
    }
}