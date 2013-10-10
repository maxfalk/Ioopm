#include <stdio.h>

#define paste(front,back) front ## back
#define swap(t, x, y) t temp = x; x = y; y = temp;

int main(int argc, char * argv[])
{
  int f = 5;
  int p = 10;
  swap(int, p,f);
  printf("%d,%d",f,p);
  return 0;

}
