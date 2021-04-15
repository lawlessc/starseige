package MenuObjects;

import android.content.res.Resources;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;


import java.util.Observable;

import Entity_types.BaseEntitys.Entity;
import baseinterfacesclasses.SingletonObjects;


//Make the HUDMenuManager the observer
//HUDObjectFactory functions should be called by MenuNotificationObserver mostly when an object is selected
public class UIObjectFactory extends Observable {

	UIObjectGFX objectgraphics;
	int ScreenWidth;
	int ScreenHeight;

	public UIObjectFactory(Resources res)
	{
		objectgraphics = new UIObjectGFX(res);
	}

	public void createGlyphButton(UIButton button , UIAttachable subject,
								  SimpleVector colour,
								  SimpleVector colour2,
								  Object3D object3d,
								  float offsetright,
								  float offsetup,

								  float scale
			                     ,float collision_box_size
	)
	{
		button.setupUIButton(objectgraphics.createGlyphObj3d(button , object3d , scale),
				SingletonObjects.cam, subject, offsetright, offsetup, colour, colour2,
				collision_box_size);
		sendToObserver(button);
		button.setSubClass(button);

	}

	public void createGlyphButton(UIButton button , UIAttachable subject,
								  SimpleVector colour,
								  SimpleVector colour2,
								  String texturename,
								  float offsetright,
								  float offsetup,

								  float scale
			,float collision_box_size)
	{
		button.setupUIButton(objectgraphics.createGlyph(button, texturename, scale),
				SingletonObjects.cam, subject, offsetright, offsetup, colour, colour2,
				collision_box_size);

		sendToObserver(button);
		button.setSubClass(button);
	}
	public void createGlyphButton(UIButton button,
								  SimpleVector colour,
								  SimpleVector colour2,
								  String texturename ,
								  float offsetright,
								  float offsetup,
								  int alignment,
								  float scale
			,float collision_box_size)
	{

		button.setupUIButton(objectgraphics.createGlyph(button, texturename, scale),
				SingletonObjects.cam,
				offsetright,
				offsetup, colour, colour2, alignment,
				collision_box_size);

		sendToObserver(button);
		button.setSubClass(button);
	}


	public void createPositionInSpaceButton(UIButton button,
								  SimpleVector colour,
								  SimpleVector colour2,
								  String texturename ,
								  SimpleVector position,
								  float scale
			,float collision_box_size

	)
	{

		button.setupUIButton(objectgraphics.createGlyph(button, texturename, scale),
				position,
				colour, colour2,
				Alignment.Free,
				collision_box_size);

		sendToObserver(button);
		button.setSubClass(button);
	}



	public void createPositionInSpaceButton(UIButton button,
											SimpleVector colour,
											SimpleVector colour2,
											Object3D obj3d ,
											SimpleVector position,
											float scale
			,float collision_box_size

	)
	{

		button.setupUIButton(objectgraphics.createGlyphObj3d(button, obj3d, scale),
				position,
				colour, colour2,
				Alignment.Free,
				collision_box_size);

		button.setIsThreeD(true);
		sendToObserver(button);
		button.setSubClass(button);
	}





	public void createPucButton(UIPuc button,
										   SimpleVector colour,
										   SimpleVector colour2,
										   String texturename ,
										   SimpleVector position,
										   float scale
			,float collision_box_size

	)
	{

		button.setupPUC(objectgraphics.createGlyph(button, texturename, scale),position,
				colour, colour2,
				collision_box_size);

		sendToObserver(button);
		button.setSubClass(button);
	}








	public void createGlyphToggleButton(UIToggleButton button , Entity subject,
								  SimpleVector colour,
								  SimpleVector colour2,
								  String texturename,
								  String texturename2,
								  float offsetright,
								  float offsetup,
								  Boolean toggle,
								  float scale
			,float collision_box_size
	)
	{
		button.setupToggleButton(objectgraphics.createGlyph(button, texturename,scale),
				                 objectgraphics.createGlyph(button, texturename2,scale),
				                 SingletonObjects.cam,
				                 subject,
				                 offsetright,
				                 offsetup, colour, colour2,toggle,
				collision_box_size);
		sendToObserver(button);
		button.setSubClass(button);
	}

