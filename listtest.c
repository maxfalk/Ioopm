#include "list.h"
#include <stdio.h>
#include <stdlib.h>

struct test{
  char name;
  int age;

};

int main(int argc,char *argv[]){


  struct test *p1 = malloc(sizeof(struct test));  
  p1->name = 'a';
  p1->age = 55;

  struct test *p2 = malloc(sizeof(struct test));
  
  p2->name = 'b';
  p2->age = 14;


  List lista = createnewlist(p1);
  lista = addtolist(lista,p2);


  struct test *found = findlist(lista, 0);
  if(found){
    printf("name: %c\n",found->name);
    printf("age: %d\n",found->age);
  }

  found = findlist(lista, 1);
  if(found){
    printf("name: %c\n",found->name);
    printf("age: %d\n",found->age);
  }

  lista = deletefromlist(lista,0);

  found = findlist(lista,0);
  if(found == NULL) puts("Borta\n");


  found = findlist(lista, 1);
  if(found){
    printf("name: %c\n",found->name);
    printf("age: %d\n",found->age);
  }else
    puts("inte hittad\n");

  //free(p1);
  //free(p2);
  destroylist(lista);

  return 0;

}
