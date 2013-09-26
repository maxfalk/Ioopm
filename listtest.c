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


  struct test *found = headlist(lista);

  if(found != NULL){
    printf("name: %c\n",found->name);
    printf("age: %d\n",found->age);
  }

  found = headlist(listtail(lista));

  if(found != NULL){
    printf("name: %c\n",found->name);
    printf("age: %d\n",found->age);
  }
  

  List lista2 = listtail(lista);
  found = headlist(lista2);
 
  if(found != NULL){
    printf("name: %c\n",found->name);
    printf("age: %d\n",found->age);
  }else
    puts("inte hittad\n");

  destroylist(lista);

  return 0;

}
