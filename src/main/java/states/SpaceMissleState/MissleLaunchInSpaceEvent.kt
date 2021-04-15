package states.SpaceMissleState

import Entity_types.BaseEntitys.Entity
import Entity_types.BaseEntitys.StarShipBasic
import Entity_types.BasicMissle
import Steering.BehaviourMode
import Steering.UpdateSteering
import states.EntityStates
import states.State



class MissleLaunchInSpaceEvent : State() {


    override fun Enter(entity: Entity) {



        (entity as StarShipBasic).behaviour_mode= BehaviourMode.WANDER;
        (entity as BasicMissle).thrustertrail.isOn=false;



    }

    override fun Exit(entity: Entity) {
        // TODO Auto-generated method stub

    }


    override fun Update(entity: Entity) {


        if (entity.body != null) {

            (entity as StarShipBasic).behaviour_mode= BehaviourMode.WANDER;
            (entity as BasicMissle).thrustertrail.isOn=false;

            entity.lifecount++




            if(entity.lifecount >100)
            {

                entity.Switch_State(EntityStates.missleBehaviourSeek)
            }

            UpdateSteering(entity)

        }
    }


}
