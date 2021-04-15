precision mediump float;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;
uniform vec3 shieldcolour;
attribute vec4 position;
attribute vec3 normal;

varying float shieldThickness;
uniform lowp  int rendermode;


void main() {
     vec3   camNormal =  normalize(modelViewMatrix * vec4(normal, 0.0)).xyz;
     vec3 v3Ray= -modelViewMatrix[3].xyz;
     v3Ray= normalize(v3Ray);

     shieldThickness  = 1.0 -dot(v3Ray,camNormal );
     gl_Position =  projectionMatrix * modelViewMatrix *position;
}

