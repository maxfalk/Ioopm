package reseplanerare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;

import javax.print.DocFlavor.URL;

public class Reseplanerare {

	//--------------------------------------------------------------
	public static Path setPathData(int _bussLine, String _fromName, String _toName, int _time, Node _from, Node _to){
		Path path = new Path(_bussLine, _fromName, _toName, _time, _from, _to);
		return path;
	}
	//--------------------------------------------------------------
	public static Node setNodeData(int _bussLine, String _name, Time _time){
		Node mynode = new Node(_bussLine,_name, _time);	
		return mynode;
	}
	//---------------------------------------------------------------
	public static String[] mySplit(String line, String regexp){

		return line.split(regexp);
	}	
	//-----------------------------------------------------------------------------
	private static Time convertToTime(String time){
		time = time.replaceAll(" ", "");
		time = time + ":00";
		Time myTime = Time.valueOf(time);
		return myTime;
	} 
	//------------------------------------------------------------------
	static void getStart(InputStream stream, Graph myGraph) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		String currentLine = null;
		Node myNode = null;
		while((currentLine = br.readLine()) != null){
			currentLine = currentLine.replaceAll(" ", "");
			String [] splitString = mySplit(currentLine,",");
			int _bussline = Integer.parseInt(splitString[0]);
			String _name = splitString[1];
			Time _time = convertToTime(splitString[2]);

			if(myGraph.checkForName(_name) == false){
				myNode = setNodeData(_bussline, _name, _time);
				myGraph.insertNode(myNode);		
			}else{
				myNode.addTime(_time);
			}



		}



	}
	//---------------------------------------
	//------------------------------------------------------------------
	static void getNetwork(InputStream stream, Graph myGraph) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		String currentLine = null;
		Node fromNode = null;
		Node toNode = null;
		while((currentLine = br.readLine()) != null){
			currentLine = currentLine.replaceAll(" ", "");
			//Fix line from file, format it
			String [] splitString = mySplit(currentLine,",");
			int bussLine = Integer.parseInt(splitString[0]);
			String fromName =splitString[1];
			String toName = splitString[2];
			int time = Integer.parseInt(splitString[3]);
			//Search for from and to node
			
			fromNode = myGraph.getNode(fromName);
			toNode = myGraph.getNode(toName);
			
			//add path to graph
			Path myPath = setPathData(bussLine,fromName,toName,time,fromNode, toNode);
			myGraph.addlink(myPath);

		}



	}
	//---------------------------------------------------------------
	public static void main(String [] arg) throws IOException{
		Graph mygraph = new Graph();
		InputStream inputStart = Reseplanerare.class.getResourceAsStream("start.txt");
		InputStream inputNetwork = Reseplanerare.class.getResourceAsStream("network.txt");

		getStart(inputStart, mygraph);
		getNetwork(inputNetwork, mygraph);
		//mygraph.printNodes();
		//mygraph.printPaths();



	}

}
