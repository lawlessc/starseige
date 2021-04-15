package SpecifiedButtons

import Entity_properties.Payload
import MenuObjects.UIButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import menusViews.OrbitalSelectionMenu


class MineButton(offsetright: Float, offsetup: Float, alignment: Int, scale: Float, collisionbox: Float) : UIButton() {

    init {
        val a1 = SimpleVector(1.0, 0.22, 0.2)
        val a2 = SimpleVector(1.0, 0.22, 0.2)
        SingletonObjects.hudFactory.createGlyphButton(this, a1, a2, "mineglyph", offsetright, offsetup, alignment, scale, collisionbox)

        this.smoothCenter=0.3f;///less is more
        this.glowdistance=1.3f;
    }


    override fun actionSingleTapFunction(points: RayPickPoints) {
        val menu = OrbitalSelectionMenu(Payload.Mine, SingletonObjects.main_planet)
    }
}

