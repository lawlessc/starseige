package Entity_types;

import java.util.Vector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 04/05/2016.
 */
public interface EffectInterface {

    void applyEffect(Entity ent, float strength);
    void applyEffect(Entity ent) ;
    void applyEffect(Entity ent,Entity ent2);
    void applyEffect(Vector<Entity> entities);
}
