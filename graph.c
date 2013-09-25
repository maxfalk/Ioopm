



typedef struct node{
  int name;
  list paths;
  void *data;

}*Node;



typedef struct graph{
  list nodes;


}*Graph;

Graph createnewgraph(void *data){

  Graph mygraph = malloc(sizeof(struct graph));
  Node mynode = malloc(sizeof(struct node));
  List nodes = createnewlist(mynode);
  mygraph->nodes = nodes;
  Node currentnode = findlist(mygraph->nodes,0);
  currentnode->data = data;

  return mygraph;

}

Graph addnewnodegraph(Graph mygraph, void *data){

  Node mynode = malloc(sizeof(struct node));
  List nodes = addtolist(mygraph->nodes, mynode);
  mygraph->nodes = nodes;
  
  int pos = 0;
  Node currentnode = NULL;  
  while((currentnode = findlist(mygraph->nodes,pos)) != NULL){
  
    currentnode->data = data;
  }

  return mygraph;

}

Graph linknodes(Graph mygraph, int keynode, void *data){

  List pathlist = findlist(mygraph->nodes, keynode);
  List nodes = addtolist(pathlist, data);
  List newlist = updatelist(mygraph->nodes, keynode, nodes);
  
  return mygraph;
}

/*procedure DFS(G,v):
      label v as explored
      for all edges e in G.adjacentEdges(v) do
         if edge e is unexplored then
              w ¡û G.adjacentVertex(v,e)
              if vertex w is unexplored then
                  label e as a discovery edge
                  recursively call DFS(G,w)
              else
                label e as a back edge
*/




void *findnode(Graph mygraph, int key){

  List pathlist = findlist(mygraph->nodes, key);


}
