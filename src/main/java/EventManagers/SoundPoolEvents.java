package EventManagers;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.threed.jpct.shader.R;

/*
 * 
 * This class will control some , or all sound effects
 * 
 */

public final class SoundPoolEvents //implements Observer {
{
	SoundPool sp = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);

	MediaPlayer thrust_hiss ;
	
	int explosion ;
	int launch_rocket ;

	int button_down;
	int button_up;
	int laser_pulse;


	//int thrust_hiss;
	
	public SoundPoolEvents(Context context)
	{

		explosion = sp.load(context, R.raw.explosion, 1);
		launch_rocket = sp.load(context, R.raw.launch, 1);
		button_up = sp.load(context,R.raw.buttontap,1);
		laser_pulse = sp.load(context,R.raw.pulsegun03,1);


		thrust_hiss = MediaPlayer.create(context, R.raw.air_hiss);
		thrust_hiss.setLooping(true);

	}


	
	public void playExplosion()
	{
		
		sp.play(explosion, 1, 1, 0, 0, 1);
	}

	public void laserpulse()
	{

		sp.play(laser_pulse, 1, 1, 0, 0, 1);
	}


	public void playLaunch()
	{
		
		sp.play(launch_rocket, 1, 1, 0, 0, 1);
	}

	public void playThrustHiss()
	{
		if(thrust_hiss.isPlaying())
		{

		}
		else
		{
			thrust_hiss.start();

		}
	}

	public void stopThrustHiss()
	{
	//	if(thrust_hiss.isPlaying())
	//	{
			thrust_hiss.stop();
	//	}
	//	else
	//	{


	//	}

	}

	public void wormHoleOpen()
	{

		sp.play(launch_rocket, 1, 1, 0, 0, 1);
	}

	public void wormHoleClose()
	{
		sp.play(launch_rocket, 1, 1, 0, 0, 1);
	}



	//UI Sounds
	public void playSelectObject()
	{

	}

	public void playTapButtonSound()
	{

	}


	public void playTapButtonDown()
	{

	}

	public void playTapButtonUp()
	{
		sp.play(button_up, 1, 1, 0, 0, 1);
	}
	

}
