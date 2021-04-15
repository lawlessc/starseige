package Entity_properties;

import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by lawless on 03/08/2015.
 */
public interface Collideable {


     boolean isCollidable();

     boolean isEthereal();//this will cover lasers etc. etheral things should not be destroyed by solid collisions

    SimpleVector collisionPoint();

    float collisionArea();//the distance from the object that impact happens, in case of beams, effects..

    void  collisionEffect(Entity x);//Every object has an effect on collision that should be applied to whatever it hits.

}
