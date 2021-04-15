package EffectsOnEntities;


import java.util.Vector;

import Entity_types.BaseEntitys.Entity;
import Entity_types.Planet;
import states.EntityStates;

/**
 * Created by Chris on 04/05/2016.
 */
public enum SolidCollisionEffect implements EffectMethods
{
    INSTANCE;

    @Override
    public  void applyEffect(Entity ent) {
        collisionAction(ent);
    }


  @Override
    public  void applyEffect(Entity ent, float strength) {


    }


   @Override
    public void applyEffect(Entity ent, Entity ent2){}

   @Override
   public void applyEffect(Vector<Entity> entities) {
            //    collisionAction(ent);
            }


     private void collisionAction(Entity a)
    {
          //we may need to check here to avoid something stupid like breaking a laser

             if(!a.isEthereal() ) {//we exclude lasers this way,and other things?
                 if( a instanceof Planet){

                 }
                 else
                 {
                     a.Switch_State(EntityStates.explodeState);
                 }
             }



    }
}




