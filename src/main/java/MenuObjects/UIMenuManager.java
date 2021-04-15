package MenuObjects;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Polyline;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import menusViews.EntityMenu;

public class UIMenuManager implements Observer //basically for UI and information.
{
	    public World world;
        private World lineWorld;// A world UI for lines. So i can blit them to keep the transparent.
        int arrayListsize = 10; //There really should be no need to have this much on screen most of the time

    public EntityMenu currentMenu;
    public EntityMenu borderMenu;

    //This will be whatever menu is open. when a new menus is opened the current one must be replaces
    // This may be possible with a super function.

    public ArrayList<UIMenuGroup> hudGroups = new ArrayList<UIMenuGroup>(arrayListsize);

    public ArrayList<UIObject> menustack = new ArrayList<UIObject>(arrayListsize);
    public ArrayList<UIObject> new_additions_list = new ArrayList<UIObject>(arrayListsize);
    public ArrayList<UIObject> removals_list =  new ArrayList<UIObject>(arrayListsize);

    public ArrayList<Polyline> linestack = new ArrayList<Polyline>(arrayListsize);
    public ArrayList<Polyline> new_lines_list = new ArrayList<Polyline>(arrayListsize);
    public ArrayList<Polyline> removal_lines =  new ArrayList<Polyline>(arrayListsize);

        Boolean clearAtNext = false; //
        Camera cam ;
        Camera uicam;

        //These are points relative to the Camera.
        public float centerDistance;
        public float distanceFromCentreToMidpoint;
        public float distanceFromMidpointToCorners;

        public float screenWidth;
        public float screenHeight;


       SimpleVector topLeft,       topRight,

                           center,

                    bottomLeft,   bottomRight;

        
        public UIMenuManager(Camera camera, int screenWidth, int screenHeight, FrameBuffer fb)
        {
            this.world = new World();
            this.cam = camera;
            uicam = this.world.getCamera();
            setCornerPositions(fb,screenWidth,screenHeight);
        }



    public void setCurrentMenu(EntityMenu menu)
    {
        if(currentMenu != null)
        {
            currentMenu.closeMenu();

        }
        currentMenu= menu;

    }


    public void setCornerPositions(FrameBuffer fb ,int screenWidth, int screenHeight)
    {

        SimpleVector TopLeft=  Interact2D.reproject2D3DWS(cam, fb, 0, 0,1.5f);
        SimpleVector TopRight=  Interact2D.reproject2D3DWS(cam, fb, screenWidth, 0,1.5f);
        SimpleVector BottomLeft = Interact2D.reproject2D3DWS(cam, fb, 0, screenHeight,1.5f);
        SimpleVector BottomRight = Interact2D.reproject2D3DWS(cam, fb, screenWidth, screenHeight,1.5f);

        TopLeft.add(cam.getPosition());
        TopRight.add(cam.getPosition());
        BottomLeft.add(cam.getPosition());
        BottomRight.add(cam.getPosition());


        SimpleVector midpointTop = midpoint(TopLeft, TopRight);
        SimpleVector midpointBottom = midpoint(BottomLeft, BottomRight);
        SimpleVector Center = midpoint(midpointTop, midpointBottom);

        this.distanceFromCentreToMidpoint = Center.distance(midpointTop);
        this.distanceFromMidpointToCorners = midpointTop.distance(TopLeft);
        centerDistance = Center.distance(cam.getPosition());
        this.screenWidth = TopLeft.distance(TopRight);
        this.screenHeight = TopLeft.distance(BottomLeft);

    }


    public SimpleVector midpoint(SimpleVector one, SimpleVector two)
    {
        float rx = (one.x+two.x)/2.0f;
        float ry = (one.y+two.y)/2.0f;
        float rz = (one.z+two.z)/2.0f;
        return new SimpleVector(rx,ry,rz);
    }


    public void UpdateCorners()
    {
     SimpleVector camdir  =   cam.getDirection();
     SimpleVector upDir   =   cam.getUpVector();
     SimpleVector sideDir =   cam.getSideVector();


     camdir.scalarMul(centerDistance);
     center = camdir.calcAdd(cam.getPosition());

     upDir.scalarMul(distanceFromCentreToMidpoint);
     sideDir.scalarMul(distanceFromMidpointToCorners);

     SimpleVector midpointTop = center.calcAdd(upDir);
     SimpleVector midpointBottom = center.calcSub(upDir);


     topLeft = midpointTop.calcSub(sideDir);
     bottomLeft = midpointBottom.calcSub(sideDir);


     topRight= midpointTop.calcAdd(sideDir);
     bottomRight = midpointBottom.calcAdd(sideDir);

    }

    public SimpleVector getTopLeft(){return new SimpleVector(topLeft);}
    public SimpleVector getTopRight(){return  new SimpleVector(topRight);}
    public SimpleVector getBottomLeft()
    {
        return new SimpleVector( bottomLeft);
    }
    public SimpleVector getBottomRight()
    {
        return new SimpleVector( bottomRight);
    }

