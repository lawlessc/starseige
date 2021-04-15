package states;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 13/04/2016.
 */
public class Mine_orbit_state extends State {

    @Override
    public void Enter(Entity entity) {
        //some sort of arrival state
    }

    @Override
    public void Exit(Entity entity) {




    }

    @Override
    public void Update(Entity entity) {

        if(checkForCloseEnemy())
        {
            //detonate

        }

    }

   private boolean checkForCloseEnemy()
   {




       return false;
   }

}
