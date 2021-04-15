package gameObjects;

import com.threed.jpct.Object3D;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;

//



   
public class FactoryObserver implements Observer{
	

    private Vector<Entity> new_additions_list = new Vector<Entity>();
	private Vector<Entity> removals_list = new Vector<Entity>();


    public FactoryObserver()
	{}
    

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if(data instanceof Entity)
		{
			Entity ent = (Entity)data;
			new_additions_list.add(ent);

			if(ent.starter_state != null)
			{
			ent.Switch_State(ent.starter_state);
			}
		}

	}


	public void removeObject(Entity ent)
	{
		removals_list.add(ent);
	}


	//Called just before draw
	public void additions()
	{
		for(int i = 0; i < new_additions_list.size() ; i ++ )
		{
			add_entity_to_main(new_additions_list.get(i));
		}


		handleRemovals();
		new_additions_list.clear();

	}

	public void handleRemovals()
	{
		//entities
		for(int k = 0; k < removals_list.size() ; k++ )
		{
			removals_list.get(k).delete();
		}
		removals_list.clear();

	}
	
	
	public void add_entity_to_main(Entity newent)
	{
		newent.add_to_world(SingletonObjects.world);
		addObject3dToMain(newent.body);
		SingletonObjects.game_objects.add(newent);
		newent.isAlive=true;
	}


	public void addObject3dToMain(Object3D obj)
	{
		SingletonObjects.world.addObject(obj);
	}

}
