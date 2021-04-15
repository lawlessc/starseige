package EffectsOnEntities;

import java.util.Vector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 14/11/2017.
 */

public enum  DebrisDamage  implements EffectMethods {
    INSTANCE {
        @Override
        public void applyEffect(Entity ent, Entity ent2) {

        }

        @Override
        public void applyEffect(Vector<Entity> entities) {

        }
    };


    @Override
    public  void applyEffect(Entity ent, float strength) {

    }

    @Override
    public void applyEffect(Entity ent) {

        collisionAction(ent);
    }



    private static void collisionAction(Entity a)
    {
        //we may need to check here to avoid something stupid like breaking a laser

        if(!a.isEthereal()) {//we exclude lasers this way,and other things?
            a.healthPoints -=1;
        }


    }




}
