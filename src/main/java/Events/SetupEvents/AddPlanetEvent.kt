package Events.SetupEvents

import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main



//This is more for testing than anything
class AddPlanetEvent(): Event() {


    override fun Event() {

       SingletonObjects.main_planet =   SingletonObjects.object_factory.createPlanet()

        setDone()

    }




}