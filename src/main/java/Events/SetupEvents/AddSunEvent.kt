package Events.SetupEvents

import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main


//This is more for testing than anything
class AddSunEvent(): Event() {


    override fun Event() {

        SingletonObjects.processHandler.setSun(

                SingletonObjects.object_factory.createSun()

        )

        setDone()

    }




}