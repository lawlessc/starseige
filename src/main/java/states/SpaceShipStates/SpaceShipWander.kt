package states.SpaceShipStates

import Entity_types.BaseEntitys.Entity
import Entity_types.BaseEntitys.StarShipBasic
import Steering.BehaviourMode
import Steering.UpdateSteering
import states.EntityStates
import states.State


/**
 * Created by Chris on 31/01/2018.
 */

class SpaceShipWander : State() {
    override fun Enter(entity: Entity) {


        (entity as StarShipBasic).behaviour_mode= BehaviourMode.WANDER;

    }

    override fun Exit(entity: Entity) {

    }

    override fun Update(entity: Entity) {

        (entity as StarShipBasic).behaviour_mode= BehaviourMode.WANDER;


        if(entity.Target_Entity != null)
        {



            if(entity.position.distance(entity.Target_Entity.position) > 40)
            {

               entity.Switch_State(EntityStates.spaceShipSeek)

            }
            if(entity.position.distance(entity.Target_Entity.position) < 18)
            {

                entity.Switch_State(EntityStates.spaceShipFlee)

            }




        }






        UpdateSteering(entity)

    }

}
