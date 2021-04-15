package MenuObjects;

import com.threed.jpct.shader.Main;

import java.util.Vector;

import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 09/03/2015.
 * Holds a group of HUDObjects for specific menu
 * this is to make it easier to add and delete specific menus
 * while ignoring others.
 *
 *
 */
public abstract class UIMenuGroup {


    public Vector<UIObject> hudGroup = new Vector<UIObject>();


    //public void
     public void clearAll()
     {

         SingletonObjects.menumanager.removeHudGroup(this);

     }
}
