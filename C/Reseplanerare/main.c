#include <stdio.h>
#include <stdlib.h>
#include "graph.h"

int main(int argc, char *argv[]){

  int one = 1;
  int two = 2;
  int three = 3;

  int linkcost = 5;

  Graph myg = createnewgraph(&one,0);
  myg = addnewnodegraph(myg,&two,1);
  myg = addnewnodegraph(myg,&three,2);



  int *result = findnode(myg,0);
  printf("Result: %d\n",*result);
  result = findnode(myg,1);
  printf("Result: %d\n",*result);

  result = findnode(myg,2);
  printf("Result: %d\n",*result);

  addlink(myg,0,1,&linkcost);


  result = getpath(myg,0,1);
  printf("Result: %d\n",*result);


  return 0;
}
