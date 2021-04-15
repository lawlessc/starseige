package Particles;

import com.threed.jpct.World;
import com.threed.jpct.shader.Main;

import java.util.Vector;
import java.util.concurrent.Executors;

import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 19/07/2015.
 */
public final class
ParticleManager
{

    World world;
    Vector<PartEmitter> partEmitters = new Vector<PartEmitter>();

    Vector <PartEmitter>toBeAdded = new Vector<PartEmitter>();
    Vector <PartEmitter>toBeRemoved = new Vector<PartEmitter>();



    Boolean clearAtNext = false;
    Float   difference =0f;

    int framelength = 1000/60;




    public ParticleManager(World world)
      {

          this.world = world;


          // This will hopefully keep particles running on another core
          // And prevent particles slowing down the gameoverall.
          Executors.newSingleThreadExecutor().execute(new Runnable() {
              @Override
              public void run() {
                  while (true) {



                      if(!SingletonObjects.isActionPaused) {


                          int index = partEmitters.size();
                          for (int i = 0; i < index; i++) {
                              partEmitters.elementAt(i).Update(difference);

                          }
                      }

                     // doAdditions();

                      try {Thread.sleep(framelength);}
                      catch(InterruptedException ie)
                      {}

                  }
              }
          });


      }



    public void remove(PartEmitter emitter)
    {
      toBeRemoved.add(emitter);

    }

    public void Add(PartEmitter emitter)
    {
        toBeAdded.add(emitter);

    }



    public void update(float Diff)
    {
        doRemovals();
        doAdditions();
        difference=Diff;
    }

    public  void doRemovals()
    {

        if(clearAtNext)
        {
            clear();
            clearAtNext=false;
        }




        if(toBeRemoved.size() >0)
        {
            //  int index = removals_list.size();
            for(int i =0 ; i < toBeRemoved.size() ; i++)
            {

                partEmitters.remove(toBeRemoved.elementAt(i));
                toBeRemoved.elementAt(i).clear(world);
                toBeRemoved.remove(i);
               // System.gc();
            }

        }
    }




    public void doAdditions()
    {

        int index = toBeAdded.size();

        for(int i = 0 ; i < index ; i++)
        {

            if(!partEmitters.contains(toBeAdded.elementAt(i) ))
            {
                partEmitters.add(toBeAdded.elementAt(i));


            //The emmiters handle aadding themselves to the world
 //	world.addObject(new_additions_list.elementAt(i).glyph.plane);
              //  toBeAdded.elementAt(i).addTo(world);
            }
        }


        toBeAdded.clear();
    }





    private void clear(){

        int index = partEmitters.size();

        for(int i =0 ; i < index ; i++){

            partEmitters.elementAt(i).clear(world);
        }


        partEmitters.clear();
    }



    public void ClearAll()
    {
      clearAtNext = true;

    }

}



