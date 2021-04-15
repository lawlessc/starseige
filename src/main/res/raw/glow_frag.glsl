precision mediump float;


     uniform  int glows;

     vec4 innerColor = vec4(0,1,1,0.3);
     vec4 outerColor = vec4(1,0,0,0.6);
     float radiusInner = 3.0;
     float radiusOuter = 3.0;


uniform lowp  int rendermode;

void main() {

    float deltaX = 2.0 - 0.0;
    float deltaY = 2.0 - 0.0;
    float distance = sqrt(deltaX*deltaX +deltaY*deltaY);

    col = mix (innerColor, outerColor, smoothstep(radiusInner, radiusOuter, distance));

    gl_FragColor = col;
}