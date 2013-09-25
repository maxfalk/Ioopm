#include <stdlib.h>
#include <stdio.h>
#include "list.h"
#include "graph.h"


typedef struct node{
  int name;
  List paths;
  void *data;

}*Node;

typedef struct path{
  int name;
  void *data;

}*Path;

struct graph{
  List nodes;
};


//--------------------------------------------------------------------
Graph createnewgraph(void *data, int name){

  Graph mygraph = malloc(sizeof(struct graph));
  Node mynode = malloc(sizeof(struct node));
  mynode->name = name;
  mynode->paths = NULL;
  mynode->data = data;
  mygraph->nodes = createnewlist(mynode);
  
  return mygraph;

}
//-------------------------------------------------------------------
Graph addnewnodegraph(Graph mygraph, void *data, int name){

  Node mynode = malloc(sizeof(struct node));
  mynode->name = name;
  mynode->paths = NULL;
  mynode->data = data;
  mygraph->nodes = addtolist(mygraph->nodes, mynode);

  return mygraph;

}
//--------------------------------------------------------------
Node searchfornode(Graph mygraph, int name){

  List cursor = mygraph->nodes;
  Node result = NULL;
  while(cursor != NULL){
    Node mynode = headlist(cursor);

    if(mynode->name == name){
      result = mynode;
      break;
    }else{
      cursor = listtail(cursor);
    }


  }

  return result;

}
//---------------------------------------------------------------
Graph addlink(Graph mygraph, int fromname,int toname, void *data){
  
  Node mynode = searchfornode(mygraph,fromname);
  if(mynode != NULL){
    Path mypath = malloc(sizeof(struct path));
    mypath->name = toname;
    mypath->data = data;
    mynode->paths = addtolist(mynode->paths,mypath);
    
  }else{
    perror("Node not found!");
  }
 

  
  return mygraph;
}
//---------------------------------------------------------------------
void *findnode(Graph mygraph, int name){

  Node mynode = searchfornode(mygraph,name);
  
  return mynode->data;

}
//--------------------------------------------------------------------
void destroy(Graph mygraph){



}
//-------------------------------------------------------------------
//Hasha namn till noder kanske?
unsigned int murmurHash2(const void *key,int len,unsigned int seed){



  const unsigned int m = 0x5bd1e995;
  const int r = 24;
  const unsigned char * data = (const unsigned char *) key;

  while(len >= 4){

    unsigned int k = *(unsigned int *) data;
    
    k *= m;
    k ^= k >> r;
    k *= m;
    
    h *= m;
    h ^= k;

    data += 4;
    len -= 4;


  }

  switch(len){
  case 3: h ^= data[2] << 16;
  case 2: h ^= data[1] << 8;
  case 1: h ^= data[0];
  default: h *=m;
  };

  h ^= h >> 13;
  h *= m;
  h ^ = h >> 15;


  return h;


}
