package graphicsSetup;

import android.content.Context;

import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import java.io.IOException;
import java.io.InputStream;

public final class LoadModel {
	
	
	
	public static Object3D loadModel(InputStream filename, float scale) {
        Object3D[] model = Loader.load3DS(filename, scale);
        Object3D o3d = new Object3D(0);
        Object3D temp = null;
        for (int i = 0; i < model.length; i++) {
            temp = model[i];
            temp.setCenter(SimpleVector.ORIGIN);
            temp.rotateX((float)( -.5*Math.PI));
            temp.rotateMesh();
            temp.setRotationMatrix(new Matrix());
            o3d = Object3D.mergeObjects(o3d, temp);
            o3d.build();
        }
        return o3d;
    }


    	public static Object3D loadModel(String filename, float scale, Context context)
                //throws IOException
                 {
      try {
          InputStream stream = context.getAssets().open(filename);
          Object3D[] model = Loader.load3DS(stream, scale);
          Object3D o3d = new Object3D(0);
          Object3D temp = null;
          for (int i = 0; i < model.length; i++) {
              temp = model[i];
              temp.setCenter(SimpleVector.ORIGIN);
              temp.rotateX((float) (-.5 * Math.PI));
              temp.rotateMesh();
              temp.setRotationMatrix(new Matrix());
              o3d = Object3D.mergeObjects(o3d, temp);
              o3d.build();
          }
          return o3d;
      }catch (IOException e )
      {
          e.printStackTrace();
          return null;
      }
                    // return null;
     }


    public static Object3D copy(Object3D bluePrint) {
        Object3D copy=new Object3D(bluePrint, true);
        copy.shareCompiledData(bluePrint);
        copy.shareTextureData(bluePrint);

        return copy;
    }


}
