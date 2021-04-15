precision mediump float;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;
attribute vec3 position;

void main() {
     //TexCoord = VextexTexCoord;
     gl_Position = projectionMatrix *
                  modelViewMatrix *
                  vec4(position,1.0);
}