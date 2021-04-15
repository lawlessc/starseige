package Events

import MenuObjects.Alignment
import MenuObjects.UIButton
import SpecifiedButtons.Number
import SpecifiedButtons.SkullGlyph
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main
import java.util.*

class SkullEvent(waitingtime: Int): TimedDisplayEvent(waitingtime)
{


    lateinit var skullbut : SkullGlyph


    override fun initialevent() {

     val red = SimpleVector(1.0, 0.0, 0.0)
     skullbut = SkullGlyph(0.0f,0.0f,Alignment.Centre,0.8f,0.01f)
     //Main.allGameObjects.INSTANCE.hudFactory.createGlyphButton(skullbut, red, red, "skullglyph",0.0f, 0.0f, Alignment.Centre, 0.8f, 0.0f)
     skullbut.disabled=true

    }



    override fun midevent() {
        //Do nothing here

    }


    inner class UIDigit(offsetright: Float, offsetup: Float, alignment: Int, scale: Float) : Number(offsetright, offsetup, alignment, scale) {

//        override fun update() {
//            super.update()
//
////            if (Main.allGameObjects.INSTANCE.player_money.digits.size > 0) {
////                val digit = Integer.parseInt(Main.allGameObjects.INSTANCE.player_money.digits[Main.allGameObjects.INSTANCE.player_money.digits.size - 1] + "")
////                this.setNumberVisibilty(digit)
////            } else {
////                this.setNumberVisibilty(0)
////            }


        //       }


    }


    override fun closingevent() {
        SingletonObjects.menumanager.addToRemovalList(skullbut)
    }



}
