precision mediump float;


uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;
uniform mat4 modelViewProjectionMatrix;

attribute  highp vec4 position;

attribute vec3 normal;
attribute vec4 tangent;
attribute vec2 texture0;

//uniform vec4 additionalColor;
//uniform vec4 ambientColor;
// uniform vec3 lightPositions[8];

varying vec2 v_coords;
varying vec3 lightVec[2]; 
varying vec3 eyeVec;
varying vec2 texCoord;

varying vec3 N;
varying vec3 v;

varying mediump vec2 v_texCoord;
varying mediump vec3 v_texCoordThree;



void main() {


    v = vec3(modelViewMatrix * position);
    N = normalize(modelViewMatrix * vec4(normal, 0.0)).xyz;


v_texCoordThree = position.xyz;

	v_texCoord.x = texture0.x;
    v_texCoord.y = texture0.y;

     gl_Position = modelViewProjectionMatrix * position;
}