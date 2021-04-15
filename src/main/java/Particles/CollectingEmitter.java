package Particles;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import Entity_types.BaseEntitys.Entity;

/**
 * Created by Chris on 03/11/2017.
 * A collecting particle emmitter should consist of inward falling particles
 *
 */

public class CollectingEmitter  extends PartEmitter {
    public CollectingEmitter(SimpleVector pos, SimpleVector direction, float w, float h, String textureName, int quantidade, int delay, float speed, float duracao, boolean repeat, float maxscale) {
        super(pos, direction, w, h, textureName, quantidade, delay, speed, duracao, repeat, maxscale);
    }

    public CollectingEmitter(Entity attachentEnt, SimpleVector attachmentPoint, SimpleVector direction, float w, float h, String textureName, int quantidade, int delay, float speed, float duracao, boolean repeat, float maxscale) {
        super(attachentEnt, attachmentPoint, direction, w, h, textureName, quantidade, delay, speed, duracao, repeat, maxscale);
    }

    public CollectingEmitter(SimpleVector pos, SimpleVector direction, float w, float h, Object3D obj, int quantidade, int delay, float speed, float duracao, boolean repeat) {
        super(pos, direction, w, h, obj, quantidade, delay, speed, duracao, repeat);
    }
}
