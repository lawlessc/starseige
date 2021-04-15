package SpecifiedButtons

import MenuObjects.UIButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates


class AlienTestButton(offsetright: Float, offsetup: Float, alignment: Int, scale: Float, collisionbox: Float) : UIButton() {

    init {
        val a1 = SimpleVector(0.5, 1.0, 0.5)
        val a2 = SimpleVector(0.1, 1.0, 0.1)
        SingletonObjects.hudFactory.createGlyphButton(this, a1, a2, "nightmode", offsetright, offsetup, alignment, scale, collisionbox)
    }

    override fun actionSingleTapFunction(points: RayPickPoints) {


     val ent=   SingletonObjects.object_factory.createAlienPlanetTorpedo(SingletonObjects.object_factory.randomPointAtdistance(40.0f),
                 SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent);



    }

}

