package menusViews

import MenuObjects.UIButton
import com.threed.jpct.shader.Main
import java.util.*

abstract class BaseMenu {
    

     abstract fun closeMenu() //Dev should make sure to destroy all buttons and ui objects here;

//
//     var buttons = Vector<UIButton>()
//
//     init {
//          Main.allGameObjects.INSTANCE.menumanager.setCurrentMenu(this)
//
//
//     }
//
//     fun closeMenu() {
//          for (i in buttons.indices) {
//               Main.allGameObjects.INSTANCE.menumanager.addToRemovalList(buttons[i])
//          }
//          Main.allGameObjects.INSTANCE.menumanager.currentMenu = null
//     }

     abstract fun update();// required for certain cases where ui items might need to change.


}
