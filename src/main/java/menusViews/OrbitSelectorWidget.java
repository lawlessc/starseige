package menusViews;

import com.threed.jpct.SimpleVector;

import OrbitCalcs.OrbitData;

/**
 * Created by Chris on 04/04/2016.
 */
public class
OrbitSelectorWidget extends Widget{

    OrbitSelectorWidget  thisWidget;
    OrbitWidget orbitWidget;
    Spacialpuc spacialCursor;
    TriangleWidget triangleWidget;
    OrbitData  orbitData;

   public OrbitSelectorWidget(SimpleVector startPosition)
    {
       thisWidget=this;
       spacialCursor= new Spacialpuc(startPosition);
       triangleWidget = new TriangleWidget(spacialCursor.getPosition());
    }

    @Override
    public void closeWidget() {
        orbitWidget.closeWidget();
        spacialCursor.closeWidget();
        triangleWidget.closeWidget();
    }

    public void UpdateLines()
    {
        SimpleVector pucposition= spacialCursor.getPosition();
        SimpleVector flatPos= new SimpleVector(pucposition);
        flatPos.y=0;
        flatPos = flatPos.normalize();

        float dist =       spacialCursor.getPosition().distance(new SimpleVector(0, 0, 0));

        flatPos.scalarMul(dist);

        SimpleVector ctop=  new SimpleVector(flatPos.x ,20,flatPos.z);
        SimpleVector planepos = new SimpleVector(pucposition.x,0, pucposition.z);

        SimpleVector pucpos= new SimpleVector( pucposition.x, pucposition.y, pucposition.z);

        float angle = pucpos.calcAngle(planepos);

        if(pucposition.y < 0)
        {
            angle = -angle;
        }

        if(orbitWidget == null) {

            orbitData = new OrbitData(ctop, 20f, angle, pucposition);
            orbitWidget = new OrbitWidget(orbitData);
        }else {

            orbitData.setOrbitData(ctop, 20f, angle, pucposition);
            orbitWidget.Update();
        }
        triangleWidget.update(pucposition);
    }


    public void OnDoubleTap()
    {
     ///OVERRIDE THIS
    }


    public class Spacialpuc extends SpacialCursor
    {

        public Spacialpuc(SimpleVector startPosition) {
            super(startPosition);
        }


        @Override
        public void OnMove()
        {
            thisWidget.UpdateLines();
        }

        @Override
        public void OnDoubleTap()
        {
            thisWidget.OnDoubleTap();
        }

        @Override
        public void OnSingleTap()
        {
            thisWidget.OnDoubleTap();
        }
    }

}
