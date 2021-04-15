package Entity_types.Beams;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 11/10/2017.
 */

public  abstract class FiringBeam extends Beam {


 //   Entity Target;
    int laserLife =30;

    public FiringBeam(Entity parent, Entity target, Object3D bodyReference, GLSLShader shader)
    {
        super(parent.getPosition(), target.getPosition(), bodyReference,  shader);
        Parent_Entity = parent;
        Target_Entity= target;
        Endpoint = new SimpleVector(Target_Entity.getPosition());
        Startpoint = new SimpleVector(Parent_Entity.getPosition());

    }


    @Override
    public void Update() {
      super.Update();//eventually need add a seeking like effect to this.

        Endpoint = new SimpleVector(Target_Entity.getPosition());
        Startpoint = new SimpleVector(Parent_Entity.getPosition());

        laserLife--;

        if(laserLife <= 0)
        {
            this.delete();
        }
    }





}
