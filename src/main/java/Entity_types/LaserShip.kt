package Entity_types

import Entity_types.BaseEntitys.StarShipBasic
import Steering.BehaviourMode
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.GLSLShader
import com.threed.jpct.Object3D
import com.threed.jpct.SimpleVector
import states.EntityStates
import states.State


class LaserShip(pos: SimpleVector, velocity : SimpleVector, state : State, bodyreference : Object3D, shader: GLSLShader) :
        MissleGunShip(pos, velocity  ,state, bodyreference , shader) {

    init {
        FireReady = 900//
        FireCharge = 900
        mass = 10.0f

        this.colour = SimpleVector(1f, 1f, 0.2f)
        this.glowColour = SimpleVector(0.6, 0.3, 0.1) //should be yellow
        this.payment = 0//no pay for this
        this.max_speed = 0.026f

        thrustertrail.isOn = true
        behaviour_mode = BehaviourMode.WANDER

    }

    override fun fire() {
        SingletonObjects.object_factory.createRedLaser(this, Target_Entity)
    }

    override fun firingDistance(): Float {
        return 40.0f
    }

}
