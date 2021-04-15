package EventManagers;

import android.content.Context;
import android.content.res.Resources;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.World;

import com.threed.jpct.shader.PostProcessHandler;

import Events.ComplexEvents.FirstStageEvent;
import Events.ComplexEvents.SecondStageEvent;
import Events.SetupEvents.GameSetupEvent;
import MenuObjects.UIMenuManager;
import MenuObjects.UIObjectFactory;
import Particles.ParticleEvents;
import Particles.ParticleManager;
import baseinterfacesclasses.SingletonObjects;
import gameObjects.EntityFactory;
import gameObjects.FactoryObserver;
import menusViews.InGameMenu;
import menusViews.MainMenu;

//import MenuObjects.MenuNotificationObserver;


/**
 * Created by lawless on 23/07/2015.
 */
public final class GameSetup {


    public GameSetup()
    {}

    public void texturesLoaded(Context bc)
    {
        SingletonObjects.object_factory = new EntityFactory(bc);
        SingletonObjects.soundManagement = new SoundPoolEvents(bc);
    }

    public void mainMenu()
    {
        SingletonObjects.skysphere = SingletonObjects.object_factory.createSkySphere();
        MainMenu mainMenu= new MainMenu();
    }

    //anything needed to create the splash and loading screen
    public void preLoadingScreen(Resources res, int screenWidth,int screentHeight,FrameBuffer fb)
    {

        SingletonObjects.factoryObserver = new FactoryObserver();

        SingletonObjects.world = new World();
        SingletonObjects.cam = SingletonObjects.world.getCamera();
        SingletonObjects.cam.setPosition(0,0,0);
        SingletonObjects.cam.setClippingPlanes(2f,270);
        SingletonObjects.processHandler = new PostProcessHandler(SingletonObjects.world,res,fb);
        //Need to load the loading glyph(s) and the spalash here and fast.
        //as these will need to be displayed some multitasking may be needed.
    }

    public void LoadingScreen(Resources res, int screenWidth,int screentHeight,FrameBuffer fb)
    {

        SingletonObjects.particleManager = new ParticleManager(SingletonObjects.world);
        SingletonObjects.particleEvents = new ParticleEvents(SingletonObjects.particleManager);

        SingletonObjects.object_factory.addObserver(SingletonObjects.factoryObserver);
        SingletonObjects.object_factory.addObserver(SingletonObjects.targetSelection);

        SingletonObjects.object_factory.setupFactory(res);

        SingletonObjects.menumanager = new UIMenuManager(SingletonObjects.cam,screenWidth,screentHeight,fb);

        SingletonObjects.hudFactory = new UIObjectFactory(res);
        SingletonObjects.hudFactory.addObserver(SingletonObjects.menumanager);

    }


    //This sets up event managers etc, These are neededto run the game, not one specific level
   public void mainSetup(Resources res, int screenWidth,int screentHeight,FrameBuffer fb )//,Context baseContext)
   {
    //This get's us as far as the menu
           ///preLoadingScreen
       SingletonObjects.factoryObserver = new FactoryObserver();

       SingletonObjects.world = new World();
       SingletonObjects.cam = SingletonObjects.world.getCamera();
       SingletonObjects.cam.setPosition(0,0,0);

       SingletonObjects.processHandler = new PostProcessHandler(SingletonObjects.world,res,fb);

       SingletonObjects.particleManager = new ParticleManager(SingletonObjects.world);
       SingletonObjects.particleEvents = new ParticleEvents(SingletonObjects.particleManager);

       SingletonObjects.object_factory.addObserver(SingletonObjects.factoryObserver);

       SingletonObjects.object_factory.setupFactory(res);

       SingletonObjects.menumanager = new UIMenuManager(SingletonObjects.cam,screenWidth,screentHeight,fb);

       SingletonObjects.hudFactory = new UIObjectFactory(res);
       SingletonObjects.hudFactory.addObserver(SingletonObjects.menumanager);

    }


   // public void addInRenderer
   public void clearGame()
   {
       SingletonObjects.menumanager.clearAtnext();

       for(int i=0; i < SingletonObjects.game_objects.size() ; i++)
       {
           SingletonObjects.factoryObserver.removeObject(SingletonObjects.game_objects.get(i));
       }
       SingletonObjects.particleManager.ClearAll();
   }

    public void MainLevel()
    {
        InGameMenu inGameMenu = new InGameMenu();//This is actually important as it starts the wallet class object..might want to delete it later

        SingletonObjects.eventCompleter.addEvent(new GameSetupEvent());
        //SingletonObjects.eventCompleter.addEvent( new FirstStageEvent());
        SingletonObjects.eventCompleter.addEvent( new SecondStageEvent());
    }
}
