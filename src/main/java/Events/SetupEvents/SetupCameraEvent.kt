package Events.SetupEvents

import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.Camera
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main



//This is more for testing than anything
class SetupCameraEvent(): Event() {


    override fun Event() {

        SingletonObjects.cam.moveCamera(Camera.CAMERA_MOVEOUT, 7f)
        SingletonObjects.cam.lookAt(SimpleVector(0f, 0f, 0f))

        setDone()

    }


}