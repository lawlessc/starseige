precision mediump float;
uniform vec2 uResolution;
//uniform sampler2D textureUnit0;
uniform float u_time;//cloudshader.setUniform("u_time", (allGameObjects.INSTANCE.runningTime*0.0001f));
uniform vec3 uAmbient;//should alwasy be black in this space game?
uniform vec3 uDiffuse; // the texture?

#define PI 3.14159
uniform lowp  int rendermode;
varying mediump vec2 v_texCoord;
varying mediump vec3 v_texCoordThree;
uniform vec3 lightPositions[8];

varying vec3 N;
varying vec3 v;




//vec4 renderClouds( const in vec3 ro, const in vec3 rd, const in float d, const in vec3 n, const in float land,
//                   const in vec3 sunColor, const in vec3 upColor, inout float shadow ) {
//	vec3 intersection = ro+rd*d;
//    vec3 cint = intersection*0.009;
//    float rot = -.2*length(cint.xy) + .6*fbm( cint*.4,0.5,2.96 ) + .05*land;
//
//    cint.xy = rotate( rot, cint.xy );
//
//    vec3 cdetail = mod(intersection*3.23,vec3(50.));
//    cdetail.xy = rotate( .25*rot, cdetail.xy );
//
//    float clouds = 1.3*(fbm( cint*(1.+.02*noise(intersection)),0.5,2.96)+.4*land-.3);
//
//#ifdef DISPLAY_CLOUDS_DETAIL
//    if( d < 200. ) {
//        clouds += .3*(fbm(cdetail,0.5,2.96)-.5)*(1.-smoothstep(0.,200.,d));
//    }
//#endif
//
//    shadow = clamp(1.-clouds, 0., 1.);
//
//    clouds = clamp(clouds, 0., 1.);
//    clouds *= clouds;
//    clouds *= smoothstep(0.,0.4,d);
//
//    vec3 clbasecolor = vec3(1.);
//    vec3 clcol = .1*clbasecolor*sunColor * vec3(specular(n,SUN_DIRECTION,rd,36.0));
//    clcol += .3*clbasecolor*sunColor;
//    clcol += clbasecolor*(diffuse(n,SUN_DIRECTION)*sunColor+upColor);
//
//    return vec4( clcol, clouds );
//}



//highp float hash(vec3 p)  // replace this by something better
//{
//    p  = fract( p*0.3183099+.1 );
//	p *= 9.0;//this can make things appear very patterend or random.
//    return fract( p.x*p.y*p.z*(p.x+p.y+p.z) );
//}
//
//vec3 hash33( const in vec3 p) {
//    return vec3( hash(p), hash(p.zyx), hash(p.yxz) );
//}
//
//highp vec3 noise3( in vec3 x )
//{
//    vec3 p = floor(x);
//    vec3 f = fract(x);
//    f = f*f*(3.0-2.0*f);
//
//    return mix(mix(mix( hash33(p+vec3(0.0,0.0,0.0)),
//                        hash33(p+vec3(1.0,0.0,0.0)),f.x),
//                   mix( hash33(p+vec3(0.0,1.0,0.0)),
//                        hash33(p+vec3(1.0,1.0,0.0)),f.x),f.y),
//               mix(mix( hash33(p+vec3(0.0,0.0,1.0)),
//                        hash33(p+vec3(1.0,0.0,1.0)),f.x),
//                   mix( hash33(p+vec3(0.0,1.0,1.0)),
//                        hash33(p+vec3(1.0,1.0,1.0)),f.x),f.y),f.z);
//}




highp vec4 plasmaThree(float w , float h,float z, float offset,vec3 coords)
{
  vec3 u_k = vec3(w,h,z);
      float v = offset;
      vec3 c = (coords* u_k - u_k)*0.3333333;
      v += sin((c.x+u_time));
      v += sin((c.y+u_time)*0.5);
      v += sin((c.z+u_time)*0.33333);
      v += sin((c.x+c.y+c.z+u_time)*0.3333333);
      c += u_k/3.0 * vec3(sin(u_time*0.3333333), cos(u_time*0.5) , sin(u_time*0.4));
      v += sin(sqrt(c.x*c.x+c.y*c.y+c.z*c.z +  1.0)  +u_time);
      v = v*0.3333333;
      v = PI*v;
      vec3 col = vec3(sin(((v)+(2.0*PI))*0.3333333), sin(v), cos(v));
      return vec4(col*.5 + .5, 1.0);//flattens it out.
}

