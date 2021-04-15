//precision highp float;
#extension GL_OES_standard_derivatives : enable
// FRAGMENT SHADER
precision lowp float;


uniform sampler2D textureUnit0;


varying mediump vec2 v_texCoord;
uniform lowp  int rendermode;



void main()
{

vec4 col  = vec4(0.0,0.0,0.0,1.0);
if(rendermode == 0)
{
 // vec4 sample0;//, sample1, sample2, sample3;

 // float step = 0.05 / 100.0;
    col = texture2D(textureUnit0, vec2(v_texCoord.x, v_texCoord.y ));
    //sample1 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y + step));
    //sample2 = texture2D(textureUnit0, vec2(v_texCoord.x + step, v_texCoord.y - step));
   // sample3 = texture2D(textureUnit0, vec2(v_texCoord.x - step, v_texCoord.y + step));

    // col  = (sample0);// + sample1 + sample2 + sample3) / 2.0;
}


//col  = vec4(0.0,0.0,0.1,1.0);

 gl_FragColor = col;




}
