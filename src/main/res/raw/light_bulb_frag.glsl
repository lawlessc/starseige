uniform sampler2D textureUnit0;
uniform lowp  int rendermode;
varying mediump vec2 texpos;
uniform  lowp vec3 colour;
uniform  lowp vec3 GlowColor;


lowp vec4 onlyGlow()
{
  lowp vec4  col = texture2D(textureUnit0 , texpos);
    col.xyz =  GlowColor; //preservers transparency
    return col;
}



lowp vec4 onlyColor()
{
  lowp vec4  col= texture2D(textureUnit0 , texpos);
  col.xyz =  colour; //preservers transparency
  return col;
}

void main() {

if(rendermode ==1)
{
    gl_FragColor= onlyGlow();
}
if(rendermode ==0)
{
   gl_FragColor= onlyColor();
}

if(rendermode ==2)
{
   gl_FragColor= vec4(0,0,0,0);
}


//gl_FragColor= vec4(1,1,1,1);


}