package SpecifiedButtons

import MenuObjects.Alignment
import MenuObjects.UIButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main


class SkullGlyph(offsetright : Float, offsetup: Float, alignment : Int, scale: Float, collisionbox : Float) : UIButton()
{

    init {
        val red = SimpleVector(1.0, 0.0, 0.0)
        SingletonObjects.hudFactory.createGlyphButton(this, red, red, "skullglyph",offsetright, offsetup, alignment, scale, collisionbox)

        disabled=true
    }


    override fun actionSingleTapFunction(points: RayPickPoints) {
        //open the research screen.
    }





}