	public void createGlyphToggleButton(UIToggleButton button,
								  SimpleVector colour,
								  SimpleVector colour2,
								  String texturename ,
								  String texturename2 ,
								  float offsetright,
								  float offsetup,
								  Boolean toggle,
								  int	alignment,
								  float scale
			,float collision_box_size
	)
	{

		button.setupToggleButton(objectgraphics.createGlyph(button,texturename,scale),
				                 objectgraphics.createGlyph(button,texturename2,scale),
				                 SingletonObjects.cam,
				                 offsetright,
				                 offsetup,
				                 colour,
				                 colour2,
				                 toggle,
			                     alignment,
				collision_box_size
		);

		sendToObserver(button);
		button.setSubClass(button);
	}

	public void createNumberGlyph(UINumberToggle button,
										SimpleVector colour,
										SimpleVector colour2,
										String texturename ,
							        	String texturename1 ,
								        String texturename2 ,
								        String texturename3 ,
								        String texturename4 ,
								        String texturename5,
								        String texturename6 ,
								        String texturename7 ,
								        String texturename8 ,
								        String texturename9 ,
			                            Entity subject,
										float offsetright,
										float offsetup,
										//Alignment	alignment,
										float scale)
	{

		button.setupNumber(
				objectgraphics.createGlyph(button,texturename,scale),
				objectgraphics.createGlyph(button,texturename1,scale),
				objectgraphics.createGlyph(button,texturename2,scale),
				objectgraphics.createGlyph(button,texturename3,scale),
				objectgraphics.createGlyph(button,texturename4,scale),
				objectgraphics.createGlyph(button,texturename5,scale),
				objectgraphics.createGlyph(button,texturename6,scale),
				objectgraphics.createGlyph(button,texturename7,scale),
				objectgraphics.createGlyph(button,texturename8,scale),
				objectgraphics.createGlyph(button,texturename9,scale),
				SingletonObjects.cam,
				subject,
				offsetright,
				offsetup,
				colour,
				colour2
		);

		sendToObserver(button);
		button.setSubClass(button);
	}

	public void createNumberGlyph(UINumberToggle button,
								  SimpleVector colour,
								  SimpleVector colour2,
								  String texturename ,
								  String texturename1 ,
								  String texturename2 ,
								  String texturename3 ,
								  String texturename4 ,
								  String texturename5,
								  String texturename6 ,
								  String texturename7 ,
								  String texturename8 ,
								  String texturename9 ,
								  float offsetright,
								  float offsetup,
								  int	alignment,
								  float scale)
	{

		button.setupNumber(
				objectgraphics.createGlyph(button,texturename,scale),
				objectgraphics.createGlyph(button,texturename1,scale),
				objectgraphics.createGlyph(button,texturename2,scale),
				objectgraphics.createGlyph(button,texturename3,scale),
				objectgraphics.createGlyph(button,texturename4,scale),
				objectgraphics.createGlyph(button,texturename5,scale),
				objectgraphics.createGlyph(button,texturename6,scale),
				objectgraphics.createGlyph(button,texturename7,scale),
				objectgraphics.createGlyph(button,texturename8,scale),
				objectgraphics.createGlyph(button,texturename9,scale),
				SingletonObjects.cam,
				offsetright,
				offsetup,
				colour,
				colour2,
				alignment
		);

		sendToObserver(button);
		button.setSubClass(button);
	}






	public   void sendToObserver(UIObject obj)
	{
	   setChanged();
	   notifyObservers(obj);
	}
	
	
	public static Object3D copy(Object3D bluePrint) {
		Object3D copy=new Object3D(bluePrint, true);
		copy.shareCompiledData(bluePrint);
		copy.shareTextureData(bluePrint);
		
		return copy;
	}
	
	


}
