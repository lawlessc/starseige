package Events.ComplexEvents

import Events.Event

//For a win event we should surround the planet with  fireworks :-)
//Show a you won object the the play :-)
class WinGameEvent(): Event() {



    override fun Event() {

      //  Main.allGameObjects.INSTANCE.object_factory.createAlienPlanetTorpedo(Main.allGameObjects.INSTANCE.object_factory.randomPointAtdistance(40.0f),
     //           SimpleVector(0.0,0.0,0.0) , EntityStates.planetAttackerBasic);
        //Create a bunch of fireworks objects and

        setDone()
    }




}