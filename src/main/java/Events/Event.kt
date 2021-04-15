package Events

import java.util.*


abstract class Event()  {

    var eventcalled: Boolean =false

    fun doEvent():Boolean //we return a boolean
      {
          if(!eventcalled)
          {
              Event()
          }

          return eventcalled;
      }

    //it is up to the user to override this event. And only this event
    abstract fun Event()

    fun setDone()//Called when an event reaches it's end state.
    {
        eventcalled=true
    }

}
