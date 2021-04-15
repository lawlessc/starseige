package states.SpaceShipStates

import states.State
import Entity_types.BaseEntitys.Entity
import Entity_types.BaseEntitys.StarShipBasic
import Steering.*
import Steering.UpdateSteering
import states.EntityStates

/**
 * Created by Chris on 31/01/2018.
 */

class SpaceShipFlee : State() {
    override fun Enter(entity: Entity) {

        (entity as StarShipBasic).behaviour_mode= BehaviourMode.FLEE;
    }

    override fun Exit(entity: Entity) {

    }

    override fun Update(entity: Entity) {

        (entity as StarShipBasic).behaviour_mode= BehaviourMode.FLEE;

        if(entity.Target_Entity != null)
        {

            if(entity.position.distance(entity.Target_Entity.position) > 30)
            {
                entity.Switch_State(EntityStates.spaceShipWander)
            }

        }

        UpdateSteering(entity)
        }

    }

