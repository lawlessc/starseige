precision highp float;
uniform vec3 atmosphereColour;

uniform mat4 modelViewMatrix;
uniform vec3 specularColors[8];

uniform vec3 lightPositions[8];
uniform int rendermodex;

varying vec3 norm;
varying vec4 eye;
varying float fogthickness;


void main(){

  vec4 spec = vec4(0.0);
  vec4 colorout = vec4(0.0,0.0,0.0,0.0);

  //vec4 colorout = (atmosphereColour);

if ( rendermodex == 0);
{
colorout = vec4(0.0,0.0,0.0,1.0);

        // normalize both input vectors
        vec3 n = normalize(norm);
        vec3 e = normalize(vec3(eye));
        vec3 lightDirection = normalize(lightPositions[0]);

        float intensity = max(dot( n,lightDirection), 0.0);
        float shininess= 1.0;


        // if the vertex is lit compute the specular color
      if (intensity > 0.0) {
            // compute the half vector
            vec3 h = normalize(lightDirection + e);
            // compute the specular term into spec
            float intSpec = max(dot(h,n), 0.0);
            spec = vec4(specularColors[0],0) * pow(intSpec,shininess);
        }
        colorout = max(intensity *  vec4(atmosphereColour,1) + spec, vec4(0.0));
      //  vec4  colorout = max( vec4(atmosphereColour,1) + spec, vec4(0.0));
      colorout = vec4( colorout.xyz , fogthickness);
}

if(rendermodex == 3)
{
colorout = vec4(0.0,0.0,0.0,0.0);

}


 gl_FragColor=colorout;
}