package menusViews;

import com.threed.jpct.SimpleVector;

import Entity_properties.Allegiance;
import Entity_types.CarrierRocket;
import Entity_types.BaseEntitys.Entity;
import MenuObjects.Alignment;
import MenuObjects.UIToggleButton;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;
import gameObjects.EntityFactory;
import states.EntityStates;

/**
 * Created by lawless on 15/09/2015.
 */
public class OrbitalSelectionMenu extends EntityMenu {

    GridWidget grid;
    OrbitSelect orbitSelect;
    Boolean  isOrbitValid = true;
    SimpleVector startPosition = new SimpleVector(7,0,7);
    GradeButton retroGrade = new GradeButton();
    int payload ;

    public OrbitalSelectionMenu(int payload,Entity parent)
    {
        super(parent);
        this.payload =payload;
        grid= new GridWidget();
        orbitSelect = new OrbitSelect(startPosition);

        SingletonObjects.hudFactory.createGlyphToggleButton(retroGrade, new SimpleVector(1, 1, 1),
                new SimpleVector(0.5,1,0.5),"arrowright","arrowleft", 0.8f, -0.1f, true, Alignment.BottomLeft,0.3f,0.3f);

        orbitSelect.UpdateLines();
    }


    @Override
    public void closeMenu() {
        super.closeMenu();
        grid.closeWidget();
        orbitSelect.closeWidget();
        SingletonObjects.menumanager.addToRemovalList(retroGrade);
    }

    @Override
    public void update() {

        if(grid !=null) {
            grid.update();
        }

    }

    public  class OrbitSelect extends  OrbitSelectorWidget
   {
       public OrbitSelect(SimpleVector startPosition) {
           super(startPosition);
       }

       @Override
       public void  OnDoubleTap()
       {
          CarrierRocket rocket= SingletonObjects.object_factory.createCarrierRocket(EntityStates.launchState,
                   payload,  orbitSelect.orbitData.returnCopy());

           rocket.allegiance= Allegiance.Player;

           //System.out.println("ORBIT CREATED");
           orbitData.outputOrbitData();

           closeMenu();
       }
   }

    public class GradeButton extends UIToggleButton
    {

        @Override
        public  void actionSingleTapFunction(RayPickPoints points) {

            if(orbitSelect.orbitData.isPrograde())
            {
                orbitSelect.orbitData.setRetrograde();
                setToggle(false);
            }
            else
            {
                orbitSelect.orbitData.setPrograde();
                setToggle(true);
            }
        }
    }
}
