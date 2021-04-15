package Events

import Entity_types.Asteroid
import Events.ComplexEvents.ConditionalEvent
import Events.ComplexEvents.SecondStageEvent
import Events.SetupEvents.AddMoneyEvent
import OrbitCalcs.OrbitData
import baseinterfacesclasses.SingletonObjects
import com.threed.jpct.SimpleVector
import states.EntityStates


class AsteroidEvent(): ConditionalEvent()  {

    lateinit  var asteroid : Asteroid





     override fun initialEvent() {

         val ctop = SimpleVector(10f, 20f, 10f)
       //  val planepos = SimpleVector(20f, 0f, 40f)
       //  val pucpos = SimpleVector(20f, 0f, 40f)
         val orbitData = OrbitData(ctop, 20f, 0f, SimpleVector.ORIGIN)


         asteroid= SingletonObjects.object_factory.createAsteroid(SimpleVector.ORIGIN,SimpleVector.ORIGIN,
                 EntityStates.orbitState,orbitData)

         SingletonObjects.cameraCursor.setFollow(asteroid)
     }

     override fun waitingEvent() {
         //just wait
     }

     override fun branchCondition(): Boolean {
         return !asteroid.isAlive
     }

     override fun closingevent() {


         SingletonObjects.eventCompleter. events.add(AddMoneyEvent(600))//almost immediate
         SingletonObjects.eventCompleter. events.add(SecondStageEvent())//MISSLES!


     }



     /*For the astoid event we need to add checks that confirm if the player correctly destroys the asteroid or it hit's the planet
     * If the planet is hit we load the asteroid lose event and show lots of explosions etc
     * If it's destroyed we begin loading main game alien attackes etc*/


}