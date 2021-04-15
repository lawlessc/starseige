//precision highp float;
precision lowp float;
// VERTEX SHADER
uniform mat4 modelViewProjectionMatrix;

//uniform sampler2D textureUnit0;
attribute vec2 texture0;
attribute vec4 position;
varying mediump vec2 texpos;
varying  lowp vec3 colour;
varying  lowp vec3 GlowColor;

void main()
{
texpos.x =texture0.x;
texpos.y =texture0.y;

colour = vec3(1.0,1.0,0.5);
GlowColor = vec3(1.0,0.7,0.0);

gl_Position =  modelViewProjectionMatrix * vec4(position.xyz,1.0);
}

