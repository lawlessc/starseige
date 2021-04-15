package Events



abstract class TimedDisplayEvent(waitingtime: Int): Event() {


    var waitime: Int
    var started = false

    init {
        waitime = waitingtime
    }

    override fun Event()
    {

        if(started) {
            if (waitime >= 0) {
                waitime--
                midevent()
            } else {
                closingevent()
                setDone()
            }
        }
        else
        {
            initialevent()
            started=true
        }
    }


    abstract fun initialevent()//The starting event for example adding a displayed word to the screen.

    abstract fun midevent()//This will probably be empty most or all of the time.

    abstract fun closingevent()

}


