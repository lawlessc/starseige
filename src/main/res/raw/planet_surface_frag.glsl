precision highp float;

uniform vec3 uAmbient;//should alwasy be black in this space game?
uniform vec3 uDiffuse; // the texture?
uniform vec3 uSpecular; // the strong reflected light
uniform vec3 cityLightsColour;
uniform float uSpecIntensity;
uniform float uTransparency;

uniform vec3 lightPositions[8];

varying vec3 N;
varying vec3 v;
varying vec2 texCoord;

varying mediump vec3 v_texCoordThree;

uniform  mediump  float u_time;//world seed essentially


uniform int rendermode;
uniform float nightmode_point;
uniform int nightmode_started;

#define PI 3.14159

#define  humid  vec3(0.02,0.6,0.16)
#define  dry    vec3(0.76,0.70,0.5)
#define  ice    vec3(1.0,1.0,1.0)// This needs to be thresholded above a certain  height.

#define  deepocean    vec3(0.02,0.27,0.49)// This needs to be thresholded above a certain  height.
#define  shallows    vec3(0.56,1.0,.99)// 199,253,247
#define  ambernight  vec3(1.0 ,0.58, 0.0)
#define  amberbackground  vec3(0.06 ,0.0348, 0.0)


const mat3 m = mat3( 0.00,  0.80,  0.60,
                    -0.80,  0.36, -0.48,
                    -0.60, -0.48,  0.64 );


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





