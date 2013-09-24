#ifndef LIST_H
#define LIST_H


typedef struct list *List;

List createnewlist(void*);
List addtolist(List,void*);
void updatelist(List,int,void*);
List deletefromlist(List,int);
void *findlist(List,int);


#endif
