package states.SpaceMissleState

import Entity_types.BasicMissle
import Entity_types.BaseEntitys.Entity
import Entity_types.BaseEntitys.StarShipBasic
import Steering.BehaviourMode
import Steering.UpdateSteering
import states.State

class MissleBehaviourSeek : State() {


    override fun Enter(entity: Entity) {

        (entity as StarShipBasic).behaviour_mode= BehaviourMode.SEEK;

    }

    override fun Exit(entity: Entity) {
        // TODO Auto-generated method stub
    }


    override fun Update(entity: Entity) {


           entity.lifecount++


                (entity as StarShipBasic).behaviour_mode= BehaviourMode.SEEK;
                (entity as BasicMissle).thrustertrail.isOn=true;


            UpdateSteering(entity)

        }
    }



