package states;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by lawless on 04/02/2015.
 */
public class ExplosionState  extends State

{
        int transparency= 150;

        public ExplosionState() {
        super();

    }





        @Override
        public void Enter(Entity entity) {

        //setChanged();
        //	notifyObservers();




      //  entity.delete();
    }
        @Override
        public void Exit(Entity entity) {
        // TODO Auto-generated method stub
    }



        @Override
        public void Update(Entity entity)
        {

                entity.lifecount++;
                entity.body.scale(1.006f);

            if(entity.lifecount >77)
            {
                entity.delete();
            }

        }









}
