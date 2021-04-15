package Events.SetupEvents

import Events.Event
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.shader.Main


class AddMoneyEvent(amount_to_add: Int): Event() {

    var amount: Int;

    init {
        amount = amount_to_add
    }

    override fun Event()
    {
        SingletonObjects.player_money.addMoney(amount)
        setDone()
    }
}


