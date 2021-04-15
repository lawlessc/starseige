package Entity_types.Beams;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;


import Entity_properties.Targetable;
import Entity_types.BaseEntitys.Entity;
import RenderHooks.LaserRenderHook;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by Chris on 24/09/2017.
 */

public
abstract class Beam extends Entity implements Targetable{
       ///This class should NOT be called directly , use as base for others.



    public SimpleVector Startpoint;//Where the laser originates
    public SimpleVector Endpoint;//Where the laser is ending.
    public SimpleVector TargetPoint;//for later, where the laser is trying to move to hitting

   // Entity target;

    public SimpleVector colour;
    public SimpleVector glowColour;

    public SimpleVector direction;//the direction of the beam
    public float distance;//The Length of the beam

    LaserRenderHook renderHook = null;

    public Beam(SimpleVector Startpoint, SimpleVector TargetPoint,Object3D bodyReference, GLSLShader shader) {
          //passing the startpoint here is an error as it messes up the laser object
        super(new BeamMidpoint(Startpoint,TargetPoint), new SimpleVector(0, 0, 0), null, bodyReference);

        Endpoint = TargetPoint;
        this.Startpoint=Startpoint;

        //position is set to midpoint.



        direction = Startpoint.calcSub(Endpoint);
        distance  = direction.length();
        direction= direction.normalize();

        position =new SimpleVector(( this.Startpoint.x +Endpoint.x)*0.5f,
                ( this.Startpoint.y +Endpoint.y)*0.5f,
                ( this.Startpoint.z+Endpoint.z)*0.5f);



        SimpleVector cent= new SimpleVector(0,0,0);
        SimpleVector camdirection = cent.calcSub(direction);
        float x = intersect(SingletonObjects.cam.getPosition() ,camdirection, direction,position );
        camdirection.scalarMul(x);
        camdirection.add(SingletonObjects.cam.getPosition());//just added this
        camdirection.sub(position);

        renderHook = new LaserRenderHook(this, shader);
        body.setShader(shader);
        body.setRenderHook(renderHook);

        body.clearTranslation();
        body.translate(position);//FOR LASERS this is incorrect, must be midpoint
        this.body.setOrientation(direction ,camdirection);
    }



    @Override
    public void Update()
    {
        super.Update();
        //we need to recalculate this every update.
        calculate();
    }

    public void calculate()
    {
        direction = Startpoint.calcSub(Endpoint);
        distance  = direction.length();
        this.position = new SimpleVector((Startpoint.x +Endpoint.x)*0.5f,
                (Startpoint.y +Endpoint.y)*0.5f,
                (Startpoint.z+Endpoint.z)*0.5f);
        direction =direction.normalize();
    }




    @Override
    public void switchToDeathState()
    {

      //  this.Switch_State(EntityStates.explodeState);
   //     this.delete();

    }

    @Override
    public void fire() {
        //does nothing
    }

    //point is where camera is, direction is reversal of plane normal
    public float intersect(SimpleVector point,
                           SimpleVector direction,
                           SimpleVector planeNormal,
                           SimpleVector pointonplane) {


        float denom = planeNormal.calcDot(direction);

        if (Math.abs(denom) > 0.0001f) // your favorite epsilon
        {
            float t = (pointonplane.calcSub(point)).calcDot(planeNormal) / denom;
            if (t >= 0)
                return t; // you might want to allow an epsilon here too
        }


        return 0;
    }



    @Override
    public Boolean isTargetable() {
        return false;
    }

    @Override
    public boolean canFire() {
        return false;
    }

    @Override
    public float firingDistance() {
        return 0;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isEthereal() {
        return true;
    }

    @Override
    public SimpleVector collisionPoint() {
        return Endpoint; //we return the lasters endpoint
    }

    @Override
    public float collisionArea() {
        return 1;
    }

    @Override
    public void collisionEffect(Entity x) {

    }
}
