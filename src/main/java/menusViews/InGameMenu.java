package menusViews;

import MenuObjects.Alignment;
import MenuObjects.UIButton;
import MenuObjects.UIToggleButton;
import SpecifiedButtons.LaserSatButton;
import SpecifiedButtons.MineButton;
import SpecifiedButtons.NightmodeButton;
import SpecifiedButtons.PauseButton;
import SpecifiedButtons.ResearchButton;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 11/08/2015.
 */
public class InGameMenu {//} extends StandardMenu {

    float xOffset=   0.10f;
    float yOffset =  -0.1f;

    WalletWatcher walletWatcher;//Wallet watcher should not be here, but separate..

    public InGameMenu()
    {

        UIToggleButton pauseButton = new PauseButton(xOffset, yOffset,Alignment.BottomLeft, !SingletonObjects.isActionPaused,0.3f,0.3f);
        UIButton nightmodeButton = new NightmodeButton(-0.2f, -0.2f, Alignment.BottomRight, 0.3f , 0.1f);
        UIButton researchButton = new ResearchButton(-0.2f, -0.1f, Alignment.BottomRight, 0.3f , 0.1f);

        UIButton mineButton = new MineButton(-0.2f, -0.3f, Alignment.BottomRight, 0.32f , 0.1f);
        UIButton laserSatButton = new LaserSatButton(-0.2f, -0.4f, Alignment.BottomRight, 0.3f , 0.1f);


        //other ideas
        //a launch button that does straight to the planet and launch mode?
        // launch mode would go to home and planet menu at the same time.
        // generic menu exit mode is needed.
        ///screnshotbutton

        walletWatcher = new WalletWatcher();
    }

    //This should send the player camera cursor back to the planet
    public class HomeButton extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }
    }
     //Fallout shelter places this on the botton left
    public class Screenshot extends UIButton
    {
        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {
        }

    }


}
