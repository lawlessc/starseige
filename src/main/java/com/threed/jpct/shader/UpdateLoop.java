package com.threed.jpct.shader;

import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 20/01/2015.
 */
public class UpdateLoop {


    public void updateEntities(float timeDiff) {
        for (int i = 0; i < SingletonObjects.game_objects.size(); i++) {
            SingletonObjects.game_objects.get(i).Update();

        }
        SingletonObjects.particleManager.update(timeDiff);

    }

}
