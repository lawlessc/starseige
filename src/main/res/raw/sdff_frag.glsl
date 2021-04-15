//precision highp float;
#extension GL_OES_standard_derivatives : enable
// FRAGMENT SHADER
precision mediump float;

varying mediump vec2 v_texCoord;

uniform sampler2D textureUnit0;



uniform int glow;
uniform int threed;
uniform  vec3 a_colour;
//uniform  vec3 GlowColor;
uniform  lowp float transparency;
//const mediump float SmoothCenter = 0.5;
//const mediump float GlowBoundary = 1.0;
uniform mediump float SmoothCenter;// = 0.5;
uniform mediump float GlowBoundary;

uniform int nightmode;
#define  ambernight  vec3(0.5 ,0.29, 0.0)






void main()
{

if(threed==3)
{


}

mediump vec4 final;


vec3 maincol=a_colour;

if(nightmode==1)
{
maincol=ambernight;
}


if (threed==0) {
// retrieve distance from texture
mediump  vec4 colour = texture2D(textureUnit0 , v_texCoord);
mediump  float distance = colour.a;

mediump float smoothWidth = fwidth(distance);
mediump float alpha;
mediump vec3 rgb;




if (glow==1) {

   vec4 sample0, sample1, sample2, sample3;

  float step = 0.5 / 100.0;
    sample0 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y - step));
    sample1 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y + step));
    sample2 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y - step));
    sample3 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y + step));

    vec4 col  = (sample0 + sample1 + sample2 + sample3) / 2.0;
    mediump  float dist = col.a;
    mediump float smoothie = fwidth(dist);

 //    mediump float alphab = smoothstep(SmoothCenter - smoothie,
                               //              SmoothCenter + smoothie, dist);


                     //(vec3(1.0,1.0,1.0) + a_colour/2.0)
            final = vec4( maincol, dist);





    }
    else
    {



        mediump float alphad = smoothstep(SmoothCenter - smoothWidth,
                                         SmoothCenter + smoothWidth, distance);
        final = vec4(maincol, alphad);

    }

 final = vec4(final.xyz,final.a*transparency);
 }
 else
 {
  final = vec4(maincol,transparency);

 }


 gl_FragColor = final;
}

