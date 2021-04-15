// from the Orange Book 2nd edition

varying vec3 reflect;
varying vec3 refract;
uniform float ratio;

//uniform sampler2D MyTex;

uniform sampler2D textureUnit0;
//uniform sampler2D textureUnit1;

void main (void)
{
vec3 refractColor = vec3( textureUnit0( MyTex, refract));
vec3 reflectColor = vec3( textureUnit0( MyTex, reflect));

vec3 color = mix(refractColor, reflectColor, ratio);

gl_FragColor = vec4(color, 1.0);
}

