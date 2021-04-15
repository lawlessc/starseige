package com.threed.jpct.shader;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

import com.threed.jpct.Config;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.Texture;
import com.threed.jpct.util.AAConfigChooser;
import com.threed.jpct.util.MemoryHelper;

import java.lang.reflect.Field;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import EventManagers.GameSetup;
import baseinterfacesclasses.SingletonObjects;

;

/**
 * @author Christopher Lawless
 */
public class Main extends Activity implements OnScaleGestureListener {
	// Used to handle pause and resume...
	private static Main master = null;
	private GLSurfaceView mGLView;
	private MyRenderer renderer = null;
	private FrameBuffer fb = null;


	int frameRate= 60;
	public int framelength;

	public static SingletonObjects allthing;

	long MS_PER_UPDATE =16;
	
	public long previous;
	public long current;
	public long lag;
	public long elapsed;
	public long timeSinceLastFrame=0;



    public UpdateLoop updateLoop = new UpdateLoop();

	private ScaleGestureDetector mScaleDetector;
	private GestureDetector tapdetection;

	private Texture font = null;
	private Boolean texturesLoaded= false;
	//////////////////////////////////This needs to be seperated from the graphical stuff above somehow.


	public Physics physics;




	protected void onCreate(Bundle savedInstanceState) {
		Logger.log("onCreate");
		allthing = new SingletonObjects();
		//Logger.setLogLevel(Logger.LL_DEBUG);
		//Logger.setLogLevel(Logger.);
		//Logger.setLogLevel(Logger.LL_VERBOSE);


		//Context baseContext= this.getBaseContext();
		Resources res = getResources();


		if (master != null) {
			copy(master);
		}

		super.onCreate(savedInstanceState);
		SingletonObjects.cameraCursor = new CameraCursor();


		if(master == null)
		{
			//This Sets Renderer , I may want to seperate these classes!
			setContentView(R.layout.experimental); //or whatever the layout you want to use
			mGLView = (GLSurfaceView) findViewById(R.id.graphics_glsurfaceview1);
			SingletonObjects.gameeventmanager = new GameSetup();

			SingletonObjects.vibrationEvents =  new GoodVibrations((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));

			//mGLView = new GLSurfaceView(getApplication());
			// Enable the OpenGL ES2.0 context
			mGLView.setEGLContextClientVersion(2);
			//
			mGLView.setEGLConfigChooser(new AAConfigChooser(mGLView));

			renderer = new MyRenderer();
			mGLView.setRenderer(renderer);

			Texture.defaultToMipmapping(true);
			Texture.defaultTo4bpp(true);
			Texture.defaultToKeepPixels(true);
			Config.maxTextureLayers = 4;
			Config.maxPolysVisible = 5000;
			Config.farPlane = 10500;

			//MY GOD I WISH I KNEW ABOUT THIS SOONER
			mGLView.setPreserveEGLContextOnPause(true);
		//mGLView.setPreserveEGLContextOnPause(true);
		}

		
		if (!texturesLoaded)
		{
		Context baseContext= this.getBaseContext();
	    SingletonObjects.gameeventmanager.texturesLoaded(baseContext);

		texturesLoaded=true;
		}

		physics = new Physics();
		SingletonObjects.colldec = new CollisionDetection();


		mScaleDetector  = new ScaleGestureDetector(this , new ScaleListener());
		tapdetection    = new GestureDetector(this, new TapListener());
       // master = this;


		framelength = 1000/ frameRate;
	}








	@Override
	protected void onPause() {
		SingletonObjects.isActionPaused=true;
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
//		if(SingletonObjects.isActionPaused)
//		{
//			copy(master);
//		}
		super.onResume();
		mGLView.onResume();
	}

	@Override
	protected void onStop() {
		Logger.log("onStop");
		super.onStop();
	}

