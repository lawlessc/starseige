package Events.ComplexEvents

import Events.Event

abstract class ConditionalEvent : Event() {


    var notstarted = true

    init {
       // waitime = waitingtime
    }

    override fun Event()
    {

        if(notstarted)
        {
            initialEvent()
            notstarted=false;
        }

        if(branchCondition())
        {

                closingevent()
                setDone()
        }
        else
        {
            waitingEvent()
        }
    }


    abstract fun initialEvent()
    abstract fun waitingEvent()//The starting event for example adding a displayed word to the screen.
    abstract fun branchCondition(): Boolean
    abstract fun closingevent()

}