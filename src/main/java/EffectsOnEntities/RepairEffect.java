package EffectsOnEntities;

import java.util.Vector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 04/05/2016.
 */
public enum RepairEffect implements EffectMethods {
    INSTANCE;


    @Override
    public void applyEffect(Entity ent, float strength) {

    }

    @Override
    public void applyEffect(Entity ent) {

    }

    @Override
    public void applyEffect(Entity ent, Entity ent2) {

    }

    @Override
    public void applyEffect(Vector<Entity> entities) {

    }


    public void repair(Entity entity)
    {

        if(!entity.isEthereal()) {//we exclude lasers this way,and other things?
            entity.healthPoints += 1;
        }





    }

}