	private void copy(Object src) {
		try {
			Logger.log("Copying data from master Activity!");
			Field[] fs = src.getClass().getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				f.set(this, f.get(src));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//IMPORTANT
	public boolean onTouchEvent(MotionEvent me) {
		mScaleDetector.onTouchEvent(me);
		tapdetection.onTouchEvent(me);
        int left = mGLView.getLeft();
        int top  =mGLView.getTop();

	
		if (me.getAction() == MotionEvent.ACTION_DOWN) {
		 if(SingletonObjects.menumanager != null) {
			 SingletonObjects.cameraCursor.actionDown(me, fb, left, top);
		 }
			return true;
		}
		if (me.getAction() == MotionEvent.ACTION_UP) {
			if(SingletonObjects.menumanager != null) {
				SingletonObjects.cameraCursor.actionUp(me, fb);
			}
			return true;
		}
		if (me.getAction() == MotionEvent.ACTION_MOVE) {
			if(SingletonObjects.menumanager != null) {
				SingletonObjects.cameraCursor.actionMove(me, fb, left, top);
			}
			return true;
		}

		try { //TODO why is this here?
			Thread.sleep(15);
		} catch (Exception e) {
		}
		return super.onTouchEvent(me);
	}



	class MyRenderer implements GLSurfaceView.Renderer {
		private int fps = 0;
		private int lfps = 0;
		private long time = System.currentTimeMillis();



		public MyRenderer() {
            Resources res = getResources();

			//time = System.currentTimeMillis();
			font = new Texture(res.openRawResource(R.raw.numbers));
			font.setMipmap(false);	
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {

			if (fb != null) {
				fb.resize(w,h);
			}
				fb = new FrameBuffer(w, h);


			if (master == null) {
				//System.out.println("MASTER IS NULLLLL");
				master = Main.this;


				Resources res = getResources();
				SingletonObjects.gameeventmanager.mainSetup(res,w,h,fb);

			   
			   current = System.currentTimeMillis();
			   lag = 0;
			   previous = System.currentTimeMillis();

				SingletonObjects.gameeventmanager.mainMenu();



				MemoryHelper.compact();
				SingletonObjects.world.compileAllObjects();

			}
			SingletonObjects.menumanager.setCornerPositions(fb,w,h);
		}


		public void onSurfaceCreated(GL10 gl, EGLConfig config) {

			Logger.log("onSurfaceCreated");
		}


		public void onDrawFrame(GL10 gl) {

			SingletonObjects.cameraCursor.setOrbit();
 			SingletonObjects.menumanager.update(); // updates ui

			if(!SingletonObjects.isActionPaused)
			{
				current = System.currentTimeMillis();
				elapsed = current - previous;
				SingletonObjects.runningTime+= elapsed;
				SingletonObjects.runningTimeSeconds = (int) (SingletonObjects.runningTime* 0.001) ;
				previous = current;
				lag += elapsed;


				while (lag >= MS_PER_UPDATE){
					SingletonObjects.factoryObserver.additions();
					physics.update(lag);
					updateLoop.updateEntities((lag)/1000.0f);
					SingletonObjects.eventCompleter.update();
					SingletonObjects.player_money.update();
					SingletonObjects.colldec.collisionDetection();
                    //CollisionDetectionTwoKt.collisionDetectionTwo();
					//CollisionDetectionThree
					lag -= MS_PER_UPDATE;
				}
			}
			else
			{
				
			 lag = 0;
			 previous= System.currentTimeMillis();
			 current =System.currentTimeMillis();
			}
			SingletonObjects.factoryObserver.handleRemovals();
			SingletonObjects.particleManager.doRemovals();

			SingletonObjects.skysphere.Update();

			SingletonObjects.cameraCursor.cameraFollow();
			SingletonObjects.menumanager.cameraUpdate();



			try {
				Thread.sleep(framelength);
			}
			catch (InterruptedException ie) {}

			
			//if(doDraw()) {

				SingletonObjects.processHandler.doPostProcess(fb);
				fb.display();
			//}




			blitNumber(lfps, 5, 5);

			blitNumber(SingletonObjects.player_money.wallamount(), 100, 5);//wallet contents

			if (System.currentTimeMillis() - time >= 1000) {
				lfps = fps;
				fps = 0;
				time = System.currentTimeMillis();
			}
			fps++;
		}


		public boolean doDraw()
		{
			timeSinceLastFrame += elapsed;
			if(timeSinceLastFrame >= framelength)
			{
				timeSinceLastFrame=0;
				return true;
			}
			return false;
		}




		private void blitNumber(int number, int x, int y) {
			if (font != null) {
				String sNum = Integer.toString(number);

				for (int i = 0; i < sNum.length(); i++) {
					char cNum = sNum.charAt(i);
					int iNum = cNum - 48;
					fb.blit(font, iNum * 5, 0, x, y, 5, 9, 5, 9, 10, true, null);
					
					x += 5;
				}
			}
		}
	}


	public boolean onScale(ScaleGestureDetector detector) {
		return true;
	}
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
		return true;
	}


	public void onScaleEnd(ScaleGestureDetector detector) {
		// TODO Auto-generated method stub
	}


private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        //I NEED TO FIND A WAY TO CHECK IF THIS IS ON AN OBJECT OR NOT?
		SingletonObjects.cameraCursor.setScaleFactor(detector);
        return true;
    }
}



private class TapListener implements OnGestureListener, GestureDetector.OnDoubleTapListener
{
	@Override
	public boolean onDown(MotionEvent e) {

			// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
        int left = mGLView.getLeft();
        int top  =mGLView.getTop();
		SingletonObjects.cameraCursor.onDoubleTap(e,fb, left, top);
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		int left = mGLView.getLeft();
		int top  =mGLView.getTop();
		SingletonObjects.cameraCursor.onSingleTapConfirmed(e,fb, left, top);
		//pauseAction();
        //cameraCursor.onDoubleTap(e,fb);
		return false;
	}
	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
    ///DONT USE THIS EVER!
	//IT FIRES FAR TOO MANY TIMES ON EACH PRESS, WILL FUCK UP YOUR DAY.
	return false;
	}

}
}
