package baseinterfacesclasses;

import com.threed.jpct.SimpleVector;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Entity_properties.Allegiance;
import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 08/04/2016.
 * This class will maintain multiple lists of units
 * based on allegiance for
 */
public final class TargetSelection implements Observer {


    public ArrayList<Entity> homeworld = new ArrayList<Entity>(49);
    public ArrayList<Entity> aliens = new ArrayList<Entity>(49);

    public TargetSelection(){};




    @Override
    public void update(Observable observable, Object data) {
        // TODO Auto-generated method stub
        if(data instanceof Entity)
        {
           Entity ent= (Entity)data;
            assignByAllegiance(ent);
        }

    }



    //called by the above function and called when an objects allegience is changed
    public void assignByAllegiance(Entity entity)
    {

        switch (entity.allegiance) {
            case Allegiance.HomeWorld:
                homeworld.add(entity);
                break;
            case Allegiance.Aliens:
                aliens.add(entity);
                break;
            case Allegiance.Unocupied:
                break;
        }


    }

    public void switchAllegiance(Entity entity, int allegiance)
    {

        switch (entity.allegiance) {
            case Allegiance.HomeWorld:
                homeworld.remove(entity);
                entity.allegiance=allegiance;
                assignByAllegiance(entity);
                break;
            case Allegiance.Aliens:
                aliens.remove(entity);
                entity.allegiance=allegiance;
                assignByAllegiance(entity);

                break;
            case Allegiance.Unocupied:
                entity.allegiance=allegiance;
                assignByAllegiance(entity);
                break;
        }



    }

    public void removeEntity(Entity ent)
    {

        try {

          //  if (ent.allegiance != null) {
                if (ent!= null) {
                switch (ent.allegiance) {
                    case Allegiance.HomeWorld:
                        homeworld.remove(ent);
                        break;
                    case Allegiance.Aliens:
                        aliens.remove(ent);
                        break;
                    case Allegiance.Unocupied:
                        break;
                    case Allegiance.Debris:
                        break;
                    case Allegiance.Untouchable:
                        break;
                }
            }
            }
            catch(Error e)
            {
                //nah

            }


    }



    public void updateLists(){}




    public void addHomeWorlder(Entity entity)
    {
      homeworld.add(entity);
    }

    public void addEnemy(Entity entity)
    {
        homeworld.add(entity);
    }


    	public Entity findEnemyInRange(Entity ent)
    {
        for(int i = 0 ; i < aliens.size() ; i++) {
            if(rangeCheck(ent.range, aliens.get(i).position, ent.position))
            {
                Entity enemy=  aliens.get(i);
                return enemy;
            }
        }
        return  null;
    }


    public Entity findHomeworlder(Entity ent)
    {
        for(int i = 0 ; i < homeworld.size() ; i++) {
            if(rangeCheck(ent.range, homeworld.get(i).position, ent.position))
            {
                Entity home=  homeworld.get(i);
                return home;
            }
        }
        return  null;
    }



    public boolean rangeCheck(float range, SimpleVector subjectposition, SimpleVector entposition)
    {

        if( subjectposition.distance(entposition) < range)
        {
            return true;
        }
        return  false;
    }

}
