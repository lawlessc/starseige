package MenuObjects;

import android.view.ScaleGestureDetector;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

import Entity_properties.Touchable;


//A hud object is any ui object, e.g buttons , orbit lines / trajectories, informationals.
public abstract class UIObject implements  Touchable , UIAttachable {
   // public TouchStrategy touchStrategy;


    public int alligned;
    UIAttachable attached;
    public SimpleVector position;//This is only used in Free allignment



    public boolean blink=false;
    public boolean fadeInNow=false;
    public boolean fadeOutNow=false;

    public float transparency=1.0f;//This is used for fade in/out
    public int isThreeD =0;

    public float size;

    public float thickness =0;
    public int glowState;//1 is on 0 is off
    public SimpleVector basecolour;

    public float smoothCenter =0.5f;
    public float glowdistance =1.0f;





    public boolean attachedtoObject= false;
    public boolean die=false;


    public abstract void update();
	public abstract void addTo(World world);
	public abstract void remove();
	public abstract SimpleVector returnPosition();

    public abstract  void setScaleFactor(ScaleGestureDetector m);
    public abstract  void checkifDie();

    public void setIsThreeD(boolean b)
    {
        if(b)
        {
            isThreeD=1;
        }
        else {
            isThreeD=0;
        }

    }

}
