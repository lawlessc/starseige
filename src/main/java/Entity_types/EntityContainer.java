package Entity_types;


import Entity_types.BaseEntitys.Entity;

//This purely exists for Kotlin purposes
public class EntityContainer {


    public void setEnt(Entity ent) {
        this.ent = ent;
    }

    public boolean isThisNull()
    {
        return ent == null;
    }




    public Entity ent;
}
