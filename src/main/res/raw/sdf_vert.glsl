//precision highp float;
precision mediump float;
// VERTEX SHADER
uniform mat4 modelViewProjectionMatrix;
uniform float thickness;

uniform sampler2D textureUnit0;

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


gl_Position =  modelViewProjectionMatrix * vec4(position.xyz+thick,1.0);
}



 
 
