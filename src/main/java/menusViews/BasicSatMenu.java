package menusViews;

import com.threed.jpct.SimpleVector;

import Entity_properties.Shieldable;
import Entity_types.BaseEntitys.Entity;
import MenuObjects.UIButton;
import SpecifiedButtons.SelfDestructButton;
import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 11/04/2016.
 */
public class  BasicSatMenu extends EntityMenu {

    Entity ent;

    public BasicSatMenu(Entity entity)

    {
        super(entity);
        ent= entity;

        UIButton button1 = new OrbitButton();
        this.getButtons().add(button1);
        SingletonObjects.hudFactory.createGlyphButton(button1,entity,
                new SimpleVector(1,1,1), new SimpleVector(0.5, 1, 0.5), "awesomeface", 4.0f ,5.0f,5.0f,0.9f);

        UIButton button2 = new SelfDestructButton(entity, this,4.0f ,-2.0f,5.0f,0.9f );
        this.getButtons().add(button2);

        if(entity instanceof Shieldable) {
            UIButton button3 = new SheildButton();
            this.getButtons().add(button3);
            SingletonObjects.hudFactory.createGlyphButton(button3, entity,
                    new SimpleVector(0.6, 0.7, 1.0), new SimpleVector(0.5, 1, 0.5), "awesomeface", -4.0f, -6.0f, 5.0f,0.9f);
        }
    }

    public void closeMenu()
    {
        super.closeMenu();
    }

    @Override
    public void update() {

    }

    public class OrbitButton extends UIButton
    {
        @Override
        public  void actionDoubleTapFunction(RayPickPoints points) {
            OrbitChangeMenu menu = new OrbitChangeMenu(ent);
            closeMenu();
        }
    }

    public class SheildButton extends UIButton
    {
        @Override
        public  void actionDoubleTapFunction(RayPickPoints points) {
            ((Shieldable)ent).spawnShield();
             closeMenu();
        }
    }










}
