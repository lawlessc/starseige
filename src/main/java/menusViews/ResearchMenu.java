package menusViews;

import com.threed.jpct.shader.Main;

import MenuObjects.UIButton;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 14/05/2018.
 *
 * This should display a small list of researchable items and abilities that cost energy for the player
 * examples
 * More advanced weapons
 * The ability to repair
 * new forms of blockers, e.g instead of mines have mini-blackholes that tear apart enemies.
 * new forms of energy generators.
 *
 *
 */

public class ResearchMenu {


    UIButton button1 = new Research_Item_one();
    UIButton button2 = new Research_Item_one();
    UIButton button3 = new Research_Item_one();
    UIButton button4 = new Research_Item_one();
    UIButton button5 = new Research_Item_one();
    UIButton button6 = new Research_Item_one();

    ResearchMenu()
    {




    }


    public class Research_Item_one extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }
    }

    public class Research_Item_two extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }
    }

    public class Research_Item_three extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }
    }

    public class Research_Item_four extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }


    }

    public class Research_Item_five extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }
    }

    public class Research_Item_six extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }
    }



    public void closeMenu() {

        SingletonObjects.menumanager.addToRemovalList(button1);
        SingletonObjects.menumanager.addToRemovalList(button2);
        SingletonObjects.menumanager.addToRemovalList(button3);
        SingletonObjects.menumanager.addToRemovalList(button4);
        SingletonObjects.menumanager.addToRemovalList(button5);
        SingletonObjects.menumanager.addToRemovalList(button6);
    }


}



