//precision highp float;
precision mediump float;
//attribute vec3 vertex;
uniform mat4 modelViewMatrix;
uniform mat4 modelViewProjectionMatrix;
//uniform mat4 projectionMatrix;
attribute vec3 position;



uniform vec3 v3CameraPos;
//funiform vec3 v3LightPos ;

uniform vec3 lightPositions[8];



uniform float fCameraHeight;// = length(v3CameraPos);
uniform float fCameraHeight2;// = fCameraHeight * fCameraHeight;

vec3  v3InvWavelength = vec3(  1.0 / pow(0.650, 4.0),  1.0 / pow(0.570, 4.0),  1.0 / pow(0.475, 4.0));


uniform float fInnerRadius ;//= 3.05;//1.0
uniform float fInnerRadius2 ;//= fInnerRadius * fInnerRadius;//calculations like this might be better done on cpu not gpu

uniform float fOuterRadius ;//= fInnerRadius * 1.125;
uniform float fOuterRadius2 ;//= fOuterRadius * fOuterRadius;

float kR =  0.0025;
float kM = 0.0015;
float ESun = 15.0;
float fKrESun = kR * ESun;
float fKmESun = kM * ESun;
float fKr4PI = kR * 4.0 * 3.14159;
float fKm4PI = kM * 4.0 * 3.14159;


// float fScale = 1.0 / (fOuterRadius - fInnerRadius);//PROBLEM HERE
 float fScaleDepth = 0.25;
// float fScaleOverScaleDepth = fScale / fScaleDepth;

const int iSamples = 8;
const float fInvSamples = 0.25;

//varying vec2 texCoord;
varying vec3 colorX;
varying vec3 secondaryColor;
varying vec3 v3Direction;
varying vec3 lightpos;
uniform lowp  int glow_on;


float scale(float fCos)
{
float x = 1.0 - fCos;
return fScaleDepth * exp(-0.00287 + x*(0.459 + x*(3.83 + x*(-6.80 + x*5.25))));
}


float getNearIntersection(vec3 v3Pos, vec3 v3Ray, float fDistance2, float fRadius2)
{
        float B = 2.0 * dot(v3Pos, v3Ray);
        float C = fDistance2 - fRadius2;
        float fDet = max(0.0, B*B - 4.0 * C);
        return 0.5 * (-B - sqrt(fDet));
}

void main(void)
{

 float fScale = 1.0 / (fOuterRadius - fInnerRadius);//PROBLEM HERE
 //float fScaleDepth = 0.25;
 float fScaleOverScaleDepth = fScale / fScaleDepth;

// Get the ray from the camera to the vertex, and its length (which is the far point of the ray passing through the atmosphere)
vec3 v3Pos = position.xyz;
//vec3 v3Pos = vec3(modelViewMatrix * position.xyz);
//v3Pos.x = -v3Pos.x;
vec3 v3Ray = v3Pos - v3CameraPos;


float fFar = length(v3Ray);
v3Ray /= fFar;

  // Calculate the closest intersection of the ray with
  // the outer atmosphere (point A in Figure 16-3)
float fNear = getNearIntersection(v3CameraPos, v3Ray, fCameraHeight2,fOuterRadius2);

// Calculate the ray's starting position, then calculate its scattering offset
vec3 v3Start = v3CameraPos + v3Ray * fNear;
fFar -= fNear;

float fInvScaleDepth = 1.0 / fScaleDepth;
float fStartAngle = dot(v3Ray, v3Start) / fOuterRadius;
float fStartDepth = exp(-fInvScaleDepth);
float fStartOffset = fStartDepth * scale(fStartAngle);



// Initialize the scattering loop variables
float fSampleLength = fFar * fInvSamples;
//float fSampleLength = fFar / fInvSamples;
float fScaledLength = fSampleLength * fScale;
vec3 v3SampleRay = v3Ray * fSampleLength;
vec3 v3SamplePoint = v3Start + v3SampleRay * 0.5;

// Now loop through the sample rays
vec3 v3FrontColor = vec3(0.0, 0.0, 0.0);
for(int i=0; i<iSamples; i++)
{
float fHeightt = length(v3SamplePoint);
float fDepth = exp(fScaleOverScaleDepth * (fInnerRadius - fHeightt));

vec3 abcd =  lightPositions[0];
lightpos =  normalize((modelViewMatrix*vec4(abcd,1)).xyz);

float fLightAngle = dot(lightpos, v3SamplePoint) / fHeightt;

float fCameraAngle = 1.0;

float fScatter = (fStartOffset + fDepth*(scale(fLightAngle) - scale(fCameraAngle)));
vec3 v3Attenuate = exp(-fScatter * (v3InvWavelength * fKr4PI + fKm4PI));
v3FrontColor += v3Attenuate * (fDepth * fScaledLength);
v3SamplePoint += v3SampleRay;
}

// Finally, scale the Mie and Rayleigh colors and set up the varying variables for the pixel shader
colorX = v3FrontColor * (v3InvWavelength * fKrESun);
secondaryColor = v3FrontColor * fKmESun;    

v3Direction = v3CameraPos - v3Pos;


gl_Position =  modelViewProjectionMatrix * vec4(position.xyz,1.0);//vec4(vertex,1.0);
}