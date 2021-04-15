package MenuObjects;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.threed.jpct.Camera;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;


import baseinterfacesclasses.RayPickPoints;
import baseinterfacesclasses.SingletonObjects;


public abstract class UIButton extends UIObject {
	

	public SpatialGlyph glyph;
	public int disabledFor;

    public boolean disabled;

    public SimpleVector mainColour;
    public SimpleVector pressedColour;


    public UIButton subClass;

    //These are to be use for registering hits.
    public int hitWidth , hitHeight;



	
	//need this for dealing with to many presses

    public UIButton()
    {
        this.glowState = 0;
    }


    public void setHitRange(int x,int y)
    {
        this.hitWidth=x;
        this.hitHeight=y;

    }

    public void disable()
    {
        disabled= true;
        transparency= 0.5f;
    }

    public void enable()
    {
        disabled= false;
        transparency= 1.0f;
    }

    public void setSubClass(UIButton subby)
    {

        subClass=subby;
    }

	
	public void setupUIButton(SpatialGlyph glyph, Camera camera, UIAttachable subject, float offsetRight, float offsetUp,
                              SimpleVector mainColour, SimpleVector pressedColour,float collision_box_size )
	{

        this.alligned= Alignment.AttachedToEntity;
        this.pressedColour=pressedColour;
        this.mainColour   = mainColour;
        this.basecolour  =mainColour;

        attachedtoObject=true;


		this.glyph   = glyph;
        size  = collision_box_size;//glyph.plane.getScale();
		glyph.setAttachmentObject(camera, subject, offsetRight, offsetUp);
		disabledFor = 0;

        fadeInNow = true;
        transparency=0f;
	}




    public void setBilloboard(Boolean xy)
    {
        glyph.plane.setBillboarding(xy);
    }




    public void setupUIButton(SpatialGlyph glyph, Camera camera, float offsetRight, float offsetUp,
                              SimpleVector mainColour, SimpleVector pressedColour, int alignment,float collision_box_size)
    {
        this.alligned = alignment;
        this.pressedColour=pressedColour;
        this.mainColour   = mainColour;
        this.basecolour  =mainColour;
        this.glyph   = glyph;
        this.size  = collision_box_size;//glyph.plane.getScale();

        glyph.setAttachmentCamera(camera, offsetRight, offsetUp);
        disabledFor = 0;

        fadeInNow = true;
        transparency=0f;
    }

    public void setupUIButton(SpatialGlyph glyph, SimpleVector position,
                              SimpleVector mainColour, SimpleVector pressedColour, int alignment,float collision_box_size)
    {
        this.alligned = alignment;
        this.position = position;

        this.mainColour  =mainColour;
        this.basecolour  =mainColour;

        this.pressedColour=pressedColour;

        this.glyph   = glyph;
        this.size  = collision_box_size;//glyph.plane.getScale();

        disabledFor = 0;
        fadeInNow = true;
        transparency=0f;
    }


	public void setAttachment(Camera camera , UIAttachable subject, float offsetright, float offsetup)
	{
		glyph.setAttachmentObject(camera, subject, offsetright, offsetup);
	}

	public void update()
	{

        if(fadeInNow)
        {
            if(transparency >= 1f)
            {
                fadeInNow= false;
            }else
            {
                transparency+=0.01f;
            }

        }

        if(fadeOutNow)
        {
            transparency-=0.01f;
        }

		glyph.update();
        checkifDie();
        if(die)
        {
            die();
        }
       // this.glyph.plane.setOrientation(new SimpleVector(0,1,0),new SimpleVector(1,0,0));
	}

    public void checkifDie()
    {
        if( (attachedtoObject == true)&&(attached==null))
        {
            //allGameObjects.INSTANCE.menumanager.addToRemovalList(this);
            die=true;
        }

    }

    public void die()
    {
        SingletonObjects.menumanager.addToRemovalList(this);
    }
	
	
	@Override
    public void addTo(World world)
    {

       glyph.addToWorld(world);
    }

    @Override
    public void remove()
    {
      glyph.removeFromWorld();
    }

	public SimpleVector returnPosition()
	{
		return glyph.plane.getTranslation();
	}


    public void setVisible()
    {
        glyph.plane.setVisibility(true);
    }

    public void setInvisble()
    {
        glyph.plane.setVisibility(true);
    }


    public  void actionUpFunction()
    {}
    public  void actionDownFunction(RayPickPoints points)
    {}
    public  void actionDoubleTapFunction(RayPickPoints points)
    {}
    public  void actionSingleTapFunction(RayPickPoints points)
    {}
    public  void actionMoveFunction(MotionEvent me, RayPickPoints returnObj)
    {}



    public final void onSingleTap(RayPickPoints points ,MotionEvent me) {

        System.out.println("actiononSingleTap");
        if(disabled == false)
        {
            this.actionSingleTapFunction(points);
        }

    }

    public final void onDoubleTap(RayPickPoints points ,MotionEvent me) {

        if(disabled == false)
        {
            this.actionDoubleTapFunction(points);
        }
    }

    public final void actionDown(RayPickPoints points ,MotionEvent me) {
        if(disabled == false)
        {
            this.basecolour = pressedColour;
            this.glowState =1;
            this.actionDownFunction(points);
        }

    }

    public final void actionUp() {
        this.basecolour = mainColour;
        this.glowState =0;
        if(disabled == false)
        {
            SingletonObjects.soundManagement.playTapButtonUp();
            this.actionUpFunction();
        }
    }

    public final void actionMove(MotionEvent me, RayPickPoints returnObj) {

        if(disabled == false)
        {
            this.glowState =1;
            subClass.actionMoveFunction(me, returnObj);
        }
    }

    @Override
    public void setScaleFactor(ScaleGestureDetector m) {

    }


    @Override
    public SimpleVector objectPosition()
    {
        return new SimpleVector(position);
    }


}
