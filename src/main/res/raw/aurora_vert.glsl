precision mediump float;

// VERTEX SHADER
uniform mat4 modelViewProjectionMatrix;
 uniform mat4 modelViewMatrix;
uniform sampler2D textureUnit0;

uniform  float u_time;
#define PI 3.14159

attribute vec2 texture0;
attribute vec4 position;
varying vec2 v_texCoord;
attribute vec3 normal;

varying  float alphachan;


varying float Thickness;

void main()
{

 vec3   camNormal =  normalize(modelViewMatrix * vec4(normal, 0.0)).xyz;
     vec3 v3Ray= -modelViewMatrix[3].xyz;
     v3Ray= normalize(v3Ray);

     Thickness  = 1.0 -dot(v3Ray,camNormal );



v_texCoord.x = texture0.x;
v_texCoord.y = texture0.y;
float time =u_time*0.5;

vec3  nrm = vec3(0.0,0.0,0.0);

nrm.x = -(normal.x*2.4);
nrm.y = -(normal.y*2.4);
nrm.z = -(normal.z*2.4);
//This checks if the arra is one to be use for auroa, if not then we just ignore all the calculations.
vec4 col = texture2D(textureUnit0 , v_texCoord);
float distance = col.a;

         if(distance > 0.0)
         {

     vec2 u_k = vec2(60,60);

       float v = 0.0;
       vec2 c = texture0 * u_k - u_k/2.0;
       v += sin((c.x+time));
       v += sin((c.y+time)/2.0);
       v += sin((c.x+c.y+time)/2.0);
       c += u_k/2.0 * vec2(sin(time/3.0), cos(time/2.0));
       //v += sin(sqrt(c.x*c.x+c.y*c.y+1.0)+time);
       v = v*0.5;
       v= v*0.1;

       v = sin(v*(5.0*PI));

     // v = v*0.5;

       nrm.x = (normal.x*v);
       nrm.y = (normal.y*v);//*v);
       nrm.z = (normal.z*v);

       float dc = dot(nrm,normal);


     if((dc > 0.2) || (dc < -0.2) )
        {
        alphachan =0.0;
        }
           else
        {
            alphachan =1.0;
        }
    }
gl_Position =  modelViewProjectionMatrix * vec4(position.xyz+nrm,1.0);
}



 
 
