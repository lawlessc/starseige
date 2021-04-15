package EffectsOnEntities;

import java.util.Vector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 04/05/2016.
 */
public enum
ExplosionDamage implements EffectMethods {
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


    float explosionStrength;


    @Override
    public void applyEffect(Entity ent, float strength) { //as an explosion is a shockwave we should apply a second distance check inside


       // if()


    }


}
