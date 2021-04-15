package Events.CameraEvents

import Entity_types.BaseEntitys.Entity
import Events.TimedDisplayEvent
import baseinterfacesclasses.SingletonObjects


//not camera can accept a null event, this is to allow pointing on an object that has not yet been created.
class CameraFollowEnt (entitytoFollow: Entity?, waitingtime: Int): TimedDisplayEvent(waitingtime){


    var ent = entitytoFollow





     override fun initialevent() {
    //     if (ent != null) {
             SingletonObjects.cameraCursor.setFollow(ent)
     //    }
     }

     override fun midevent() {
         //SingletonObjects.cameraCursor.setFollow(ent)
     }

     override fun closingevent() {
        // SingletonObjects.cameraCursor.setFollow(SingletonObjects.)
     }






}