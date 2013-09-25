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

  List newlist = malloc(sizeof(struct list));
  int key = (mylist->key +1);
  newlist->key = key;
  newlist->info = newinfo;
  newlist->next = mylist;

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

List deletefromlist(List mylist, int key){

  List cursor = mylist;
  List lastcursor = NULL;

  while(cursor != NULL){
    printf("Hkey:%d, inkey: %d",cursor->key,key);
    if(cursor->key == key){
      if(lastcursor == NULL){
	List firstcursornext = cursor->next;
	free(cursor->info);
	free(cursor);
	printf("key:%d\n",key);
	fixkeys(firstcursornext);
	cursor = firstcursornext;	
	break;

      }else{
	lastcursor->next = cursor->next;
	fixkeys(cursor->next);
	free(cursor->info);
	free(cursor);
	
	cursor = mylist;
	
	break;
      }


    }
    puts("Looping\n");
    lastcursor = cursor;
    cursor = cursor->next;

  }


  return cursor;

}

void *findlist(List mylist, int key){

  List cursor = NULL; 
  cursor = mylist;
  void *value = NULL;

  while(cursor != NULL){
    if(cursor->key == key){
      value = cursor->info;
      
    }

    cursor = cursor->next;
  }

  return value;

}
