package Events.CameraEvents

import Entity_types.EntityContainer
import Events.TimedDisplayEvent
import baseinterfacesclasses.SingletonObjects


//not camera can accept a null event, this is to allow pointing on an object that has not yet been created.
class CameraFollowEvent (entityContainer: EntityContainer? ,waitingtime: Int): TimedDisplayEvent(waitingtime){


    var entityContainer = entityContainer





     override fun initialevent() {
    //     if (ent != null) {
             SingletonObjects.cameraCursor.setFollow(entityContainer?.ent)
     //    }
     }

     override fun midevent() {
         //SingletonObjects.cameraCursor.setFollow(ent)
     }

     override fun closingevent() {
        // SingletonObjects.cameraCursor.setFollow(SingletonObjects.)
     }






}