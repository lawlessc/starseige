package Steering

import Entity_properties.CollisionAvoidance
import Entity_types.BaseEntitys.Entity
import Entity_types.BaseEntitys.StarShipBasic
import com.threed.jpct.SimpleVector


  fun UpdateSteering(entity: Entity?) {

        if (entity != null && entity.Target_Entity != null) {

            chooseBehavior(entity.Target_Entity.position, entity as StarShipBasic)

            if((entity as CollisionAvoidance).hasCollisionAvoidance())
            {
                //we should do collision avoidance here
            }

            entity.steerforce.x = entity.steerforce.x / entity.mass
            entity.steerforce.y = entity.steerforce.y / entity.mass
            entity.steerforce.z = entity.steerforce.z / entity.mass

            entity.velocity.add(entity.steerforce)
            entity.position.add(entity.velocity)
        }
    }


    /**

     */
    inline   fun seek(targetPosition: SimpleVector, ent: StarShipBasic) {
        ent.desired_veclocity = targetPosition.calcSub(ent.position)
        ent.distance_to_target = ent.desired_veclocity.length()
        ent.desired_veclocity = ent.desired_veclocity.normalize()
        ent.desired_veclocity.scalarMul(ent.max_speed)
        ent.steerforce = ent.desired_veclocity.calcSub(ent.velocity)
    }

inline fun calculateLookAhead(ent: StarShipBasic)
{
    val velo = SimpleVector(ent.velocity)
    velo.normalize()

    ent.ahead = ent.position.calcAdd(velo)
    ent.ahead.scalarMul(ent.MAX_SEE_AHEAD)
    ent.ahead2 = SimpleVector(ent.ahead)
    ent.ahead2.scalarMul(0.5f)
}



  fun  avoidanceForce(ent: StarShipBasic , obstacle: Entity)
  {
      val  avoidanceforce = ent.ahead.calcSub(obstacle.position)
           avoidanceforce.normalize()
           avoidanceforce.scalarMul(ent.MAX_AVOID_FORCE)
       ent.steerforce =    avoidanceforce.calcSub(ent.velocity)



      //avoidance_force = ahead - obstacle_center
     // avoidance_force = normalize(avoidance_force) * MAX_AVOID_FORCE
  }
    /**

     */
    inline   fun flee(targetPosition: SimpleVector, ent: StarShipBasic) {
        ent.desired_veclocity = ent.position.calcSub(targetPosition)
        ent.distance_to_target = ent.desired_veclocity.length()
        ent.desired_veclocity = ent.desired_veclocity.normalize()
        ent.desired_veclocity.scalarMul(ent.max_speed)
        ent.steerforce = ent.desired_veclocity.calcSub(ent.velocity)
    }

inline   fun WanderSeek(ent: StarShipBasic)
     {

         if(Math.random() > 3.999)
         { wander(ent)}
         else
         { seek(ent.Target_Entity.position, ent)}
         //seek(entity.Target_Entity.position, entity as StarShipBasic)
     }



inline fun wander(ent: StarShipBasic) {
    //// Calculate the circle center
    var circlecenter = SimpleVector(ent.velocity)
    circlecenter.normalize()
    circlecenter.scalarMul(5.0f)

    // Calculate the displacement force
   var displacement = SimpleVector((Math.random()*2 -1) , (Math.random()*2 -1) , (Math.random()*2 -1) );
       displacement.scalarMul(5.0f);

    displacement= setAngle(displacement, ent.wanderAngle)

   // ent.wanderAngle += ((Math.random() * 15.05f) - ( 15.05f * .03)).toFloat()

    ent.wanderAngle +=  (Math.random() * 0.2 - 0.2 * .5).toFloat();

    var wanderforce = circlecenter.calcAdd(displacement)
    wanderforce = wanderforce.normalize()
    wanderforce.scalarMul(ent.max_speed)
    ent.steerforce = wanderforce.calcSub(ent.velocity);
}



//ahead = position + normalize(velocity) * MAX_SEE_AHEAD




//inline fun collisionAvoidCircle(ent: StarShipBasic) {
//    //// Calculate the circle center
//    var circlecenter = SimpleVector(ent.velocity)
//    circlecenter.normalize()
//    circlecenter.scalarMul(5.0f)
//
//    // Calculate the displacement force
//    var displacement = SimpleVector((Math.random()*2 -1) , (Math.random()*2 -1) , (Math.random()*2 -1) );
//    displacement.scalarMul(5.0f);
//
//    displacement= setAngle(displacement, ent.wanderAngle)
//
//    // ent.wanderAngle += ((Math.random() * 15.05f) - ( 15.05f * .03)).toFloat()
//
//    ent.wanderAngle +=  (Math.random() * 0.2 - 0.2 * .5).toFloat();
//
//    var wanderforce = circlecenter.calcAdd(displacement)
//    wanderforce = wanderforce.normalize()
//    wanderforce.scalarMul(ent.max_speed)
//    ent.steerforce = wanderforce.calcSub(ent.velocity);
//}






inline fun chooseBehavior(targetPosition: SimpleVector, ent: StarShipBasic) {
       when (ent.behaviour_mode) {
           BehaviourMode.SEEK -> {
               seek(targetPosition, ent)
           }

           BehaviourMode.FLEE -> {
               flee(targetPosition, ent)
           }

           BehaviourMode.WANDER -> {
               wander(ent)
           }

       }
   }


inline fun setAngle(vector : SimpleVector, value: Float ): SimpleVector {
    var len  = vector.length().toDouble()
    vector.x = (Math.cos(value.toDouble()) * len).toFloat()
    vector.y = (Math.sin(value.toDouble()) * len).toFloat()
    vector.z = (Math.tan(value.toDouble()) * len).toFloat()
    return vector
}





//}