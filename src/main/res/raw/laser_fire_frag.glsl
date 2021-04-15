//precision highp float;
#extension GL_OES_standard_derivatives : enable
// FRAGMENT SHADER
precision mediump float;

varying mediump vec2 v_texCoord;

uniform sampler2D textureUnit0;


uniform int rendermode;


//uniform int usesinewave;
//uniform float u_time;


uniform int threed;
uniform  vec3 a_colour;
uniform  vec3 GlowColor;
uniform  lowp float transparency;
const mediump float SmoothCenter = 0.5;
const mediump float GlowBoundary = 1.5;



void main()
{
mediump vec4 final;
if (threed==0) {
// retrieve distance from texture
mediump  vec4 colour = texture2D(textureUnit0 , v_texCoord);
mediump  float distance = colour.a;

mediump float smoothWidth = fwidth(distance);
mediump float alpha;
mediump vec3 rgb;




if (rendermode==1) {

   vec4 sample0, sample1, sample2, sample3;

  float step = 0.5 / 100.0;
    sample0 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y - step));
    sample1 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y + step));
    sample2 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y - step));
    sample3 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y + step));

    vec4 col  = (sample0 + sample1 + sample2 + sample3) * 0.5;
    mediump  float dist = col.a;
    mediump float smoothie = fwidth(dist);

 //    mediump float alphab = smoothstep(SmoothCenter - smoothie,
                               //              SmoothCenter + smoothie, dist);


                     //(vec3(1.0,1.0,1.0) + a_colour/2.0)
          // float  wave  =  sin(v_texCoord.z+u_time);

         //  GlowColor = mix(GlowColor,GlowBoundary,wave)

            final = vec4( GlowColor, dist);





    }
     else if(rendermode==0)
    {



        mediump float alphad = smoothstep(SmoothCenter - smoothWidth,
                                         SmoothCenter + smoothWidth, distance);
        final = vec4(a_colour, alphad);

    }
    else
    {
       mediump float alphad = smoothstep(SmoothCenter - smoothWidth,
                                              SmoothCenter + smoothWidth, distance);

    final=vec4(0,0,0,alphad);
    }

 final = vec4(final.xyz,final.a*transparency);
 }
 else
 {
  final = vec4(a_colour,transparency);

 }


 gl_FragColor = final;
}