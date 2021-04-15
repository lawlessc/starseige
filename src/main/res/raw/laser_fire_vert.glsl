//precision highp float;
precision mediump float;
// VERTEX SHADER
uniform mat4 modelViewProjectionMatrix;
uniform float thickness;

uniform sampler2D textureUnit0;

uniform int rendermodex;

attribute vec2 texture0;
attribute vec4 position;

attribute vec3 normal;

uniform vec3 originpoint;
uniform vec3 endpoint;
uniform float lengthmult;


varying vec2 v_texCoord;

uniform float u_time;

//varying vec2 sinwave;

void main()
{

v_texCoord.x = texture0.x ;
v_texCoord.y = texture0.y;


//This doesn't work because all less thick section is cut off.
vec4 colour = texture2D(textureUnit0 , v_texCoord);
float distance = colour.a;

float thicknessDist=distance;//thickness*distance;

vec3 thick;

 vec4 pos= position;

 pos.z = pos.z* lengthmult;


 float  wave  =  sin(pos.z+u_time);//perhaps use origin point and endpint here


thick.x = normal.x*thicknessDist;
thick.y = normal.y*thicknessDist;//wave;
thick.z = normal.z*thicknessDist;//wave;


 //vec4 pos= position;

 //pos.z = pos.z* lengthmult;
 //pos.z = -pos.z;


 //thick.x+=wave;


if(rendermodex == 1)
{

  //pos.y= pos.y*1.5;
  pos.x= pos.x*0.01+(normal.x*wave);
}

if(rendermodex == 0)
{

  //pos.y= pos.y*0.5;
  pos.x= pos.x*0.01+(normal.x*wave);
}

gl_Position =  modelViewProjectionMatrix * vec4(pos.xyz+thick,1.0);
}

