package Particles;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 03/11/2017.
 *
 * This is to be a single burst particle emmitter, once over it should delete itself.
 */

public class BurstPartEmitter extends PartEmitter {


    public BurstPartEmitter(SimpleVector pos, SimpleVector direction, float w, float h, String textureName, int quantidade, int delay, float speed, float duracao, boolean repeat, float maxscale) {
        super(pos, direction, w, h, textureName, quantidade, delay, speed, duracao, repeat, maxscale);
    }

    public BurstPartEmitter(Entity attachentEnt, SimpleVector attachmentPoint, SimpleVector direction, float w, float h, String textureName, int quantidade, int delay, float speed, float duracao, boolean repeat, float maxscale) {
        super(attachentEnt, attachmentPoint, direction, w, h, textureName, quantidade, delay, speed, duracao, repeat, maxscale);
    }

    public BurstPartEmitter(SimpleVector pos, SimpleVector direction, float w, float h, Object3D obj, int quantidade, int delay, float speed, float duracao, boolean repeat) {
        super(pos, direction, w, h, obj, quantidade, delay, speed, duracao, repeat);
    }
}

