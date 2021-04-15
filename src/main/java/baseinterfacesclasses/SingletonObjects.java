package baseinterfacesclasses;

import com.threed.jpct.Camera;
import com.threed.jpct.World;
import com.threed.jpct.shader.CameraCursor;
import com.threed.jpct.shader.CollisionDetection;
import com.threed.jpct.shader.GoodVibrations;
import com.threed.jpct.shader.PostProcessHandler;

import java.util.ArrayList;

import Entity_types.BaseEntitys.Entity;
import Entity_types.SkySphere;
import EventManagers.GameSetup;
import EventManagers.SoundPoolEvents;
import Events.EventCompleter;
import MenuObjects.UIMenuManager;
import MenuObjects.UIObjectFactory;
import Particles.ParticleEvents;
import Particles.ParticleManager;
import Player_wealth.Player_money;
import gameObjects.EntityFactory;
import gameObjects.FactoryObserver;

public class SingletonObjects {

    public  static ArrayList<Entity> game_objects = new ArrayList<Entity>(49);

    public  static ParticleManager particleManager=null;
    public  static ParticleEvents particleEvents=null;

    //public  static GameCommandManager gameCommand=new GameCommandManager();

    public  static EntityFactory object_factory=null;
    public  static FactoryObserver factoryObserver=null;
    public  static GoodVibrations vibrationEvents=null;

    public static World world=null;
    public static Camera cam=null;
    //might make sense to start this at 0 anyway
    public static long runningTime = 0;
    public static long runningTimeSeconds;
    public static Boolean isActionPaused=false;

    public static UIMenuManager menumanager=null;
    public static UIObjectFactory hudFactory=null;

    public static SoundPoolEvents soundManagement=null;
    //public MenuNotificationObserver menuNotificationObserver = null;
    public static CameraCursor cameraCursor=null;// new CameraCursor(this);

    public static GameSetup gameeventmanager=null;
    public static SkySphere skysphere=null;
    public static PostProcessHandler processHandler=null;
    public static CollisionDetection colldec=null;

    public static TargetSelection targetSelection=new TargetSelection();
    public static Player_money player_money=new Player_money();

    public static EventCompleter eventCompleter=new EventCompleter();


    //Important entities AKA the Star and Planet
    public static Entity main_planet= null;




}
