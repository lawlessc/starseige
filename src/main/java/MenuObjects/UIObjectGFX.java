package MenuObjects;

import android.content.res.Resources;

import com.threed.jpct.GLSLShader;
import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.shader.R;

public class UIObjectGFX {

	//public Object3D orbitSphere;

	Texture awesomefaceTexture = null;
	Texture circleTexture = null;
	
	static GLSLShader glyphShader = null;

	public UIObjectGFX(Resources res)
	{
		TextureManager tm = TextureManager.getInstance();

		 awesomefaceTexture =	new Texture(res.openRawResource(R.raw.awesomefacesdf),true);
		 tm.addTexture("awesomeface", awesomefaceTexture);
		 tm.getTexture("awesomeface").setMipmap(false);
		 
		 circleTexture =	new Texture(res.openRawResource(R.raw.circlesdf),true);
		 tm.addTexture("circleTexture", circleTexture);
		 tm.getTexture("circleTexture").setMipmap(false);

         glyphShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.sdf_vert)), 
	                Loader.loadTextFile(res.openRawResource(R.raw.sdff_frag)));
	}


	public SpatialGlyph createGlyphObj3d(UIObject obj ,Object3D object3D ,float scale )
	{
		SpatialGlyph newGlyph = new	SpatialGlyph(obj,glyphShader ,scale ,object3D);
		return newGlyph;
	}
	
	public SpatialGlyph createGlyph(UIObject obj ,String texturename ,float scale )
	{
		SpatialGlyph newGlyph = new SpatialGlyph(obj,texturename,glyphShader,scale);
		return newGlyph;
	}

	public static Object3D copy(Object3D bluePrint) {
		Object3D copy=new Object3D(bluePrint, true);
		copy.shareCompiledData(bluePrint);
		copy.shareTextureData(bluePrint);
		return copy;
	}
	
	
	
	
	
	
	
}
