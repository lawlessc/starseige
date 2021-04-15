package states;

import Entity_properties.Orbiting;
import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 15/02/2016.
 */
public class OrbitSegue extends State {

    @Override
    public void Enter(Entity entity) {

        SingletonObjects.soundManagement.playThrustHiss();

    }

    @Override
    public void Exit(Entity entity) {

        SingletonObjects.soundManagement.stopThrustHiss();

    }

    @Override
    public void Update(Entity entity) {
        SingletonObjects.soundManagement.playThrustHiss();
     System.out.println("STILL IN SEGUE");

        Orbiting orb= (Orbiting) entity;
        boolean finished_move=  orb.getOrbit().shiftToNewOrbit();


     if(finished_move)
     {

         entity.Switch_State(EntityStates.orbitState);
     }




    }
}
