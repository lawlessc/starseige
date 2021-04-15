package Events.ComplexEvents

import Events.*
import baseinterfacesclasses.SingletonObjects

class SecondStageEvent(): Event() {



    override fun Event() {

        SingletonObjects.eventCompleter. events.add(WaitEvent(100))//almost immediate
        SingletonObjects.eventCompleter.  events.add(StageNotifyNumber(200,3))
//        SingletonObjects.eventCompleter. events.add(StageNotifyNumber(200,2))
//        SingletonObjects.eventCompleter. events.add(StageNotifyNumber(200,1))
//        SingletonObjects.eventCompleter. events.add(AlienMissleGroupEvent())
//        SingletonObjects.eventCompleter. events.add(AlienMissleGroupEvent())

        SingletonObjects.eventCompleter. events.add(AlienMissileShipEvent())

        SingletonObjects.eventCompleter. events.add(AlienLaserShipEvent())

        //SingletonObjects.eventCompleter. events.add(AlienMissleGroupEvent())

      //  SingletonObjects.eventCompleter.  events.add(SkullEvent(400))


        setDone()

    }


}