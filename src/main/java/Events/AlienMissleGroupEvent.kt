package Events

import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates


//This is more for testing than anything
class AlienMissleGroupEvent(): Event() {

    override fun Event() {

        var mainpoint= SingletonObjects.object_factory.randomPointAtdistance(40.0f)

        var p1 = SingletonObjects.object_factory.randomPointAtdistanceNearPoint(4.0f,mainpoint)
        var p2 = SingletonObjects.object_factory.randomPointAtdistanceNearPoint(4.0f,mainpoint)
        var p3 = SingletonObjects.object_factory.randomPointAtdistanceNearPoint(4.0f,mainpoint)
        var p4 = SingletonObjects.object_factory.randomPointAtdistanceNearPoint(4.0f,mainpoint)

        SingletonObjects.object_factory.createAlienPlanetTorpedo(p1,
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

        SingletonObjects.object_factory.createAlienPlanetTorpedo(p2,
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

        SingletonObjects.object_factory.createAlienPlanetTorpedo(p3,
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

        SingletonObjects.object_factory.createAlienPlanetTorpedo(p4,
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)



        setDone()
    }
}