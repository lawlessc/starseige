package SpecifiedButtons

import MenuObjects.Alignment
import MenuObjects.UIButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main


class NightmodeButton(offsetright: Float, offsetup: Float, alignment: Int, scale: Float, collisionbox: Float) : UIButton() {

    init {
        val a1 = SimpleVector(0.5, 1.0, 0.5)
        val a2 = SimpleVector(0.1, 1.0, 0.1)
        SingletonObjects.hudFactory.createGlyphButton(this, a1, a2, "nightmode", offsetright, offsetup, alignment, scale, collisionbox)
    }

    override fun actionSingleTapFunction(points: RayPickPoints) {
//        SingletonObjects.processHandler.nightmode = !SingletonObjects.processHandler.nightmode
//        SingletonObjects.processHandler.nightmode_startup =SingletonObjects.processHandler.nightmode
//        SingletonObjects.processHandler.startup_point=5.9f;

        SingletonObjects.processHandler.switchnightmode()

    }

}
