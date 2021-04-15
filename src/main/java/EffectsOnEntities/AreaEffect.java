package EffectsOnEntities;

import com.threed.jpct.SimpleVector;

import Entity_types.EffectInterface;
import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;


/**
 * Created by Chris on 23/09/2016.
 * basically for splash damage other effects
 * eg during an explosion, or maybe a collision with lots of components present
 * or a repair field
 *
 */
public class AreaEffect {
    EffectInterface effect;
    SimpleVector centerPoint;
    float        effectDistance;

    AreaEffect(EffectInterface effect ,SimpleVector point,float effectDistance)
    {
        this.effect=effect;
        this.centerPoint=point;
        this.effectDistance=effectDistance;


    }

    public void ApplyAreaEffect()
    {

        for (Entity ent: SingletonObjects.game_objects) {

            float dist = ent.position.distance(centerPoint);
            if(dist<effectDistance)
            {
                effect.applyEffect(ent,effectDistance/dist);
            }

        }


    }






}
