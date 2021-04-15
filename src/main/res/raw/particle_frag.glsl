


uniform sampler2D textureUnit0;


uniform lowp int glows;
uniform lowp  int rendermode;
varying mediump vec2 texpos;
uniform  lowp vec3 colour;
uniform  lowp vec3 GlowColor;


lowp vec4 onlyGlow()
{
    lowp vec4  col= texture2D(textureUnit0 , texpos);
    col.xyz =  GlowColor;//preservers transparency
    return col;
}

lowp vec4 onlyTexture()
{
 return texture2D(textureUnit0 , texpos);
}

lowp vec4 onlyColor()
{
 lowp vec4  col= texture2D(textureUnit0 , texpos);
     col.xyz =  colour;//preservers transparency
     return col;
}

lowp vec4 textureWithColour()
{

lowp vec4  col= texture2D(textureUnit0 , texpos);

    col.xyz = col.xyz+ GlowColor;
 return col;
}

void main() {


if(rendermode ==1)
{
if (glows==1) {
      gl_FragColor= onlyGlow();
}
    else
    {

    gl_FragColor=vec4(0.0,0.0,0.0,0.0);
    }
}
if(rendermode ==0)
{
    if (glows==1) {
        gl_FragColor= vec4(0.0,0.0,0.0,0.0);
                    }
      if (glows==0) {
             gl_FragColor= onlyTexture();
                    }
}

if(rendermode ==2 || rendermode ==3 )
{gl_FragColor= vec4(0.0,0.0,0.0,0.0);
}



}