package reseplanerare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.Calendar;

import javax.print.DocFlavor.URL;

public class Reseplanerare {

	//--------------------------------------------------------------
	public static Path setPathData(int _bussLine, String _fromName, String _toName, Time _time, Node _from, Node _to){
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
		Time myTime = Time.valueOf(time);
		return myTime;
	} 
	//-------------------------------------------------------------------------------
	private static Time addTimes(Time myTime, Time addTime){
		int minutes = addTime.getMinutes();
		Calendar cal = Calendar.getInstance();
		cal.setTime(myTime);
		cal.add(Calendar.MONDAY, minutes);
		
		return (Time) cal.getTime();
		
	}
	//------------------------------------------------------------------------------
	public static String fotmateToTime(int hours, int minutes, int seconds){
		
		String formate = "";

		int[] checkInt = {hours,minutes,seconds};
		
		for(int i = 0; i < checkInt.length; i++){
			if(checkInt[i] < 60){
				if(checkInt[i] < 10){
					formate += "0" + Integer.toString(checkInt[i]);
				}else{
					formate += Integer.toString(checkInt[i]);
				}
				if(i != 2)
					formate = formate + ":";
			}
		}
		

		return formate;
		
	}

	//------------------------------------------------------------------
	static void getStart(InputStream stream, Graph myGraph) throws IOException, FileNotFoundException{

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));

		String currentLine = null;
		Node myNode = null;
		while((currentLine = br.readLine()) != null){
			
			String [] splitString = mySplit(currentLine,",");
			//bussline
			int _bussline = Integer.parseInt(splitString[0].trim());
			//Name
			String _name = splitString[1].trim();
			//Fixa time fromat
			splitString[2] = splitString[2].trim();
			splitString[2] = splitString[2] +":00";
			Time _time = convertToTime(splitString[2]);
			//checkif node exists
			if(myGraph.checkForName(_name) == false){
				myNode = setNodeData(_bussline, _name, _time);
				myGraph.insertNode(myNode);		
			}else{
			//add just the time to the node
				myNode.addTime(_time);
			}



		}
		//Close stream
		br.close();

	}
	//---------------------------------------
	//------------------------------------------------------------------
	static void getNetwork(InputStream stream, Graph myGraph) throws IOException, FileNotFoundException{

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String currentLine = null;
		Node fromNode = null;
		Node toNode = null;
		
		while((currentLine = br.readLine()) != null){
			//Fix line from file, format it
			String [] splitString = mySplit(currentLine,",");
			//bussLine
			int bussLine = Integer.parseInt(splitString[0].trim());
			//fromName
			String fromName =splitString[1].trim();
			//toName
			String toName = splitString[2].trim();
			//Time
			splitString[3] = splitString[3].trim();
			int intTimeMinutes = Integer.parseInt(splitString[3]);
			String stringTime = fotmateToTime(0,intTimeMinutes,0);
			Time time = convertToTime(stringTime);
			
			//Search for from and to node
			fromNode = myGraph.getNode(fromName);
			toNode = myGraph.getNode(toName);
			
			//add node if it isn't there already
			if(fromNode == null){
				Node myNode = setNodeData(bussLine, fromName, time);
				myGraph.insertNode(myNode);		
				fromNode = myGraph.getNode(fromName);
				
			}
			
			//add node if it isn't there already
			if(toNode == null){
				Node myNode = setNodeData(bussLine, toName, time);
				myGraph.insertNode(myNode);		
				toNode = myGraph.getNode(toName);
			}
			
			//add path to graph
			Path myPath = setPathData(bussLine,fromName,toName,time,fromNode, toNode);
			myGraph.insertPath(myPath);

		}
		//close stream
		br.close();

	}
	//--------------------------------------------------------------------------------
	static void travel(Graph myGraph, String fromName, String toName){
		Node fromNode = myGraph.getNode(fromName);
		Path shortest = myGraph.getShortestPath(fromName);
		
		
	}
	//------------------------------------------------------------------------------
	public static void main(String [] arg) throws IOException{
		Graph mygraph = new Graph();
		InputStream inputStart = Reseplanerare.class.getResourceAsStream("start.txt");
		InputStream inputNetwork = Reseplanerare.class.getResourceAsStream("network.txt");

		getStart(inputStart, mygraph);
		getNetwork(inputNetwork, mygraph);
		
		mygraph.printNodes();
		mygraph.printPaths();



	}

}