highp vec3 offsets()
{
       vec4 off = plasmaThree(13.1,13.1,13.1 ,1.731456 ,v_texCoordThree.xyz );
       off.xyz*=2.0;
       off.xyz = vec3(off.x-1.0,off.y-1.0,off.z-1.0);

       vec4 offtwo = plasmaThree(2.1,2.1,2.1 ,2.99931456 ,v_texCoordThree.xyz );
       offtwo.xyz*=2.0;
       offtwo.xyz = vec3(offtwo.x-1.0,offtwo.y-1.0,offtwo.z-1.0);

       vec3 offsetcoords= v_texCoordThree.xyz  +(off.xyz*.5);//+ clamp(offtwo.xyz,0.0,1.0);
       offsetcoords= offsetcoords  +(offtwo.xyz*0.6);//if i use this for clouds i should make this component change more slowloy

return offsetcoords;
}


highp float rande(vec3 co)  // replace this by something better
{
    highp float a = 12.9898;
    highp float b = 78.233;
    highp float h = 21.233;
    highp float c = 43758.5453;
    highp float dt= dot(co.xzy ,vec3(a,b,h));
    highp float sn= mod(dt,3.14);
    return fract(sin(sn) * c);
}

highp float noise( in vec3 x )
{

    vec3 p = floor(x);
    vec3 f = fract(x);
    f = f*f*(3.0-2.0*f);

    return mix(mix(mix( rande(p+vec3(0.0,0.0,0.0)),
                        rande(p+vec3(1.0,0.0,0.0)),f.x),
                   mix( rande(p+vec3(0.0,1.0,0.0)),
                        rande(p+vec3(1.0,1.0,0.0)),f.x),f.y),
               mix(mix( rande(p+vec3(0.0,0.0,1.0)),
                        rande(p+vec3(1.0,0.0,1.0)),f.x),
                   mix( rande(p+vec3(0.0,1.0,1.0)),
                        rande(p+vec3(1.0,1.0,1.0)),f.x),f.y),f.z);
}

float turbulence(in vec3 x) {
    float t = -.5;
    float W=9.0;




    for (float f = 1.0 ; f <= W ; f *= 3.0)
     {// W = Image width in pixels
        t += abs(noise(x) / f);
     }
    return t;
}


const mat3 m = mat3( 0.00,  0.80,  0.60,
                    -0.80,  0.36, -0.48,
                    -0.60, -0.48,  0.64 );


highp float cloudf()//maybe concert the return type to a float?
{  //i need to use plasma effect to distort the cloudscape but i also need ot make
                                       //sure the distortion does not cause astretching effect, so the more a point is
                                       //distorted the greater should be the q value!
                                      // vec4 on = plasmaThree(3.1,3.1,3.1 ,1.731456 ,v_texCoordThree.xyz );

       vec3 distorted  = offsets();

       vec4 off = plasmaThree(4.1,4.1,4.1 ,1.331456 ,distorted );
       off.xyz*=2.0;
       off.xyz = vec3(off.x-1.0,off.y-1.0,off.z-1.0);
       distorted=distorted  +(off.xyz*.5);


       float dist = distance(distorted,v_texCoordThree.xyz);
       vec4  ret = vec4(0,0,0,1.0);

       vec3 q = 6.0* distorted*dist; //the higher the number is here the more grainy it looks

       float f =0.0;
       f  = 0.5000*noise( q ); q = m*q*2.01;
      //f += 0.2500*noise( q ); q = m*q*2.02;
       f += 0.1250*noise( q ); q = m*q*6.03;
      // f += 0.0625*noise( q ); q = m*q*18.01;

        return f;
}



lowp float noise(vec2 ec )
{
	return sin(1.5*ec.x)*sin(1.5*ec.y);
}


void main(void)
{
 vec4 col  = vec4(0.0,0.0,0.0,0.0);

 if(rendermode == 0)//normally set to zero
 {

 vec4 white = vec4(1.0,1.0,1.0,1.0);

      vec3 L = normalize(lightPositions[0] - v);
      //calculate Diffuse Term:
      float res = max(dot(N,L), -0.25);
      res+=0.23;

     // vec4 Idiff =  vec4(uDiffuse,1 )* res;

      vec4 reddish = vec4(.9,.3,.051,1.0);
      vec4 Idiff =  vec4(white.xyz,1 )* res;

      reddish= vec4(reddish.xyz,1 )* (res+0.1);

      //Idiff = Idiff*1.7;
      Idiff = clamp(Idiff, 0.0, 1.0);
      reddish = clamp(reddish, 0.0, 1.0);


       white = (reddish+Idiff);

if( reddish.x>0.2 )
 {//we do this first to avoid wasting cycles.
 if(cloudf() > 0.5)
     {
        col= white;
     }
  }
 }


  gl_FragColor = col;
}