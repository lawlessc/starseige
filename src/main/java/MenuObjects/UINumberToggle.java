package MenuObjects;

import com.threed.jpct.Camera;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

/**
 * Created by Chris on 18/09/2017.
 */

public class UINumberToggle extends UIButton {

    //Zero is default and not covered
    SpatialGlyph glyph1;
    SpatialGlyph glyph2;
    SpatialGlyph glyph3;
    SpatialGlyph glyph4;
    SpatialGlyph glyph5;
    SpatialGlyph glyph6;
    SpatialGlyph glyph7;
    SpatialGlyph glyph8;
    SpatialGlyph glyph9;

    int default_num=0;

    public UINumberToggle()
    {}


    public void setupNumber(SpatialGlyph glyph0, SpatialGlyph glyph1,
                            SpatialGlyph glyph2, SpatialGlyph glyph3,
                            SpatialGlyph glyph4, SpatialGlyph glyph5,
                            SpatialGlyph glyph6, SpatialGlyph glyph7,
                            SpatialGlyph glyph8, SpatialGlyph glyph9,
                            Camera camera, UIAttachable subject, float offsetRight, float offsetUp,
                                  SimpleVector mainColour, SimpleVector pressedColour)
    {
        this.pressedColour=pressedColour;
        this.mainColour   = mainColour;
        this.basecolour  =mainColour;


        this.attachedtoObject= true;
        this.glyph   = glyph0;
        this.glyph1 = glyph1;
        this.glyph2   = glyph2;
        this.glyph3 = glyph3;
        this.glyph4   = glyph4;
        this.glyph5 = glyph5;
        this.glyph6   = glyph6;
        this.glyph7 = glyph7;
        this.glyph8   = glyph8;
        this.glyph9 = glyph9;


        this.size  = 0.3f;//glyph.plane.getScale();
        this.glyph.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph1.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph2.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph3.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph3.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph4.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph5.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph6.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph7.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph8.setAttachmentObject(camera, subject, offsetRight, offsetUp);
        this.glyph9.setAttachmentObject(camera, subject, offsetRight, offsetUp);

        this.disabledFor = 0;

        disabled=true;//we disable this to prevent reactions to button presses

        fadeInNow = true;
        transparency=0f;
        setNumberVisibilty(default_num);
    }


    public void setupNumber(SpatialGlyph glyph0, SpatialGlyph glyph1,
                            SpatialGlyph glyph2, SpatialGlyph glyph3,
                            SpatialGlyph glyph4, SpatialGlyph glyph5,
                            SpatialGlyph glyph6, SpatialGlyph glyph7,
                            SpatialGlyph glyph8, SpatialGlyph glyph9,
                            Camera camera, float offsetRight, float offsetUp,
                            SimpleVector mainColour,  SimpleVector pressedColour,int	alignment)
    {
        this.pressedColour=pressedColour;
        this.mainColour   = mainColour;
        this.basecolour  =mainColour;


        this.attachedtoObject= false;
        this.glyph   = glyph0;
        this.glyph1 = glyph1;
        this.glyph2   = glyph2;
        this.glyph3 = glyph3;
        this.glyph4   = glyph4;
        this.glyph5 = glyph5;
        this.glyph6   = glyph6;
        this.glyph7 = glyph7;
        this.glyph8   = glyph8;
        this.glyph9 = glyph9;


        this.size  = 0.3f;//glyph.plane.getScale();
        this.glyph.setAttachmentCamera(camera,offsetRight, offsetUp);
        this.glyph1.setAttachmentCamera(camera, offsetRight, offsetUp);
        this.glyph2.setAttachmentCamera(camera,offsetRight, offsetUp);
        this.glyph3.setAttachmentCamera(camera, offsetRight, offsetUp);
        this.glyph4.setAttachmentCamera(camera,offsetRight, offsetUp);
        this.glyph5.setAttachmentCamera(camera, offsetRight, offsetUp);
        this.glyph6.setAttachmentCamera(camera,offsetRight, offsetUp);
        this.glyph7.setAttachmentCamera(camera, offsetRight, offsetUp);
        this.glyph8.setAttachmentCamera(camera,offsetRight, offsetUp);
        this.glyph9.setAttachmentCamera(camera, offsetRight, offsetUp);

        this.disabledFor = 0;
        this.alligned = alignment;

        disabled=true;//we disable this to prevent reactions to button presses

        fadeInNow = true;
        transparency=0f;
        setNumberVisibilty(default_num);
    }


    public boolean setNumberVisibilty(int y)
    {
        if(y > 9)
        {
            return false;
        }

        setAllInvisible();

        switch (y) {

            case 0:
                this.glyph.plane.setVisibility(true);
                break;
            case 1:
                this.glyph1.plane.setVisibility(true);
                break;
            case 2:
                this.glyph2.plane.setVisibility(true);
                break;
            case 3:
                this.glyph3.plane.setVisibility(true);
                break;
            case 4:
                this.glyph4.plane.setVisibility(true);
                break;
            case 5:
                this.glyph5.plane.setVisibility(true);
                break;
            case 6:
                this.glyph6.plane.setVisibility(true);
                break;
            case 7:
                this.glyph7.plane.setVisibility(true);
                break;
            case 8:
                this.glyph8.plane.setVisibility(true);
                break;
            case 9:
                this.glyph9.plane.setVisibility(true);
                break;


            }








        return true;
    }

    public void setAllInvisible()
    {

        this.glyph.plane.setVisibility(false);
        this.glyph1.plane.setVisibility(false);
        this.glyph2.plane.setVisibility(false);
        this.glyph3.plane.setVisibility(false);
        this.glyph4.plane.setVisibility(false);
        this.glyph5.plane.setVisibility(false);
        this.glyph6.plane.setVisibility(false);
        this.glyph7.plane.setVisibility(false);
        this.glyph8.plane.setVisibility(false);
        this.glyph9.plane.setVisibility(false);


    }


    @Override
    public void remove()
    {
        glyph.removeFromWorld();
        glyph1.removeFromWorld();
        glyph2.removeFromWorld();
        glyph3.removeFromWorld();
        glyph4.removeFromWorld();
        glyph5.removeFromWorld();
        glyph6.removeFromWorld();
        glyph7.removeFromWorld();
        glyph8.removeFromWorld();
        glyph9.removeFromWorld();
    }

    @Override
    public void addTo(World world)
    {
        glyph.addToWorld(world);
        glyph1.addToWorld(world);
        glyph2.addToWorld(world);
        glyph3.addToWorld(world);
        glyph4.addToWorld(world);
        glyph5.addToWorld(world);
        glyph6.addToWorld(world);
        glyph7.addToWorld(world);
        glyph8.addToWorld(world);
        glyph9.addToWorld(world);

    }

    @Override
    public void update()
    {
        super.update();
        glyph.update();
        glyph1.update();
        glyph2.update();
        glyph3.update();
        glyph4.update();
        glyph5.update();
        glyph6.update();
        glyph7.update();
        glyph8.update();
        glyph9.update();
        checkifDie();
    }

    public void setBilloboard(Boolean xy)
    {
        glyph.plane.setBillboarding(xy);
        glyph1.plane.setBillboarding(xy);
        glyph2.plane.setBillboarding(xy);
        glyph3.plane.setBillboarding(xy);
        glyph4.plane.setBillboarding(xy);
        glyph5.plane.setBillboarding(xy);
        glyph6.plane.setBillboarding(xy);
        glyph7.plane.setBillboarding(xy);
        glyph8.plane.setBillboarding(xy);
        glyph9.plane.setBillboarding(xy);
    }






}
