package Events.SetupEvents

import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main

//This is more for testing than anything
class SetAmbientLightEvent(): Event() {


    override fun Event() {

        SingletonObjects.world.setAmbientLight(0, 0, 0)

        setDone()

    }


}