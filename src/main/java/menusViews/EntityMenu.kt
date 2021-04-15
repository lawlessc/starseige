package menusViews

import Entity_types.BaseEntitys.Entity
import MenuObjects.UIButton
import baseinterfacesclasses.SingletonObjects
import java.util.*


/**
 * Created by lawless on 25/01/2016.
 */
 abstract class EntityMenu constructor( var parentp: Entity?) : BaseMenu(), Observer {

    var parent  =parentp;
    var buttons = Vector<UIButton>()

    init {
        SingletonObjects.menumanager.setCurrentMenu(this)
        parent?.addObserver(this)

    }

    override  fun closeMenu() {
        for (i in buttons.indices) {
            SingletonObjects.menumanager.addToRemovalList(buttons[i])
        }
        SingletonObjects.menumanager.currentMenu = null
    }

    override fun update(observable: Observable?, data: Any?) {
        closeMenu()
    }

}
