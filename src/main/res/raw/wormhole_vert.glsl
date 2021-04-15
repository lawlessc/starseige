//precision highp float;
precision highp float;
// VERTEX SHADER
uniform mat4 modelViewProjectionMatrix;


uniform sampler2D textureUnit0;
attribute vec2 texture0;
attribute vec4 position;

attribute vec3 normal;

varying vec2 v_texCoord;

void main()
{
v_texCoord.x = texture0.x;
v_texCoord.y = texture0.y;


gl_Position =  modelViewProjectionMatrix * vec4(position.xyz,1.0);
}


