package menusViews;

import com.threed.jpct.SimpleVector;

import Entity_properties.Orbiting;
import Entity_types.BaseEntitys.Entity;
import OrbitCalcs.OrbitData;

/**
 * Created by Chris on 25/02/2016.
 */
public class OrbitChangeMenu extends EntityMenu
{
    GridWidget grid;
    OrbitWidget originOrbit;
    OrbitSelect orbitSelect;
    OrbitData originalOrbitData;
    Orbiting subject;

    public OrbitChangeMenu(Entity ent)
    {
       super(ent);

        Orbiting orb =  (Orbiting) ent;

        subject = orb;
        originalOrbitData = subject.getOrbit();
        orbitSelect = new OrbitSelect(subject.getOrbit().getPucposition());
        grid= new GridWidget();
        originOrbit = new OrbitWidget(originalOrbitData);
        orbitSelect.UpdateLines();
    }



   @Override
    public void closeMenu() {
       super.closeMenu();
       grid.closeWidget();
       orbitSelect.closeWidget();
       originOrbit.closeWidget();
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
        public void OnDoubleTap()
        {
            originalOrbitData.beginSegue(orbitSelect.orbitData, subject);
            closeMenu();
        }

    }


}
