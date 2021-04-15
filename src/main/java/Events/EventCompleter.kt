package Events

import java.util.ArrayList


class EventCompleter()
{

    var events = ArrayList<Event>(100)




  fun addEvent(event_to_add:Event){
      events.add(event_to_add)
  }


    fun update()
{//In here we loop through event items sequantially.
     //Only the first item in the list should be touched.
     //events marked as completed should be deleted from the list
       if(events.size >0){

        if(events.get(0).eventcalled)
        {
            events.removeAt(0)
        }
        else
        {
          events[0].doEvent()
        }
}}


    fun clearAll()//This could be useful when we need all events gone and replaces with a lose  or win event?
    {
        events.clear()
    }





}