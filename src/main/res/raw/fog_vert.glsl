precision mediump float;
 uniform mat4 modelViewMatrix;
 uniform mat4 modelViewProjectionMatrix;
 uniform vec3 lightPositions[8];
 //uniform int rendermode;

 attribute highp vec4 position;
 attribute vec3 normal;

 varying float fogthickness;
 varying vec3 norm;
 varying vec4 eye;


 void main() {
     vec3 n = normalize(modelViewMatrix * vec4(normal, 0.0)).xyz;
     norm = n;
     eye= -modelViewMatrix[3];
     fogthickness = 1.2-     dot(normalize(eye.xyz),n );
     gl_Position =  modelViewProjectionMatrix *position;
 }