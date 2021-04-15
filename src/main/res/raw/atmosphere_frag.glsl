//precision highp float;
precision mediump float;
varying vec3 colorX;
varying vec3 secondaryColor;


varying vec3 v3Direction;
//uniform vec3 v3LightPos ;
varying vec3 lightpos;


uniform lowp  int rendermode;
#define  ambernight  vec3(1.0 ,0.58, 0.0)

float g =  -0.95;
float g2 = g*g;
float fExposure = 2.0;

float getMiePhase(float fCos, float fCos2, float g, float g2)
{
        return 1.5 * ((1.0 - g) / (2.0 + g2)) * (1.0 + fCos2) / pow(abs(1.0 + g2 - 2.0*g*fCos), 1.5);
}
// Calculates the Rayleigh phase function
float getRayleighPhase(float fCos2)
{
        return 0.75 + 0.75*fCos2;
}


void main (void)
{//lightpos

vec4 col=  vec4(0.0,0.0,0.0,0.0);

if(rendermode == 0)
{
//float fCos = dot(v3LightPos, v3Direction) / length(v3Direction);
float fCos = dot(lightpos, v3Direction) / length(v3Direction);
float fCos2 = fCos * fCos;

col.rgb = (getRayleighPhase(fCos2) * colorX   + getMiePhase(fCos, fCos2, g, g2) * secondaryColor).xyz;
col.a =  col.b;
}

if(rendermode == 3)
{

col.rgb = ambernight;
col.a = 0.0;
}

//else
//{
//col = vec4(0.0,0.0,0.0,0.0);
//}



gl_FragColor= col;
} 
