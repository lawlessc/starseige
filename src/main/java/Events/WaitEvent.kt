package Events


class WaitEvent(waitingtime: Int): Event() {

    var waitime: Int;

    init {
        waitime = waitingtime
    }

    override fun Event()
    {
        if(waitime >= 0)
        {
           waitime--
        }
        else
        {
            setDone()
        }
    }
}


