// from the Orange Book 2nd edition

uniform mat4 modelViewMatrix;


attribute vec4 position;

const float Eta = 0.66;
const float FresnelPower = 0.5;
const float F = ((1.0-Eta)*(1.0-Eta))/((1.0+Eta)*(1.0+Eta));

varying vec3 reflect;
varying vec3 refract;
uniform float ratio;

void main()
{
    vec4 ecPosition = modelViewMatrix * position;
    vec3 ecPosition3 = ecPosition.xyz / ecPosition.w;

    vec3 i = normalize(ecPosition3);
    vec3 n = normalize(gl_NormalMatrix * gl_Normal);


    Ratio = F + (1.0 - F) * pow((1.0 - dot(-i, n)), FresnelPower);

    refract = refract(i, n, Eta);
    refract = vec3(gl_TextureMatrix[0] * vec4 (refract, 1.0));

    reflect = reflect(i, n);
    reflect = vec3(gl_TextureMatrix[0] * vec4 (reflect, 1.0));

    gl_Position = ftransform();
}
