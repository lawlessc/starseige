package com.threed.jpct.shader;

import android.content.res.Resources;
import android.graphics.Color;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.GLSLShader;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Loader;
import com.threed.jpct.NPOTTexture;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureInfo;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;

import Entity_types.Sun;
import RenderHooks.PostProcessingRenderHook;
import baseinterfacesclasses.SingletonObjects;

/**
 * Created by lawless on 12/10/2015.
 */
public class PostProcessHandler {

    public NPOTTexture glowTexture;
    //public NPOTTexture glowTextureLowP;
    public NPOTTexture glowTextureMidP;
    public NPOTTexture godRayTexture;

    public NPOTTexture mainTexture;
    public Texture textureles;


    World world;
    World postProcessWorld;
    //where our texture /object3d will exist to render a fullscreen quad;
    Camera cam = null;

    public boolean nightmode = false;
    public int RenderMode = 0;  //Render Mode 0 is regular,
    // 1 is glow,
    // 2 is godrays ,
    // 3 is nightmode sometimes.

    public boolean nightmode_startup= false;
    public float   startup_point= 5.9f;//at 0.0 it isn't fully setup and 1.0 it is.



    Object3D theRenderspot = null;

    GLSLShader renderShader = null;
    PostProcessingRenderHook renderHook = null;
    TextureManager tm = TextureManager.getInstance();
    TextureInfo screens_ti;

    //GLSLShader gameboy_shader = null;
    Sun sun = null;
    public SimpleVector sunScreenPos = new SimpleVector(0, 0, 0);
    public int doLightScattering = 0;


    int divRatio;


    int main_height;
    int main_width;

    int main_height_half;
    int main_width_half;

    int main_height_quart;
    int main_width_quart;


    public void setSun(Sun sun) {

        this.sun = sun;
    }

    public PostProcessHandler(World world, Resources res, FrameBuffer fb) {


        textureles = new Texture(res.openRawResource(R.raw.textureless));

        this.theRenderspot = Primitives.getPlane(4, 10);

        divRatio = 3;

        this.world = world;
        postProcessWorld = new World();
        cam = postProcessWorld.getCamera();
        cam.setPosition(-10, 0, 0);
        cam.lookAt(new SimpleVector(0, 0, 0));

        renderShader = new GLSLShader(Loader.loadTextFile(res.openRawResource(R.raw.postprocess_vert)),
                Loader.loadTextFile(res.openRawResource(R.raw.postprocess_frag)));

        main_height=fb.getHeight();
        main_width=fb.getWidth();
        main_height_half=fb.getHeight()/2;
        main_width_half=fb.getWidth()/2;
        main_height_quart=fb.getHeight()/4;
        main_width_quart=fb.getWidth()/4;

        glowTexture = new NPOTTexture(main_width,main_height, RGBColor.GREEN);
        //glowTexture = new NPOTTexture(fb.getWidth()/2 , fb.getHeight()/2, RGBColor.GREEN);
        glowTexture.setFiltering(true);
        glowTexture.setMipmap(false);
        glowTexture.setTextureCompression(true);//turning on texture compression eliminates the artifacts, no idea why lol
        tm.addTexture("glowscene", glowTexture);


        glowTextureMidP = new NPOTTexture(main_width_quart, main_height_quart, RGBColor.GREEN);
        glowTextureMidP.setFiltering(true);
        glowTextureMidP.setMipmap(false);
        glowTextureMidP.setTextureCompression(true);
        tm.addTexture("glowscenemidp", glowTextureMidP);


//        glowTextureLowP = new NPOTTexture(fb.getWidth()/16 , fb.getHeight()/16, RGBColor.GREEN);
//        glowTextureLowP.setFiltering(true);
//        glowTextureLowP.setMipmap(false);
//        glowTextureLowP.setTextureCompression(true);
//        tm.addTexture("glowscenelowp", glowTextureLowP);


        godRayTexture = new NPOTTexture(main_width_half, main_height_half, RGBColor.GREEN);
        godRayTexture.setFiltering(true);
        godRayTexture.setMipmap(false);
        godRayTexture.setTextureCompression(true);
        tm.addTexture("godrays", godRayTexture);


        mainTexture = new NPOTTexture(main_width, main_height, RGBColor.BLUE);
        mainTexture.setFiltering(true);
        mainTexture.setMipmap(false);
        mainTexture.setTextureCompression(true);
        tm.addTexture("mainprocess", mainTexture);


        screens_ti = new TextureInfo(TextureManager.getInstance().getTextureID("glowscene"));
        screens_ti.add(TextureManager.getInstance().getTextureID("glowscenemidp"), TextureInfo.MODE_ADD);
        //screens_ti.add(TextureManager.getInstance().getTextureID("glowscenelowp"), TextureInfo.MODE_ADD);
        screens_ti.add(TextureManager.getInstance().getTextureID("godrays"), TextureInfo.MODE_ADD);
        theRenderspot.setTexture(screens_ti);


        ///  theRenderspot.setTransparency(3);
        theRenderspot.setCulling(false);


        renderHook = new PostProcessingRenderHook(theRenderspot, renderShader, this);
        renderHook.setCurrentShader(renderShader);

        theRenderspot.setOrigin(new SimpleVector(0.01, 0, 0));
        theRenderspot.setShader(renderShader);
        theRenderspot.setRenderHook(renderHook);
        postProcessWorld.addObject(theRenderspot);


    }


