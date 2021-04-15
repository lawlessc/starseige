package Entity_types

import Entity_types.BaseEntitys.StarShipBasic
import Steering.BehaviourMode
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.GLSLShader
import com.threed.jpct.Object3D
import com.threed.jpct.SimpleVector
import states.EntityStates
import states.State

open class MissleGunShip(pos: SimpleVector, velocity : SimpleVector ,state : State, bodyreference : Object3D, shader: GLSLShader) :
        StarShipBasic(pos, velocity  ,state, bodyreference , shader) {


    internal var thrustertrail = SingletonObjects.particleEvents.rocketThruster(this, SimpleVector(0f, 0f, 0f))


    init {


        FireReady = 1200//
        FireCharge = 1200
        mass= 10.0f

        this.colour = SimpleVector(1f, 1f, 0.2f)
        this.glowColour = SimpleVector(0.6, 0.3, 0.1) //should be yellow
        this.payment = 0//no pay for this
        this.max_speed= 0.026f


        thrustertrail.isOn = true
        behaviour_mode= BehaviourMode.WANDER

    }

    override fun fire() {
      val ent1=  SingletonObjects.object_factory.createAlienPlanetTorpedo(SimpleVector(this.position),
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

        val ent2=  SingletonObjects.object_factory.createAlienPlanetTorpedo(SimpleVector(this.position),
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)

        val ent3=  SingletonObjects.object_factory.createAlienPlanetTorpedo(SimpleVector(this.position),
                SimpleVector(0.0,0.0,0.0) , EntityStates.missleLaunchInSpaceEvent)


     ent1.setTarget(this.Target_Entity)
     ent2.setTarget(this.Target_Entity)
     ent3.setTarget(this.Target_Entity)


    }

    override fun firingDistance(): Float {
        return 40.0f
    }



}


