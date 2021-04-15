package Events.ComplexEvents

import Events.*
import Events.CameraEvents.CameraFollowEvent
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main

class FirstStageEvent(): Event() {



    //var asteroideventa= AsteroidEvent();




    override fun Event() {



        SingletonObjects.eventCompleter. events.add(WaitEvent(200))//almost immediate
        SingletonObjects.eventCompleter. events.add(AsteroidEvent())
      //  SingletonObjects.eventCompleter. events.add(CameraFollowEvent(asteroideventa.asteroid_container,200))
       // SingletonObjects.eventCompleter. events.add(WaitEvent(200))//almost immediate
       // SingletonObjects.eventCompleter. events.add(WaitEvent(200))//almost immediate

      //  SingletonObjects.eventCompleter. events.add(SecondStageEvent())//almost immediate


        setDone()

    }




}