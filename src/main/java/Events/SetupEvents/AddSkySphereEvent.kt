package Events.SetupEvents


import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main



//This is more for testing than anything
class AddSkySphereEvent(): Event() {


    override fun Event() {

        SingletonObjects.skysphere = SingletonObjects.object_factory.createSkySphere()

        setDone()

    }




}