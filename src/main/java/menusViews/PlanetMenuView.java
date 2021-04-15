package menusViews;

import com.threed.jpct.SimpleVector;


import Entity_properties.Payload;
import Entity_types.Planet;
import MenuObjects.UIButton;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;

public class PlanetMenuView extends EntityMenu  {

	UIButton button1 = new OrbitButton(Payload.LaserSatellite);
	UIButton button2 = new OrbitButton(Payload.BatterySatellite);
	UIButton button3 = new OrbitButton(Payload.Mine);
	//UIButton button1 = new OrbitButton();

		public PlanetMenuView(Planet planet)
		
		{
			super(planet);
	 		SingletonObjects.hudFactory.createGlyphButton(button1,planet,
					new SimpleVector(0,1,0.0), new SimpleVector(0.0, 1, 0.0), "awesomeface", 4.0f ,4.0f,5.0f,0.9f);

			SingletonObjects.hudFactory.createGlyphButton(button2,planet,
					new SimpleVector(1,0.6f,1), new SimpleVector(0.5, 1, 0.5), "awesomeface", 4.0f ,-2.0f,5.0f,0.9f);

			SingletonObjects.hudFactory.createGlyphButton(button3,planet,
					new SimpleVector(0.1,0.5,1f), new SimpleVector(0.5, 1, 0.5), "awesomeface", 2.0f ,8.0f,5.0f,0.9f);

		}

	@Override
	public void closeMenu() {
		super.closeMenu();
		SingletonObjects.menumanager.addToRemovalList(button1);
		SingletonObjects.menumanager.addToRemovalList(button2);
		SingletonObjects.menumanager.addToRemovalList(button3);
	}

	@Override
	public void update() {

	}


	public class OrbitButton extends UIButton
	{
		int payload;
		public  OrbitButton(int payload)
		{
			super();
			this.payload =payload;
		}

		@Override
		public  void actionDoubleTapFunction(RayPickPoints points) {

			OrbitalSelectionMenu menu = new OrbitalSelectionMenu(payload,getParent());
			die=true;
		}
	}





}
