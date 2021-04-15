
#extension GL_OES_standard_derivatives : enable

uniform sampler2D textureUnit0;
uniform sampler2D textureUnit1;


varying mediump vec2 texpos;
varying lowp vec3 colour;
varying lowp vec3 GlowColor;
uniform lowp  int rendermode;
const mediump float SmoothCenter = 0.5;

#define  ambernight  vec3(1.0 ,0.58, 0.0)

lowp vec4 onlyGlow()
 {
  lowp vec4  col = texture2D(textureUnit0 , texpos);
  col.xyz =  GlowColor; //preservers transparency
  return col;
 }

lowp vec4 onlyColor()
{
  lowp vec4  col= texture2D(textureUnit0 , texpos);
  col.xyz =  colour;
  return col;
}



void main() {

 lowp vec4 col = vec4(0.0,0.0,0.0,0.0);

if(rendermode == 1)
{
    col= onlyGlow();
}
else if(rendermode == 0)
{
   col= onlyColor();
}
 else if (rendermode == 2)
{
    col= onlyColor();
}
else  if(rendermode == 3)
{
  mediump  vec4 coloura = texture2D(textureUnit1 , texpos);
  mediump  float distance = coloura.a;
  mediump float smoothWidth = fwidth(distance);

      mediump float alphad = smoothstep(SmoothCenter - smoothWidth,                                SmoothCenter + smoothWidth, distance);
          col = vec4(ambernight, alphad);

}
gl_FragColor= col;

}

