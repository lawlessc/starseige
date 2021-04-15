precision mediump float;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;
uniform mat4 modelViewProjectionMatrix;

attribute highp vec4 position;
attribute highp vec3 normal;
attribute vec2 texture0;

varying vec3 N;
varying vec3 v;
varying vec2 texCoord;
varying mediump vec3 v_texCoordThree;
varying vec4 landscape;

uniform mediump float u_time;//world seed essentially


uniform  int rendermode;

#define PI 3.14159




//highp float rand(vec2 co)
//{//Thank you
////http://byteblacksmith.com/improvements-to-the-canonical-one-liner-glsl-rand-for-opengl-es-2-0/
//    highp float a = 12.9898;
//    highp float b = 78.233;
//    highp float c = 43758.5453;
//    highp float dt= dot(co.xy ,vec2(a,b));
//    highp float sn= mod(dt,3.14);
//    return fract(sin(sn) * c);
//}


//highp vec4 plasmaThree(float w , float h,float z, float offset,vec3 coords)
//{
//    vec3 u_k = vec3(w,h,z);
//    float v = offset;
//    vec3 c = (coords* u_k - u_k)*0.33333333;
//    v += sin((c.x+u_time));
//    v += sin((c.y+u_time)*0.5);
//    v += cos((c.z+u_time)*0.5);
//    v += sin((c.x+c.y+c.z+u_time)*0.33333333);
//    c += u_k/3.0 * vec3(sin(u_time*0.33333333), cos(u_time*0.5) , sin(u_time*0.4));
//    v += sin(sqrt(c.x*c.x+c.y*c.y+c.z*c.z +  1.0)  +u_time);
//    v = v*0.33333333;
//    v = PI*v;
//    vec3 col = vec3(sin(((v)+(2.0*PI))/3.0), sin(v), cos(v));
//    return vec4(col*.5 + .5, 1.0);//flattens it out.
//}
//
//highp vec4 landscapef()//maybe concert the return type to a float?
//{
//                              vec4 off = plasmaThree(17.1,20.1,17.1 ,1.731456 ,v_texCoordThree.xyz );
//                              vec4 offtwo = plasmaThree(7.1,8.1,7.1 ,2.731456 ,off.xyz );
//                              vec3 offsetcoords= v_texCoordThree.xyz  +(offtwo.xyz*1.5);//+ clamp(offtwo.xyz,0.0,1.0);
//
//                              vec4 pp = plasmaThree(3.1,3.1,3.1 ,0.2135251 ,offsetcoords);
//                              vec4 xx = plasmaThree(4.1,4.1,4.1,3.7314,offsetcoords);
//
//
//                              pp.xyz= xx.xyz - pp.xyz;
//                              pp.xyz= -pp.xyz;
//
//                              pp.a=1.0;
//                              pp.x=  (pp.x+pp.y+pp.z)*0.3333333;
//                              pp.y=    pp.x;
//                              pp.z=    pp.x;
//                              return pp;
//
//}



void main(void)
{
     texCoord = texture0.xy;
     //landscape = landscapef();
     v_texCoordThree = position.xyz;

  //   vec4 offsetpos=position;

   //  if(landscape.x >= 0.4)
  //   {
  //    offsetpos.xyz+=(normal*landscape.x)*0.04;
  //   }

    v = vec3(modelViewMatrix * position);
    N = normalize(modelViewMatrix * vec4(normal, 0.0)).xyz;
    gl_Position = modelViewProjectionMatrix * position;
}

