//precision highp float;
#extension GL_OES_standard_derivatives : enable
// FRAGMENT SHADER
precision mediump float;
//#define PI 3.14159

varying mediump vec2 v_texCoord;

uniform sampler2D textureUnit0;



uniform int glow;
uniform  vec3 a_colour;
uniform  vec3 GlowColor;
const mediump float SmoothCenter = 0.5;
const mediump float GlowBoundary = 1.0;


varying  float alphachan;
uniform lowp  int rendermode;

varying float Thickness;

uniform float u_time;
#define PI 3.14159

highp vec4 plasma(float w , float h)
{
    vec2 u_k = vec2(w,h);
    float v = 0.0;
    vec2 c = v_texCoord * u_k - u_k*0.5;
    v += sin((c.x+u_time));
    v += sin((c.y+u_time)*0.5);
    v += sin((c.x+c.y+u_time)*0.5);
    c += u_k*0.5 * vec2(sin(u_time*0.333333333), cos(u_time*0.5));
    v += sin(sqrt(c.x*c.x+c.y*c.y+1.0)+u_time);
    v = v*0.5;
    v = PI*v;
    vec3 col = vec3(sin(((v)+(2.0*PI))*0.333333333), sin(v), cos(v));
    return vec4(col*.5 + .5, 1.0);//flattens it out.
}

lowp float noise(vec2 ec )
{
	return sin(1.5*ec.x)*sin(1.5*ec.y);
}

void main()
{
vec4 final  = vec4(0.0,0.0,0.0,0.0);
if(rendermode == 1 && rendermode != 3)
{
// retrieve distance from texture
//This should be all moved into the vertex to avoid wasting computer power on
//running the plasma calculations on untextured area.


 float x,y;
  //   x = v_texCoord.x + 0.2 * clamp(wavy(60.0, 60.0),0.0,0.5);
    // y = v_texCoord.y + 0.2 *  clamp(wavy(60.0+2.5, 60.0+6.7),0.0,0.5);

           vec4 pp = plasma(20.0,20.0);
           vec4 cc = plasma(20.0,20.0);

           x = v_texCoord.x + 0.2 * clamp(pp.x,0.0,0.1);
           y = v_texCoord.y + 0.2 *  clamp(pp.y,0.0,0.1);



mediump  vec4 colour = texture2D(textureUnit0 , vec2(x,y));
 colour.a=colour.a-(cc.x + cc.y)*0.333333333;
mediump  float distance = colour.a;

mediump float smoothWidth = fwidth(distance);
mediump float alpha;
mediump vec3 rgb;



        mediump float alphad = smoothstep(SmoothCenter - smoothWidth,
                                         SmoothCenter + smoothWidth, distance);

                                           if(alphad > 0.0)
                                           {
                                           alphad=alphachan;
                                           }
      //  final = vec4(a_colour, alphad*0.9);
        final = vec4(a_colour, alphad*Thickness);

}
 gl_FragColor = final;
}

