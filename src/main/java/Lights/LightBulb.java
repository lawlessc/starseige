package Lights;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import java.util.Observable;
import java.util.Observer;

import Entity_types.BaseEntitys.Entity;
import RenderHooks.LightBulbRenderHook;
import states.State;

/**
 * Created by Chris on 13/05/2016.
 */
public class LightBulb extends Entity implements Observer{

    SimpleVector attachmentPoint =null;

    GLSLShader shader = null;
    LightBulbRenderHook renderHook = null;
    public SimpleVector colour= new SimpleVector(1,1,0);
    public SimpleVector glowColour= new SimpleVector(1,1,0);

    public LightBulb(SimpleVector pos, SimpleVector moment,
                     State state,
                     Object3D bodyReference ,
                     SimpleVector attachmentPoint ,Entity parent,
                     GLSLShader shader) {

        super(pos, moment, state, bodyReference);

        renderHook = new LightBulbRenderHook(this, shader);

        this.body.setVisibility(true);
        this.body.setTransparency(200);

        this.Parent_Entity =parent;
        this.attachmentPoint=attachmentPoint;

        parent.addObserver(this);

        body.setOrigin(attachmentPoint);

        body.setShader(shader);
        body.setRenderHook(renderHook);
        body.addParent(parent.body);


        this.shader=shader;
    }

    public void setColour(SimpleVector colour) {
        this.colour = colour;
    }

    public void setGlowColour(SimpleVector glowColour) {
        this.glowColour = glowColour;
    }

    @Override
    public void Update()
    {
//        body.clearTranslation();
//        body.translate(Parent_Entity.body.getTranslation());
//
//      body.  setScale(1f);
//       body.setTranslationMatrix ( Parent_Entity.body.getTranslationMatrix());

      //  body.setOrigin(attachmentPoint.getOrigin());


    }

    @Override
    public void fire() {

    }


    @Override
    public void update(Observable observable, Object data) {


        delete();
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
    public Boolean isTargetable() {
        return null;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean isEthereal() {
        return true;
    }

    @Override
    public SimpleVector collisionPoint() {
        return null;
    }

    @Override
    public float collisionArea() {
        return 0;
    }

    @Override
    public void collisionEffect(Entity x) {

    }
}
