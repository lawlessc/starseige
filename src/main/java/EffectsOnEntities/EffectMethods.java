package EffectsOnEntities;


import java.util.Vector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 22/11/2017.
 */

public interface EffectMethods {



    public void  applyEffect(Entity ent, float strength) ;


    public   void applyEffect(Entity ent) ;

    public  void applyEffect(Entity ent, Entity ent2) ;


    public  void applyEffect(Vector<Entity> entities);


}