    public SimpleVector getCenter(){return center;}


        public void update()
        {

             if(currentMenu != null) {
                 currentMenu.update();
             }

            if(clearAtNext)
            {
                clearMenu();
                world.removeAllPolylines();
                clearAtNext=false;
            }


            if(removals_list.size() >0)
            {

              //  int index = removals_list.size();
                for(int i =0 ; i < removals_list.size() ; i++)
                {
                   // UIObject torem = removals_list.elementAt(i);
                    UIObject torem = removals_list.get(i);
                    removals_list.remove(torem);
                    removeHudObject(torem);

                }

            }

            if(removal_lines.size() >0)
            {

                //  int index = removals_list.size();
                for(int i =0 ; i < removal_lines.size() ; i++)
                {
                    Polyline lin = removal_lines.get(i);
                    removal_lines.remove(lin);


                    removeLine(lin);

                }

            }
        	
        	int index = menustack.size();
        	for(int i =0 ; i < index ; i++)
        	{
                menustack.get(i).update();
        	}

            addButtonsToWorld();
            addLinesToWorld();

        }


    private void removeHudObject(UIObject hh){
        menustack.remove(hh);
        hh.remove();
    }

    private void removeLine(Polyline l){
        linestack.remove(l);
        world.removePolyline(l);
    }


    public void clearMenu(){

        int index = menustack.size();

        for(int i =0 ; i < index ; i++){

            UIObject cur = menustack.get(i);

            if(removals_list.contains(cur))
            {
                removals_list.remove(cur);
            }


            cur.remove();



        }

        menustack.clear();


        int index2 = linestack.size();
        for(int i =0 ; i < index2 ; i++){
            Polyline line = linestack.get(i);
            if(removals_list.contains(line))
            {
                removals_list.remove(line);
            }

            world.removePolyline(line);
        }
        linestack.clear();
    }


       public void cameraUpdate()
       {
           UpdateCorners();
           int index = menustack.size();
           for(int i =0 ; i < index ; i++)
           {
               menustack.get(i).update();
           }
           uicam.setPosition(cam.getPosition());
           uicam.setOrientation(cam.getDirection(), cam.getUpVector());
       }


     public void uiRender(FrameBuffer fb)
     {
         world.renderScene(fb);
     }

    public void uiDraw(FrameBuffer fb)
    {
        world.draw(fb);
    }

        //We add new button Object3D's to the world 
        public void addButtonsToWorld()
        {
        	
             int index = new_additions_list.size();
        	
        	for(int i = 0 ; i < index ; i++)
        	{  
        		
        		if(!menustack.contains(new_additions_list.get(i) ))
        		{
        		menustack.add(new_additions_list.get(i));
        		
        	//	world.addObject(new_additions_list.elementAt(i).glyph.plane);
        		new_additions_list.get(i).addTo(world);
        		}
        	}
        	
        	
        	new_additions_list.clear();
        }

    public void addLinesToWorld()
    {

        int index = new_lines_list.size();

        for(int i = 0 ; i < index ; i++)
        {

            if(!linestack.contains(new_lines_list.get(i)))
            {
                linestack.add(new_lines_list.get(i));

                	world.addPolyline(new_lines_list.get(i));
                //  new_lines_list.elementAt(i).addTo(world);
            }
        }


        new_lines_list.clear();
    }


    public void addToRemovalList(UIObject h) {
        if (!removals_list.contains(h)) {
            removals_list.add(h);
        }
    }
    public void addToRemovalList(Polyline p)
    {
        if (!removal_lines.contains(p)) {
            removal_lines.add(p);
        }
    }




   public void addButton(UIButton button)
       {
    	   new_additions_list.add(button);
       }
    public void addLine(Polyline line)
    {
        new_lines_list.add(line);
    }



    public void clearAtnext()
    {
        clearAtNext =	true;
    }



    public void removeHudGroup(UIMenuGroup bg)
    {
        for(int i = 0 ; i < bg.hudGroup.size() ; i ++)
        {
            removals_list.add( bg.hudGroup.elementAt(i));
        }
        hudGroups.remove(bg);
    }

	@Override
	public void update(Observable observable, Object data) {
		
       if (data instanceof UIObject) {
           UIObject newoneHudObject = (UIObject) data;
           new_additions_list.add(newoneHudObject);
       }
        if(data instanceof UIMenuGroup)
        {

            UIMenuGroup UIMenuGroup = (UIMenuGroup) data;
            for(int i = 0 ; i < UIMenuGroup.hudGroup.size() ; i ++)
            {
                new_additions_list.add( UIMenuGroup.hudGroup.elementAt(i));
            }
            hudGroups.add(UIMenuGroup);
        }
	}

}