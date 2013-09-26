#ifndef LIST_H
#define LIST_H


typedef struct list *List;



void *headlist(List);
List createnewlist(void*);
List addtolist(List,void*);
void updatelist(List,int,void*);
void destroylist(List);
List listtail(List);

#endif
