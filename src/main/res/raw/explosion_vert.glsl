precision lowp float;

uniform lowp mat4 modelViewMatrix;
uniform  lowp mat4 projectionMatrix;

uniform lowp vec3 explosionColour;

attribute lowp vec4 position;
attribute lowp vec3 normal;

varying lowp float shockwaveThickness;
varying lowp vec3 colour;
uniform lowp  int rendermode;



void main() {


     vec3   camNormal =  normalize(modelViewMatrix * vec4(normal, 0.0)).xyz;
     vec3 v3Ray= -modelViewMatrix[3].xyz;
     v3Ray= normalize(v3Ray);
     colour = explosionColour;

    shockwaveThickness  = 1.0 -dot(v3Ray,camNormal );



     gl_Position =  projectionMatrix * modelViewMatrix *position; //* vec4(position,1.0);
}