    public void OnSurfaceChange(FrameBuffer fb) {


        main_height=fb.getHeight();
        main_width=fb.getWidth();
        main_height_half=fb.getHeight()/2;
        main_width_half=fb.getWidth()/2;
        main_height_quart=fb.getHeight()/4;
        main_width_quart=fb.getWidth()/4;


        glowTexture = new NPOTTexture(main_width_half, main_height_half, RGBColor.GREEN);
        glowTexture.setFiltering(true);
        glowTexture.setMipmap(false);
        glowTexture.setTextureCompression(true);//turning on texture compression eliminates the artifacts, no idea why lol
        tm.addTexture("glowscene", glowTexture);


        glowTextureMidP = new NPOTTexture(main_width_quart, main_height_quart, RGBColor.GREEN);
        glowTextureMidP.setFiltering(true);
        glowTextureMidP.setMipmap(false);
        glowTextureMidP.setTextureCompression(true);
        tm.addTexture("glowscenemidp", glowTextureMidP);


//        glowTextureLowP = new NPOTTexture(fb.getWidth()/16 , fb.getHeight()/16, RGBColor.GREEN);
//        glowTextureLowP.setFiltering(true);
//        glowTextureLowP.setMipmap(false);
//        glowTextureLowP.setTextureCompression(true);
//        tm.addTexture("glowscenelowp", glowTextureLowP);


        godRayTexture = new NPOTTexture(main_width_half, main_height_half, RGBColor.GREEN);
        godRayTexture.setFiltering(true);
        godRayTexture.setMipmap(false);
        godRayTexture.setTextureCompression(true);
        tm.addTexture("godrays", godRayTexture);


        mainTexture = new NPOTTexture(main_width, main_height, RGBColor.BLUE);
        mainTexture.setFiltering(true);
        mainTexture.setMipmap(false);
        mainTexture.setTextureCompression(true);
        tm.addTexture("mainprocess", mainTexture);


        screens_ti = new TextureInfo(TextureManager.getInstance().getTextureID("glowscene"));
        screens_ti.add(TextureManager.getInstance().getTextureID("glowscenemidp"), TextureInfo.MODE_ADD);
        // screens_ti.add(TextureManager.getInstance().getTextureID("glowscenelowp"), TextureInfo.MODE_ADD);
        screens_ti.add(TextureManager.getInstance().getTextureID("godrays"), TextureInfo.MODE_ADD);
        theRenderspot.setTexture(screens_ti);


        theRenderspot.setCulling(false);

        renderHook = new PostProcessingRenderHook(theRenderspot, renderShader, this);
        renderHook.setCurrentShader(renderShader);

        theRenderspot.setOrigin(new SimpleVector(0.01, 0, 0));
        theRenderspot.setShader(renderShader);
        theRenderspot.setRenderHook(renderHook);
        postProcessWorld.addObject(theRenderspot);
    }


