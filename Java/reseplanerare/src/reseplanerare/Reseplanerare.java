package reseplanerare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reseplanerare {

	//--------------------------------------------------------------
	private static Path setPathData(int _bussLine, String _fromName, String _toName, int _time, Node _from, Node _to){
		Path path = new Path(_bussLine, _fromName, _toName, _time, _from, _to);
		
		return path;
		
	}
	//--------------------------------------------------------------
	private static Node setNodeData(int _bussLine, String _name, String _time){
		
		Node mynode = new Node(_bussLine,_name, _time);	
		
		return mynode;
	}
	//---------------------------------------------------------------
	private static String[] mySplit(String line, String regexp){
		
		return line.split(regexp);
	}	
	//------------------------------------------------------------------
	private static void getStart(String aFileName, Graph myGraph) throws NumberFormatException, IOException{
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(aFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String currentLine = null;
			while((currentLine = br.readLine()) != null){
				String [] splitString = mySplit(currentLine,",");
				Node myNode = setNodeData(Integer.parseInt(splitString[0]),splitString[1],splitString[2]);
				myGraph.insertNode(myNode);
				System.out.println(Integer.parseInt(splitString[0]));
				
			}
		
			
			
	}
	//---------------------------------------
	//------------------------------------------------------------------
	private static void getNetwork(String aFileName, Graph myGraph) throws NumberFormatException, IOException{
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(aFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String currentLine = null;
			while((currentLine = br.readLine()) != null){
				String [] splitString = mySplit(currentLine,",");
				
				int bussLine = Integer.parseInt(splitString[0]);
				String fromName =splitString[1];
				String toName = splitString[2];
				int time = Integer.parseInt(splitString[3]);
				//Search for from and to node
				Path myPath = setPathData();
				myGraph.insertNode(myNode);
				System.out.println(Integer.parseInt(splitString[0]));
				
			}
		
			
			
	}
	//---------------------------------------------------------------
	public static void main(String [] arg) throws NumberFormatException, IOException{
		Graph mygraph = new Graph();
		getNetwork("H:\\Users\\Max\\workspace\\reseplanerare\\src\\reseplanerare\\start.txt", mygraph);
		
		
		
		
	}
	
}
