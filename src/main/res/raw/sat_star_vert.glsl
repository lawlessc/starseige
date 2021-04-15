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

varying vec2 v_texCoord;



void main()
{

v_texCoord.x = texture0.x;
v_texCoord.y = texture0.y;

//This doesn't work because all less thick section is cut off.
vec4 colour = texture2D(textureUnit0 , v_texCoord);
float distance = colour.a;

float thicknessDist=distance;//thickness*distance;

vec3 thick;

thick.x = normal.x*thicknessDist;
thick.y = normal.y*thicknessDist;
thick.z = normal.z*thicknessDist;


 vec4 pos= position;

 if(rendermodex !=3)
 {
 pos.y= position.y*2.0;
 }

//if(rendermode != 2)
//{
////pos.y= position.y*2.0;//stretches the texture vertically
//}

if(rendermodex == 1)
{

  pos.y= pos.y*1.5;
  pos.x= pos.x*1.5;
}

if(rendermodex == 2)
{

  pos.y= pos.y*0.5;
  pos.x= pos.x*0.5;
}

if(rendermodex == 3)
{

  pos.y= pos.y*1.5;
  pos.x= pos.x*1.5;
}



gl_Position =  modelViewProjectionMatrix * vec4(pos.xyz+thick,1.0);


//gl_Position =  modelViewProjectionMatrix * vec4(position.xyz+thick,1.0);
}

