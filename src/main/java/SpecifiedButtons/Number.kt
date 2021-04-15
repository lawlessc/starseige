package SpecifiedButtons

import MenuObjects.Alignment
import MenuObjects.UINumberToggle
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import com.threed.jpct.shader.Main

 open class Number(offsetright: Float, offsetup: Float, alignment: Int , scale: Float) : UINumberToggle() {

     init {

       val colour1=  SimpleVector(1f, 0f, 0f)
       val colour2=  SimpleVector(1f, 1f, 1f)

       SingletonObjects.hudFactory.createNumberGlyph(this,colour1,colour2,
                 "zero", "one",
                 "two", "three",
                 "four", "five",
                 "six", "seven",
                 "eight", "nine",
                 offsetright, offsetup,
                 alignment, scale)
     }



//    override fun update() {
//        super.update()

//        if (SingletonObjects.player_money.digits.size > 0) {
//            val digit = Integer.parseInt(SingletonObjects.player_money.digits[SingletonObjects.player_money.digits.size - 1] + "")
//            this.setNumberVisibilty(digit)
//        } else {
//            this.setNumberVisibilty(0)
//        }


 //   }


}