package reseplanerare;

import java.sql.Time;
import java.util.ArrayList;
import java.util.ListIterator;

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
public boolean checkForName(String _name){
	
	ListIterator<Node> it = nodes.listIterator();
	boolean result = false;
	while(it.hasNext()){
		Node currentnode = (Node)it.next();
		String currentName = currentnode.getName();
		//System.out.println(currentName + "===" + _name);
		if(currentName.compareTo(_name) == 0){
			result = true;
			
			break;
		}
		
	}
	
	return result;
}
//---------------------------------------------------------
public Node getNode(String _name){
	
	ListIterator<Node> it = nodes.listIterator();
	Node result = null;
	while(it.hasNext()){
		Node currentnode = (Node)it.next();
		if(currentnode.getName() == _name){
			result = currentnode;
			break;
		}
		
	}
	
	return result;
}
//---------------------------------------------------------------
public void printNodes(){
	
	ListIterator<Node> it = nodes.listIterator();
	while(it.hasNext()){
		Node currentnode = (Node)it.next();
		System.out.print(currentnode.getBussline() + ", ");
		System.out.print(currentnode.getName() + ", ");
		ArrayList<Time> timeList = currentnode.getTime();
		ListIterator<Time> itime = timeList.listIterator();
		while(itime.hasNext()){
			Time currentElement = (Time) itime.next();
			System.out.print(currentElement.toString() + ", ");
		}
		System.out.print("\n");
	}
	
	
}




//---------------------------------------------------------------
}
