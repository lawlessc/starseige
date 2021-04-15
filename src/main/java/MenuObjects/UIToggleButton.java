package MenuObjects;

import com.threed.jpct.Camera;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

/**
 * Created by lawless on 16/08/2015.
 */
public class UIToggleButton extends UIButton {

    SpatialGlyph glyph2;
    Boolean toggled;

    public UIToggleButton()
    {}


    public void setupToggleButton(SpatialGlyph glyph,SpatialGlyph glyph2, Camera camera, UIAttachable subject, float offsetRight, float offsetUp,
                            SimpleVector mainColour, SimpleVector pressedColour,Boolean toggled ,float collision_box_size)
    {
        this.pressedColour=pressedColour;
        this.mainColour   = mainColour;
        this.basecolour  =mainColour;
        this.toggled=toggled;

        this.attachedtoObject= true;
        this.glyph   = glyph;
        this.glyph2 = glyph2;
        this.size  = collision_box_size;//glyph.plane.getScale();
        this.glyph.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph2.setAttachmentObject(camera, subject, offsetRight, offsetUp);

        this.disabledFor = 0;
        setToggle();


        fadeInNow = true;
        transparency=0f;
    }


    public void setupToggleButton(SpatialGlyph glyph,SpatialGlyph glyph2,Camera camera, float offsetRight, float offsetUp,
                               SimpleVector mainColour, SimpleVector pressedColour,Boolean toggled,int alligned ,float collision_box_size)
    {
        this.pressedColour=pressedColour;
        this.mainColour   = mainColour;
        this.basecolour  =mainColour;
        this.toggled=toggled;
        this.glyph  = glyph;
        this.glyph2 = glyph2;
        this.size  = collision_box_size;
        this.glyph.setAttachmentCamera(camera,offsetRight, offsetUp);
        this.glyph2.setAttachmentCamera(camera, offsetRight, offsetUp);

        this.disabledFor = 0;
        this.alligned = alligned;

        setToggle();

        fadeInNow = true;
        transparency=0f;
    }


    public void setBilloboard(Boolean xy)
    {
        glyph.plane.setBillboarding(xy);
        glyph2.plane.setBillboarding(xy);
    }

    public void Toggle()
    {
        if(toggled)
        {
            toggled = true;
        }
        else
        {
            toggled = false;
        }
        glyph.plane.setVisibility(toggled);
        glyph2.plane.setVisibility(!toggled);
    }


    public void setToggle(Boolean toggle) {
        this.toggled = toggle;
        glyph.plane.setVisibility(toggled);
        glyph2.plane.setVisibility(!toggled);
    }



    private void setToggle() {
    glyph.plane.setVisibility(toggled);
    glyph2.plane.setVisibility(!toggled);
    }


    @Override
    public void remove()
    {
        glyph.removeFromWorld();
        glyph2.removeFromWorld();
    }

    @Override
    public void addTo(World world)
    {
      glyph.addToWorld(world);
      glyph2.addToWorld(world);

    }

    @Override
    public void update()
    {
        super.update();
        glyph.update();
        glyph2.update();
        checkifDie();
    }

}
