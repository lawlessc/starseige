precision highp float;
varying vec2 v_texCoord;
uniform sampler2D textureUnit0;
uniform sampler2D textureUnit1;

//god ray stuff

uniform lowp  float exposure;
uniform lowp  float decay;
uniform lowp  float density;
uniform lowp  float weight;
uniform lowp vec3 lightPositionOnScreen;//only use XY
uniform sampler2D textureUnit2;
uniform lowp int doLightScattering;

const lowp vec2 scale = vec2(0.5, 0.5);


highp float rand(vec2 co)
{//Thank you
//http://byteblacksmith.com/improvements-to-the-canonical-one-liner-glsl-rand-for-opengl-es-2-0/
    highp float a = 12.9898;
    highp float b = 78.233;
    highp float c = 43758.5453;
    highp float dt= dot(co.xy ,vec2(a,b));
    highp float sn= mod(dt,3.14);
    return fract(sin(sn) * c);
}


void rep( inout vec2 textCoo,inout float illuminationDecay, inout vec2 deltaTextCoord, inout vec4 fin)
{
       textCoo -= deltaTextCoord;
       vec4 samp = texture2D(textureUnit2, textCoo );
       samp *= illuminationDecay * weight;
       fin += samp;
       illuminationDecay *= decay;
}


vec4 GodRay()
{
       vec4 fin= texture2D(textureUnit2, v_texCoord.xy );
	   vec2 deltaTextCoord = vec2( v_texCoord.xy - lightPositionOnScreen.xy);
       vec2 textCoo = v_texCoord.xy;
       deltaTextCoord *= 0.03571428571428 * density;//we do the multiply instead of the division by 28
       float illuminationDecay = 1.0;

       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);//14
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);
       rep(textCoo,illuminationDecay,deltaTextCoord,fin);//28

 return fin *= exposure;
}







void main() {
 lowp vec4 sample0  = vec4(0,0,0,0)

 ,           sample1 = vec4(0,0,0,0) ,sample2 = vec4(0,0,0,0),
             sample3 = vec4(0,0,0,0) ,sample4 = vec4(0,0,0,0),
             sample5 = vec4(0,0,0,0) ,sample6 = vec4(0,0,0,0),
             sample7 = vec4(0,0,0,0);

 lowp float step = 0.10/ 100.0;
    sample0 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y - step));
    sample1 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y + step));
    sample2 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y - step));
    sample3 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y + step));

//    sample4 = texture2D(textureUnit1, vec2(v_texCoord.x - step, v_texCoord.y - step));
//    sample5 = texture2D(textureUnit1, vec2(v_texCoord.x + step, v_texCoord.y + step));
//    sample6 = texture2D(textureUnit1, vec2(v_texCoord.x + step, v_texCoord.y - step));
//    sample7 = texture2D(textureUnit1, vec2(v_texCoord.x - step, v_texCoord.y + step));




  if(doLightScattering ==1)
  {
    lowp vec4 g_rays = GodRay();

     gl_FragColor = min(
                        ((sample0 + sample1 + sample2 + sample3) / 4.0)+g_rays, 1.0);
  }
  else
  {



          sample4 = texture2D(textureUnit1, vec2(v_texCoord.x - step, v_texCoord.y - step));
          sample5 = texture2D(textureUnit1, vec2(v_texCoord.x + step, v_texCoord.y + step));
          sample6 = texture2D(textureUnit1, vec2(v_texCoord.x + step, v_texCoord.y - step));
          sample7 = texture2D(textureUnit1, vec2(v_texCoord.x - step, v_texCoord.y + step));

     gl_FragColor = min(
                        ((sample0 + sample1 + sample2 + sample3) / 4.0)+
                          (( sample4 +sample5 +sample6 +sample7)/4.0), 1.0);
  }






}





