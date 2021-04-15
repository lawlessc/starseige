//precision highp float;
#extension GL_OES_standard_derivatives : enable
// FRAGMENT SHADER
precision lowp float;


//uniform sampler2D textureUnit0;
uniform int glow_on;

varying mediump vec2 v_texCoord;
uniform lowp  int rendermode;
varying mediump vec3 v_texCoordThree;


//float hash( const in float n ) {
//    return fract(sin(n)*43758.5453123);
//}

highp float hash(vec3 p, float z)  // replace this by something better
{
    p  = fract( p*0.3183099+.1 );
	p *= z;
    return fract( p.x*p.y*p.z*(p.x+p.y+p.z) );
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


const mat3 m = mat3( 0.00,  0.80,  0.60,
                    -0.80,  0.36, -0.48,
                    -0.60, -0.48,  0.64 );




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




//-----------------------------------------------------
//Based on Stars (by // @reindernijhoff
//-----------------------------------------------------

vec4 renderStars( const in vec3 rd ) {
	vec3 rds = normalize(rd);
	vec3 col = vec3(0);

     float s;
     vec3 q = 130.0*rds; //the higher the number is here the more grainy it looks
     s  = 0.5000*noise( q ); q = m*q*2.01;
     s += 0.0625*noise( q ); q = m*q*2.02;
;
    if (s > 0.5) {
        vec3 backStars = vec3(s)*.5 * vec3(0.95,0.95,0.95);
        col += backStars;
    }
	return   vec4( col, 1 );
}


void main()
{


vec4 col  = vec4(0.0,0.0,0.0,1.0);
if(rendermode == 0)
{
col = renderStars(v_texCoordThree);
}

if(rendermode == 1)
{
col = renderStars(v_texCoordThree);
}

if(rendermode == 2)
{//i have no idea why i did this// leave this in as it is probably for during the sunbeam effect
 col = vec4(0.07,0.07,0.07,1.0);
}

//float randout= rand(v_texCoord);
//randout= randout/3.0;
//col  = vec4(col.x-randout ,col.y-randout,col.z-randout,1.0);
 gl_FragColor = col;




}


