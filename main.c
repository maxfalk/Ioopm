#include <stdio.h>
#include <stdlib.h>
#include "graph.h"

int main(int argc, char *argv[]){

  int one = 1;
  int two = 2;
  int three = 3;

  Graph myg = createnewgraph(&one,0);
  myg = addnewnodegraph(myg,&two,1);
  myg = addnewnodegraph(myg,&three,2);



  int *result = findnode(myg,1);
  printf("Result: %d\n",*result);


  return 0;
}
