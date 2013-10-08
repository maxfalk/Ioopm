#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#ifdef ANIMATE
#include <X11/Xlib.h>
#include <X11/Xutil.h>

#define X_SIZE 800
#define Y_SIZE 800
#endif

#define prec float
#define PI 3.14159265359
#define NG 6.67e-11 // Newtons gravitationskonstant

static prec gdt = 0.00001;
float t;



typedef struct {
  prec x;
  prec y;
  prec forcex;
  prec forcey; 
  prec accx;
  prec accy;
  prec velocityx;
  prec velocityy;  
  prec mass;
} body;

static void copyToXBuffer(body* star, XPoint* points, int N);
prec calculateacceleration(prec force,prec mass);
static void resetForce(body* b);
static void update(body* a, prec dt);
static void addForce(body* a, body* b);
static prec newRand() ;
void init(int N, body* star);
prec distance(body *starx, body *stary);
prec calculateforce(body *starx, body *stary);
prec deltatime(int oldtime);


static void update(body* a, prec dt)
{
  
  a->accx = calculateacceleration(a->forcex,a->mass);
  a->accy = calculateacceleration(a->forcey,a->mass);
  a->velocityx = (a->accx*dt);
  a->velocityy = (a->accy*dt);

  a->y += (a->velocityy * t) + ((a->accy*pow(dt,2))/2);
  a->x += (a->velocityx * t) + ((a->accx*pow(dt,2))/2);
}

static void resetForce(body* b)
{

  b->forcex = 0;
  b->forcey = 0;
} 

static void addForce(body* a, body* b)
{
  prec force = calculateforce(a, b);
  prec xdiff = (a->x-b->x);
  prec ydiff = (a->y-b->y);

  prec xydiff = abs(xdiff) + abs(ydiff);
  prec forcerat = (force/xydiff);

  a->forcex += (xdiff*forcerat);
  a->forcey +=(ydiff*forcerat);

  b->forcex += (-1)*(a->forcex);
  b->forcey += (-1)*(a->forcey);

}
//rand num between 0 and 1.
static prec newRand() 
{
	prec r = (prec)((double)rand()/(double)RAND_MAX);
	return r;
}
//initiate starts, give mass, pos and velocity
void init(int N, body* star)
{
  srand( time(NULL));

  for(int i=0;i<N;i++){
    star[i].x = 300 +(rand()%200);
    star[i].y = 300 + (rand()%200);
    star[i].velocityy = newRand();
    star[i].velocityx = newRand();
    star[i].mass = 50;//newRand();
  }
}
/*
static void updateForces(int N, body* star)
{


}
*/
 //calculate distance between two stars
prec distance(body *starx, body *stary)
{
  prec xdiff = (starx->x-stary->x);
  prec ydiff = (starx->y-stary->y);
  xdiff = pow(xdiff,2);
  ydiff = pow(ydiff,2);

  return sqrt(xdiff+ydiff);

}
//calculate force between two stars
prec calculateforce(body *starx, body *stary)
{
  prec dist = distance(starx,stary);
  prec xymass = (starx->mass*stary->mass);
  //printf("massxy:%f, dist: %f\n", xymass,(dist));
  return (NG*(xymass/dist));//(NG*(xymass/pow(dist,2)));

}
//calculate acceleration
prec calculateacceleration(prec force,prec mass)
{

  return (force/mass);

}
//put star positions in point to be drawn.
#ifdef ANIMATE
static void copyToXBuffer(body* star, XPoint* points, int N)
{

  for(int i=0;i<N;i++){  
    points[i].x = (int) star[i].x;
    points[i].y = (int) star[i].y;
  }

}
#endif

int main(int argc, char* argv[]) {

	int N = 2;
	int iter = 50000;
	
	if(argc == 3)
	{
	  N = atoi(argv[1]);
	  iter = atoi(argv[2]);
	}
	
	body *star = malloc(sizeof(body)*N);// allocate mem for all the stars
	init(N,star); // initiate stars

	#ifdef ANIMATE
	XPoint* points = malloc(sizeof(XPoint)*N);
	Display* disp;
	Window window, rootwin;
	int screen;

	disp = XOpenDisplay(NULL);
	screen = DefaultScreen(disp);
	rootwin = RootWindow(disp,screen);
	window = XCreateSimpleWindow(disp,rootwin,
				0,0,X_SIZE,Y_SIZE,1,0,0);
	GC gc = XCreateGC(disp, window, 0, 0);
	XSetForeground(disp, gc, WhitePixel(disp, screen));
	XSetBackground(disp, gc, BlackPixel(disp, screen));
	XMapWindow(disp,window);

	XClearWindow(disp,window);	
	
	copyToXBuffer(star, points, N);


	XDrawPoints(disp, window, gc, points, N, 0);

	XFlush(disp);

	#endif
       	t = gdt;
	clock_t start = clock();
	for(int i = 0; i < iter; i++)
	{

		#ifdef ANIMATE
		copyToXBuffer(star, points, N);
		XDrawPoints(disp, window, gc, points, N, CoordModeOrigin);		
		XClearWindow(disp,window);	
		#endif

		//reset force for all stars
		for(int i = 0; i<N;i++){
		  resetForce(star);
		}
		//Calculate forces for all the stars
		for(int i = 0; i <N;i++)
		  {
		    for(int j = 0; j <N;j++)
		      {
			if (i!=j)
			  addForce(star+i, star+j);
		    
		      } 	
		    //Update stars positions
		    update(star+i,gdt);	
		    //increase time by delta time
		    t += gdt;
		    
		  }
		//    printf("iteration: %d. X:%f Y: %f VelocityXY:%f,%f accXY: %f, %f ForceXY: %f, %f\n",0,(star)->x,(star)->y,(star)->velocityx,(star)->velocityy,(star)->accx,(star)->accy,(star)->forcex,(star)->forcey);
	}
	clock_t stop = clock();
	float diff = (float)(stop - start)/CLOCKS_PER_SEC;

	printf("Total: %lf seconds\n",diff);
	printf("Bodies: %d\n",N);
	printf("Iterations: %d\n", iter);

	#ifdef ANIMATE
	XCloseDisplay(disp);
	#endif

	return 0;
}
