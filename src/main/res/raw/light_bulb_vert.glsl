//precision highp float;
precision mediump float;
// VERTEX SHADER
uniform mat4 modelViewProjectionMatrix;

uniform sampler2D textureUnit0;
attribute vec2 texture0;
attribute vec4 position;
uniform lowp  int rendermode;
varying mediump vec2 texpos;
uniform  lowp vec3 colour;
uniform  lowp vec3 GlowColor;

void main()
{
texpos.x =texture0.x;
texpos.y =texture0.y;


gl_Position =  modelViewProjectionMatrix * vec4(position.xyz,1.0);
}