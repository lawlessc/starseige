package SpecifiedButtons

import Entity_types.BaseEntitys.Entity
import MenuObjects.UIButton
import baseinterfacesclasses.RayPickPoints
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates
import menusViews.BaseMenu

class SelfDestructButton (entity: Entity?, baseMenu: BaseMenu?, offsetright : Float, offsetup: Float, scale: Float, collisionbox : Float) : UIButton()
{
    var  ent = entity;
    var  menu = baseMenu;

    val a1 =  SimpleVector(1f, 0f, 0f)
    val a2 = SimpleVector(0.5, 1.0, 0.5)

    init {
        SingletonObjects.hudFactory.createGlyphButton(this,entity, a1, a2, "skullglyph" , offsetright, offsetup, scale, collisionbox)
    }


    override fun actionDoubleTapFunction(points: RayPickPoints) {
        ent?.Switch_State(EntityStates.selfDestruct_state)
        menu?.closeMenu()
    }

}


