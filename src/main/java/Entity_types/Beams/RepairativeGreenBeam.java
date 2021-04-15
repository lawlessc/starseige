package Entity_types.Beams;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import EffectsOnEntities.RepairEffect;
import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 11/11/2017.
 */

public class RepairativeGreenBeam extends FiringBeam {
    public RepairativeGreenBeam(Entity parent, Entity target, Object3D bodyReference, GLSLShader shader) {
        super(parent, target, bodyReference, shader);

        //Repair laser is green
        this.colour = new SimpleVector(0.2, 1, 0.2);
        this.glowColour = new SimpleVector(0, 1, 0);
    }


    @Override
    public void collisionEffect(Entity x) {

        RepairEffect.INSTANCE.applyEffect(x);

    }
}
