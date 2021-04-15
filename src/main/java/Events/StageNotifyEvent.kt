package Events

import MenuObjects.Alignment
import SpecifiedButtons.Number
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main
import java.util.*


class StageNotifyEvent(waitingtime: Int): TimedDisplayEvent(waitingtime)
{



    lateinit var stagenum : UIDigit



    override fun initialevent() {
        //Create the sign here
        stagenum = UIDigit( 0.0f, 0.0f, Alignment.Centre, 0.6f);
        stagenum.setNumberVisibilty(1)
        stagenum.mainColour= SimpleVector(1.0f,1.0f,1.0f)
    }






    override fun midevent() {
       //Do nothing here

        stagenum.mainColour= SimpleVector(0.3f,1.0f,1.0f)
        stagenum.basecolour= SimpleVector(0.3f,1.0f,1.0f)

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
        SingletonObjects.menumanager.addToRemovalList(stagenum)

    }



}