    public void doPostProcess(FrameBuffer fb) {


        if (sun != null && !nightmode) {


            try {
                sunScreenPos = Interact2D.projectCenter3D2D(fb, sun.body);
            } catch (Exception e) {


            }


            if (sunScreenPos == null) {
                sunScreenPos = new SimpleVector(0, 0, 0);
            } else {
                sunScreenPos.x = (sunScreenPos.x / fb.getWidth());
                sunScreenPos.y = 1 - (sunScreenPos.y / fb.getHeight());
            }

            if (SingletonObjects.cam.getDirection().calcAngle(sun.position) < 0.9) {

                fb.resize(main_width_half, main_height_half);


                RenderMode = 2;
//                if (nightmode)////GLOW SECTION
//                { RenderMode = 3; }

                doLightScattering = 1;
                //I need to turn Off everthing except the sun and sky here.
                fb.setRenderTarget(godRayTexture);
                fb.clear();
                world.renderScene(fb);
                world.draw(fb);
                fb.display();
            } else {
                doLightScattering = 0;
            }

        } else {

            FrameClearance(fb,godRayTexture);
        }


        if (nightmode)//
        {//in this mode we give everything orange glow
            RenderMode = 3;//NIGHTMODE


                if(startup_point >=  -7.0f)
                {
                    startup_point -= 0.23f;
                }
                else
                {
                    nightmode_startup=false;
                    startup_point = 10.0f;
                }


            fb.resize(main_width_half, main_height_half);
            fb.setRenderTarget(glowTexture);
            fb.clear();
            //    fb.clear(Color.BLACK);
            world.renderScene(fb);
            world.draw(fb);

            fb.resize(main_width, main_height);
           SingletonObjects.menumanager.uiRender(fb);
            SingletonObjects.menumanager.uiDraw(fb);

            fb.display();


            fb.resize(main_width_quart, main_height_quart);
            fb.setRenderTarget(glowTextureMidP);
            fb.clear();
            world.renderScene(fb);
            world.draw(fb);

           // fb.resize(main_width, main_height);
//            SingletonObjects.menumanager.uiRender(fb);
//            SingletonObjects.menumanager.uiDraw(fb);


            fb.display();

            fb.resize(main_width_half, main_height_half);
            //RENDERS A world with only the glow buffer.
            fb.setRenderTarget(mainTexture);
            fb.clear(Color.BLACK);
            postProcessWorld.renderScene(fb);//WAS POST PROCESS
            postProcessWorld.draw(fb);
            fb.display();
            fb.removeRenderTarget();

        } else {
            RenderMode = 1;//GLOWMODE

            fb.resize(main_width_half, main_height_half);
            fb.setRenderTarget(glowTexture);
            fb.clear();
            //    fb.clear(Color.BLACK);
            world.renderScene(fb);
            world.draw(fb);

            fb.display();


            fb.resize(main_width_quart, main_height_quart);
            fb.setRenderTarget(glowTextureMidP);
            fb.clear();
            world.renderScene(fb);
            world.draw(fb);

            fb.display();


            //RENDERS A world with only the glow buffer.
            fb.resize(main_width, main_height);
            fb.setRenderTarget(mainTexture);
            fb.clear(Color.BLACK);
            postProcessWorld.renderScene(fb);//WAS POST PROCESS
            postProcessWorld.draw(fb);
            fb.display();
            fb.removeRenderTarget();

        }


        if (nightmode) {//in this mode we give everything orange glow

            // fb.setRenderTarget(mainTexture);
            fb.resize(main_width,main_height);
            fb.clear(Color.BLACK);
            postProcessWorld.renderScene(fb);//WAS POST PROCESS
            postProcessWorld.draw(fb);
            fb.display();
            fb.removeRenderTarget();


        } else {//NORMAL MODE MAIN RENDERING
            RenderMode = 0;

            fb.resize(main_width,main_height);
            fb.clear();
            world.renderScene(fb);
            world.draw(fb);
            fb.display();
        }


        doBlit(fb);

        //RENDERS UI
        if(!nightmode) {
        //    fb.resize(main_width,main_height);
            SingletonObjects.menumanager.uiRender(fb);
            SingletonObjects.menumanager.uiDraw(fb);
        }
    }


    public void doBlit(FrameBuffer fb) {

        fb.resize(main_width,main_height);
        fb.blit(mainTexture, 0, 0, 0, fb.getHeight(),
                mainTexture.getWidth(), mainTexture.getHeight(), fb.getWidth(), -fb.getHeight(), 100, true, null);


    }




    //This is for circumstances where a texture needs to be cleared to black without rendering.
    public void FrameClearance(FrameBuffer fb, NPOTTexture tex)

    {
        fb.setRenderTarget(tex);
        fb.clear(Color.BLACK);
        fb.display();
        fb.removeRenderTarget();
    }



    public void SwitchGlowtexRes(FrameBuffer fb)
    {
        mainTexture = new NPOTTexture(fb.getWidth(), fb.getHeight(), RGBColor.BLUE);
        mainTexture.setFiltering(true);
        mainTexture.setMipmap(false);
        mainTexture.setTextureCompression(true);
        tm.addTexture("mainprocess", mainTexture);
    }


    public void switchnightmode()
    {

        nightmode = !nightmode;
        nightmode_startup =nightmode;
        startup_point=5.9f;
    }

}
