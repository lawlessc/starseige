package EffectsOnEntities;

import java.util.Vector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 05/05/2016.
 */
public enum Discharge  implements EffectMethods {
    INSTANCE {
        @Override
        public void applyEffect(Entity ent) {

        }

        @Override
        public void applyEffect(Entity ent, Entity ent2) {

        }

        @Override
        public void applyEffect(Vector<Entity> entities) {

        }
    };



    @Override
    public void applyEffect(Entity ent, float strength) {

    }

}
