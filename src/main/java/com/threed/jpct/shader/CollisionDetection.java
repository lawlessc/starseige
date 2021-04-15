package com.threed.jpct.shader;


import java.util.concurrent.Executors;

import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;

public final class CollisionDetection {
	float distance;
	//float collisionDistance = 1.05f;
	//int framelength = 1000/60;

	public CollisionDetection() {
	}

	public void collisionDetection() {
		for (int i = 0; i < SingletonObjects.game_objects.size(); i++) {
			Entity one = SingletonObjects.game_objects.get(i);
			if ((one.isCollidable())) {
				//X starts at i to help reduce repeatedly going over already checked objects
				for (int x = i; x < SingletonObjects.game_objects.size(); x++) {
					if (i != x) {
						Entity two = SingletonObjects.game_objects.get(x);


						if ((two.isCollidable()) && (two.allegiance != one.allegiance)) {

							distance = SingletonObjects.game_objects.get(i).position.distance(SingletonObjects.game_objects.get(x).collisionPoint());

							//firing check
							if (distance <= one.firingDistance()) {
								if (one.canFire() && two.isTargetable()) {
									one.setTarget(two);
								}
							}

							if (distance <= two.firingDistance()) {
								if (two.canFire() && one.isTargetable()) {
									two.setTarget(one);
								}
							}

							//Collision check
							if (distance <= (one.collisionArea()+two.collisionArea()) ) {

								//	collisionAction.applyEffect(one,two);
								one.collisionEffect(two);
								two.collisionEffect(one);

							}
						}
					}
				}

			}
		}
	}//end of collision detection

}

	
	
	