precision lowp float;
     varying lowp float shockwaveThickness;
     varying lowp vec3 colour;
     uniform lowp  int rendermode;

void main() {

    if(rendermode ==2)
    {
       gl_FragColor= vec4(0,0,0,0);
    }
    else{
     gl_FragColor = vec4(colour,shockwaveThickness);
    }
}