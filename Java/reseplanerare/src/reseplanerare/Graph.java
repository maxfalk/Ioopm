package reseplanerare;

import java.util.ArrayList;


public class Graph{
	

private ArrayList <Node> nodes = new ArrayList<Node>();
private ArrayList <Path> paths = new ArrayList<Path>();

//---------------------------------------------------------
public void insertNode(Node _node){
	
	nodes.add(_node);
	
}
//---------------------------------------------------------
public void addlink(Node _fromNode, Node _toNode,Path _path){
	
	if(nodes.contains(_fromNode) == true && nodes.contains(_toNode) == true){
		if(_path.from == _fromNode && _path.to == _toNode){
			paths.add(_path);
		}
	}
	
	
}
//---------------------------------------------------------
public Node getNode(int _bussline, String start){
	
	int len = nodes.size();
	Node[] currentNodes  = new Node[len];
	currentNodes =	(Node[])nodes.toArray();
	
	for(int i = 0; i< len; i++){
		
		if(currentNodes[i].bussline == _bussline){
			
			
		}
		
	}
	
	return ;
}
//---------------------------------------------------------
public Path getPath(Path _path){
	
	int pos = paths.indexOf(_path);
	return paths.get(pos);
	
	
}
//---------------------------------------------------------


	
}
