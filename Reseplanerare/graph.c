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
  int weight;
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
//-------------------------------------------------------------
Path searchforpath(Graph mygraph, int nodename, int pathname){

  Node mynode = searchfornode(mygraph,nodename);
  List paths = mynode->paths;
  List cursor = paths;
  Path result = NULL;

  while(cursor != NULL){
    Path mypath = headlist(cursor);

    if(mypath->name == pathname){
      result = mypath;
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
//--------------------------------------------------------------------
void *getpath(Graph mygraph,int nodename, int pathname){
  
  Path mypath = searchforpath(mygraph,nodename,pathname);
  return mypath->data;


}
//---------------------------------------------
/*
Let the node at which we are starting be called the initial node. Let the distance of node Y be the distance from the initial node to Y. Dijkstra's algorithm will assign some initial distance values and will try to improve them step by step.

1.    Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes.

2.    Mark all nodes unvisited. Set the initial node as current. Create a set of the unvisited nodes called the unvisited set consisting of all the nodes.


.3    For the current node, consider all of its unvisited neighbors and calculate their tentative distances. For example, if the current node A is marked with a distance of 6, and the edge connecting it with a neighbor B has length 2, then the distance to B (through A) will be 6 + 2 = 8. If this distance is less than the previously recorded tentative distance of B, then overwrite that distance. Even though a neighbor has been examined, it is not marked as "visited" at this time, and it remains in the unvisited set.
  
4.  When we are done considering all of the neighbors of the current node, mark the current node as visited and remove it from the unvisited set. A visited node will never be checked again.


5.    If the destination node has been marked visited (when planning a route between two specific nodes) or if the smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal; occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.
 

6.   Select the unvisited node that is marked with the smallest tentative distance, and set it as the new "current node" then go back to step 3.
*/
//-------------------------------------------------------------
void searchshortestpath(Graph mygraph,int fromname, int toname){

  int initnode = fromname;
  int rotweight = 0;


  typedef struct pcd{
    int visited;
    int weight;
  }*Pcd;

  //rot
  Pcd mydata = malloc(sizeof(struct pcd));
  mydata->visited = 1;
  mydata->weight = 0;
  List visited = createnewlist(mydata);
  Node mynode = searchfornode(mygraph,fromname);
  
  //alla noder vi går till

  List paths = mynode->paths;
  List cursor = paths;

    //iterera över alla pathlists o alla noder,
  while(){

     
  


    //nuvarande nodes pathlist och sätt in vikter
    while(cursor != NULL){
      Path mypath = headlist(cursor);
      int weight = mypath->weight;

      Pcd mydata = malloc(sizeof(struct pcd));

      mydata->weight = rotweight + weight;
      visited = addtolist(visited,visit);
      
      cursor = listtail(cursor);

    }
  


    //mydata->visited = 1;
    


    



}








}
//-------------------------------------------------------------------
//Hasha namn till noder kanske?
/*unsigned int murmurHash2(const void *key,int len,unsigned int seed){



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
*/
