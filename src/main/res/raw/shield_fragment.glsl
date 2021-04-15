precision mediump float;


     varying float shieldThickness;
     uniform vec3 shieldcolour;
     uniform lowp  int rendermode;

void main() {

vec4 final = vec4(0.0,0.0,0.0,0.0);
if(rendermode ==0)
{
final = vec4(shieldcolour,shieldThickness);
}

if(rendermode ==1)

{
final = vec4(shieldcolour,shieldThickness);
}

    gl_FragColor = final;



}