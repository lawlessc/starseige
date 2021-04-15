package SpecifiedButtons

import MenuObjects.Alignment
import MenuObjects.UIButton
import MenuObjects.UIToggleButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main

class PauseButton (offsetright: Float, offsetup: Float, alignment: Int, state: Boolean, scale: Float, collisionbox: Float) : UIToggleButton() {


    init {
        val a1 = SimpleVector(1f, 1f, 1f)
        val a2 = SimpleVector(0.5, 1.0, 0.5)

        SingletonObjects.hudFactory.createGlyphToggleButton(this, a1, a2,"playbutton", "pausebutton", offsetright, offsetup,
              !state, alignment, scale, collisionbox)

    }

    override fun actionSingleTapFunction(points: RayPickPoints) {
        SingletonObjects.isActionPaused = !SingletonObjects.isActionPaused;
        setToggle(SingletonObjects.isActionPaused)
    }

}