package menusViews;


import com.threed.jpct.SimpleVector;


import MenuObjects.Alignment;
import MenuObjects.UIButton;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 09/08/2015.
 */
public class
MainMenu {//extends StandardMenu {


    UIButton button1 = new NewGameButton();
    UIButton button2 = new LoadGameButton();
    UIButton button3 = new QuitGameButton();


    public MainMenu()

    {

        SingletonObjects.hudFactory.createGlyphButton(button1,
                new SimpleVector(1, 1, 1),new SimpleVector(0.5, 1, 0.5), "newgame", 0.0f, 0.0f,Alignment.Centre,0.9f,0.5f);

        SingletonObjects.hudFactory.createGlyphButton(button2,
                new SimpleVector(0, 1, 0),new SimpleVector(0, 1, 0), "loadgame", 0.5f, 0.8f,Alignment.TopLeft,0.9f,0.5f);

        SingletonObjects.hudFactory.createGlyphButton(button3,
                new SimpleVector(1, 0, 0), new SimpleVector(1, 0, 0), "quitgame", 0.5f, -0.8f, Alignment.BottomLeft, 0.9f ,0.5f);


    }

   // @Override
    public void closeMenu() {

        SingletonObjects.menumanager.addToRemovalList(button1);
        SingletonObjects.menumanager.addToRemovalList(button2);
        SingletonObjects.menumanager.addToRemovalList(button3);
    }


    public class NewGameButton extends UIButton
    {


        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {

            //might be a race condition here that i need to deal with..
            closeMenu();
            SingletonObjects.gameeventmanager.MainLevel();


        }

    }




    public class LoadGameButton extends UIButton
    {


        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {


        }

    }

    public class SettingsButton extends UIButton
    {


        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {

        }
    }


    public class QuitGameButton extends UIButton
    {

        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }


        @Override
        public  void actionDoubleTapFunction(RayPickPoints points) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }








}