highp float hash(vec3 p, float z)  // replace this by something better
{
    p  = fract( p*0.3183099+.1 );
	p *= z;
    return fract( p.x*p.y*p.z*(p.x+p.y+p.z) );
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




highp vec4 plasmaThree(float w , float h,float z, float offset,vec3 coords)
{

  vec3 u_k = vec3(w,h,z);
      float v = offset;
      vec3 c = (coords* u_k - u_k)*0.33333333;
      v += sin((c.x+u_time));
      v += sin((c.y+u_time)*0.5);
      v += sin((c.z+u_time)*0.33333);
      v += sin((c.x+c.y+c.z+u_time)*0.33333333);
      c += u_k/3.0 * vec3(sin(u_time*0.33333333), cos(u_time*0.5) , sin(u_time*0.4));
      v += sin(sqrt(c.x*c.x+c.y*c.y+c.z*c.z +  1.0)  +u_time);
      v = v*0.33333333;
      v = PI*v;
      vec3 col = vec3(sin(((v)+(2.0*PI))/3.0), sin(v), cos(v));
      return vec4(col*.5 + .5, 1.0);//flattens it out.
}


highp vec4 landscapef(vec3 offsetcoords)//maybe concert the return type to a float?
{
            vec4  ret = vec4(0.0,0.0,0.0,1.0);
            float f;
            vec3 q = 1.0*offsetcoords; //the higher the number is here the more grainy it looks
            f  = 0.5000*noise( q ); q = m*q*2.01;
            f += 0.2500*noise( q ); q = m*q*2.02;
            f += 0.1250*noise( q ); q = m*q*2.03;
            f += 0.0625*noise( q ); q = m*q*2.01;

            q = 9.0*offsetcoords;
            f += 0.050*noise( q ); q = m*q*2.01;
            f += 0.025*noise( q ); q = m*q*2.02;
            f += 0.012*noise( q ); q = m*q*2.03;
            f += 0.006*noise( q ); q = m*q*2.01;



            ret.x =f;
            ret.y=ret.x;
            ret.z=ret.x;

            return ret;
}

//highp vec3 offsets()
//{
//       vec4 off = plasmaThree(3.1,3.1,3.1 ,1.731456 ,v_texCoordThree.xyz );
//       off.xyz*=2.0;
//       off.xyz = vec3(off.x-1.0,off.y-1.0,off.z-1.0);
//
//       vec4 offtwo = plasmaThree(2.1,2.1,2.1 ,2.99931456 ,v_texCoordThree.xyz );
//       offtwo.xyz*=2.0;
//       offtwo.xyz = vec3(offtwo.x-1.0,offtwo.y-1.0,offtwo.z-1.0);
//
//       vec3 offsetcoords= v_texCoordThree.xyz  +(off.xyz*.5);//+ clamp(offtwo.xyz,0.0,1.0);
//       offsetcoords= offsetcoords  +(offtwo.xyz*0.6);//if i use this for clouds i should make this component change more slowloy
//
//return offsetcoords;
//}

highp vec4 lights(vec3 offsetcoords, float height)//maybe concert the return type to a float?
{
            vec4  ret = vec4(0,0,0,1.0);
            float fk;//THIS IS PERFECT DO NOT CHANGE, SAVE IF YYOU DO
            vec3 qz = 5.0*offsetcoords ; //the higher the number is here the more grainy it looks
            fk  = 1.9000*noise( qz ); qz = m*qz*2.01;
            fk -= 0.2500*noise( qz ); qz = m*qz*2.02;
            fk -= 1.1250*noise( qz ); qz = m*qz*2.03;
            fk -= 1.0625*noise( qz ); qz = m*qz*2.01;

           // ret.x =mix ((fk+f)*.5,0.0,(height*2.0)-.5);
            ret.x =   mix(fk,  0.0,(height*2.0)-.5);
            ret.y=ret.x;
            ret.z=ret.x;
            return ret;
}



vec4 darksideGlow(vec4 city)
{
      vec4 color = vec4(0.0,0.0,0.0,1.0);
     vec4 base =city;
      vec3 L = -normalize(lightPositions[0] - v);
     // vec3 E = normalize(-v); // we are in Eye Coordinates, so EyePos is (0,0,0)
      //vec3 R = normalize(-reflect(L,N));
      //calculate Ambient Term:
      vec4 Iamb = vec4(uAmbient,1);

      //calculate Diffuse Term:
      float res = max(dot(N,L), -0.25);
      res+=0.23;
      vec4 Idiff =  vec4(uDiffuse,1 )* res;
//      Idiff = clamp(Idiff, 0.0, 1.0);
      // vec4 Idiff =  vec4(uDiffuse,1 )* max(dot(N,L), 0.0);
        Idiff = Idiff*1.7;
        Idiff = clamp(Idiff, 0.0, 1.0);

      float randout= rand(texCoord);
      randout= randout*0.1;

             highp vec4 newbase =  vec4( base.x - randout,
             base.y - randout,
             base.z - randout,1.0);

        color = (vec4(uAmbient,1.0) *newbase) + Iamb + (Idiff*newbase);

return color*vec4(cityLightsColour,1.0);
}



bool icecap(vec3 pos)
{

if(rendermode==3)
{
return false;
}

float fl= pos.y;

 float f;
 vec3 q = 20.5*pos ; //the higher the number is here the more grainy it looks
 f  = 0.5000*noise( q ); q = m*q*3.01;
 f += 0.2500*noise( q ); q = m*q*3.02;
 f += 0.1250*noise( q ); q = m*q*3.03;
 f += 0.0625*noise( q ); q = m*q*3.01;


if((fl+f) >3.6)
{
 return true;
}

if((pos.y-f) < -3.6 )
{
 return true;
}
return false;
}

void main(void)
{


vec4 col  = vec4(0.0,0.0,0.0,1.0);
//vec3 offsetz=  offsets();



//col.a=1.0;

int is_land = 0;

vec4 base = vec4(0.0,0.0,0.0,1.0);
vec4 city = vec4(0.0,0.0,0.0,1.0);

bool doice = icecap(v_texCoordThree.xyz);

if(rendermode != 2)
{
base = landscapef(v_texCoordThree.xyz);

if(!doice)
    {
city =lights(v_texCoordThree.xyz,base.x);
    }
}


if(rendermode == 0 || rendermode == 3 )
{

  vec4 darksidevec=darksideGlow(city);
  vec4 shinytex = base;
    if(shinytex.x < 0.5)
     {//WATER
        shinytex.x=0.5;//water.
        shinytex.y=0.5;
        shinytex.z=0.5;

      base.a= (base.x*2.0)-.5;

      base.xyz=deepocean;
      base.xyz= mix(base.xyz,shallows, base.a-0.2);

      if(doice)
      {
      base.xyz=ice;
      }

      base*= .4;

        darksidevec = vec4(0.0,0.0,0.0,0.0);
     }
     else
     {//LAND
     is_land=1;



       shinytex.xyz*= 0.0; //a quick way to change it to nothing.
       base.a= (base.x*2.0)-0.5;

     base.xyz= humid;
     base.xyz= mix(base.xyz,dry, base.a);
     base.xyz= mix(base.xyz,ice, base.a-.9);


     base*= .4;

      if(doice)
      {
      base.xyz=ice;
      shinytex.x=0.5;//water.
      shinytex.y=0.5;
      shinytex.z=0.5;
      base*= .4;
      }
      else if (city.x >0.1 )
      {
          base.xyz= vec3(0.5*city.x,0.5*city.x,0.5*city.x) ;
      }




     }

 //  vec4 shinytex = texture2D(textureUnit1, texCoord);
   float  shine = (shinytex.x +shinytex.y +shinytex.z) /3.0;
   //vec4 ambienttexture = texture2D(textureUnit2, texCoord);
   vec3 amb= darksidevec.xyz;

   vec3 L = normalize(lightPositions[0] - v);
   vec3 E = normalize(-v); // we are in Eye Coordinates, so EyePos is (0,0,0)
   vec3 R = normalize(-reflect(L,N));
   //calculate Ambient Term:
   vec4 Iamb = vec4(amb,1);

// calculate Diffuse Term:
   vec4 Idiff =  vec4(uDiffuse,1 )* max(dot(N,L), 0.0);
   Idiff = clamp(Idiff, 0.0, 1.0);


      float res = max(dot(N,L), -0.45);
         res+=0.43;

   if(shine < 0.40)//0.40 LANDMASS
   {
       //  vec4 Idiff =  vec4(uDiffuse,1 )* res;
       vec4 Idiff =  vec4(uDiffuse,1 )* res;
         Idiff = clamp(Idiff, 0.0, 1.0);
     col = (vec4(amb,1.0) *(base*2.0)) + Iamb + (Idiff*(base*2.0));
   }
   else //WATER
   {
        vec4 Idiff =  vec4(uDiffuse,1 )* res;
        Idiff = clamp(Idiff, 0.0, 1.0);

     // calculate Specular Term:                                   //shinyness is here
      vec4 Ispec = vec4(uSpecular,1.0 ) * pow(max(dot(R,E),0.0),9.0*shine);//0.3*shine);///REALLY IMPORTANT
      Ispec = clamp(Ispec, 0.0, 1.0);
      col = (vec4(amb,1.0) *(base*2.5)) + Iamb + (Idiff*(base*2.5)) + Ispec;
   }

}
else if(rendermode == 1)
{
     vec4 darksidevec=vec4(0.0,0.0,0.0,0.0);

     if( base.x > 0.5 && base.x < .9 )//keeps lights out of sea and high places
     {
     if(!doice)
        {
              darksidevec = darksideGlow(city);
        }
     }






     col =darksidevec;
}
col.a=1.0;


if(rendermode ==3)
     {
       col  = vec4(0.0,0.0,0.0,1.0);
     if((nightmode_point < v_texCoordThree.y) ||(nightmode_started == 0)  )
     {

       col.xyz=amberbackground;
       if(is_land ==1 && base.x < .55)
       {

       col.xyz=ambernight*base.x*0.7;

        if(base.x < .08)
        {
        col.xyz= ambernight;
        }
       }


         float aaa= min(nightmode_point,v_texCoordThree.y-1.0 ) ;
         float bbb= max(nightmode_point,v_texCoordThree.y-1.0 ) ;

         float mult= 3.0 - (abs((aaa) - (bbb)));
         mult= max(0.0, mult);
         col.x = max((col.x * mult), col.x);
         col.y = max((col.y * mult), col.y);
         col.z = max((col.z * mult), col.z);

         col.a =  rande(v_texCoordThree);
     }
 }


	gl_FragColor = col;
}