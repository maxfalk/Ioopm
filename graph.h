#ifndef GRAPH_H
#define GRAPH_H

typedef struct graph *Graph;


Graph createnewgraph(void *,int);
Graph addnewnodegraph(Graph,void*,int);
Graph addlink(Graph,int,int,void *);
void * findnode(Graph,int);
void * getpath(Graph,int,int);

#endif
