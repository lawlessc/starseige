package Entity_types.Beams

import com.threed.jpct.SimpleVector
/*
*
* All this does is returns a beam at midpoint betweet two beams
* */
class BeamMidpoint(origin : SimpleVector , target : SimpleVector ): SimpleVector() {

    init {

    val one=  SimpleVector((origin.x +target.x)*0.5f,
                (origin.y +target.y)*0.5f,
                (origin.z+target.z)*0.5f);
        this.x = one.x
        this.y=  one.y
        this.x = one.x
    }

}