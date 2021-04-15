package Events.SetupEvents

import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main
import java.util.*


//This is more for testing than anything
class GameSetupEvent(): Event() {




    override fun Event()
    {

        SingletonObjects.eventCompleter.addEvent(AddSunEvent())
        SingletonObjects.eventCompleter.addEvent(AddPlanetEvent())
        SingletonObjects.eventCompleter.addEvent(AddSkySphereEvent())
      //SingletonObjects.eventCompleter.addEvent(SetAmbientLightEvent())//useless
        SingletonObjects.eventCompleter.addEvent(SetupCameraEvent())
        SingletonObjects.eventCompleter.addEvent(AddMoneyEvent(100))

        setDone()

    }


}