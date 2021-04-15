package states;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 11/04/2016.
 */
public class SelfDestruct_State extends State {
    @Override
    public void Enter(Entity entity) {

    }

    @Override
    public void Exit(Entity entity) {
        //Why bother?

    }

    @Override
    public void Update(Entity entity) {


        int sub= (entity.maxHealthPoints/100)*1;

        entity.damageInflicted(01);



    }
}
