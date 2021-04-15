package com.threed.jpct.shader;

import com.threed.jpct.SimpleVector;


import Entity_properties.Orbiting;
import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;


//Basic physics class
public class Physics {

    public SimpleVector zero= new SimpleVector(0,0,0);
	Physics(){}

	public void update(long lag)
	{
		updateOrbitPos(lag);
	}

	public  void updateOrbitPos(long lag)
	{

		int elems = SingletonObjects.game_objects.size();
		for(int x = 0 ; x <elems ;x++ )
		{
			Entity ent = SingletonObjects.game_objects.get(x);

			if(ent instanceof Orbiting) {
				Orbiting orbitent = (Orbiting) ent;


				if (orbitent.getOrbit() != null && orbitent.isOrbiting() == true) {


					//ent.position=	ent.orbit.calculatePosition();
					ent.position = orbitent.getOrbit().calculatePositionOffset();


				}

				if (orbitent.getOrbit() != null && orbitent.isOrbiting()== false) {
					//	ent.target =     ent.orbit.calculatePosition();
					ent.target = orbitent.getOrbit().calculatePositionOffset();
				}
			}
		}



	}

}
