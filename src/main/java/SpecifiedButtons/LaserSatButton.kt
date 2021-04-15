package SpecifiedButtons

import Entity_properties.Payload
import MenuObjects.UIButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import menusViews.OrbitalSelectionMenu




class LaserSatButton(offsetright: Float, offsetup: Float, alignment: Int, scale: Float, collisionbox: Float) : UIButton() {

    init {
        val a1 = SimpleVector(0.5, 1.0, 0.5)
        val a2 = SimpleVector(0.1, 1.0, 0.1)
        SingletonObjects.hudFactory.createGlyphButton(this, a1, a2, "laser_button", offsetright, offsetup, alignment, scale, collisionbox)
    }


    override fun actionSingleTapFunction(points: RayPickPoints) {
        val menu = OrbitalSelectionMenu(Payload.LaserSatellite, SingletonObjects.main_planet)
    }
}
