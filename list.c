#include <stdlib.h>
#include "list.h"
#include <stdio.h>

struct list{
  int key;
  void *info;
  struct list *next;

};


List createnewlist(void *newinfo){

  List mylist = malloc(sizeof(struct list));
  mylist->info = newinfo;
  mylist->key = 0;

  return mylist;

}

List addtolist(List mylist, void *newinfo){
  List newlist = NULL;

  if(mylist != NULL){
    newlist = malloc(sizeof(struct list));
    int key = (mylist->key +1);
    newlist->key = key;
    newlist->info = newinfo;
    newlist->next = mylist;
  }else{
    newlist = createnewlist(newinfo);
  }
  return newlist;

} 

void updatelist(List mylist,int key, void *updatedinfo){

  List cursor = mylist;
  
  while(cursor != NULL){

    if(cursor->key == key){
      free(cursor->info);
      cursor->info = updatedinfo;
    }
    cursor = cursor->next;

  }

}
void destroylist(List mylist){

  List cursor = mylist;

  while(cursor != NULL){
    List nextcursor = cursor->next;
    free(cursor->info);
    free(cursor);
    cursor = nextcursor;

  }


}

List fixkeys(List mylist){

  List cursor = mylist;
  while(cursor != NULL){
    mylist->key = mylist->key -1;
    cursor = cursor->next;
  }

  return mylist;

}
//-------------------------------------------------------------
void *headlist(List mylist){

  return mylist->info;

}
//--------------------------------------------------------------
List listtail(List mylist){

  return mylist->next;

}

