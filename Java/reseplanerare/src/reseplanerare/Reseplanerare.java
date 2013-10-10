package reseplanerare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;

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
			private static Time convertToTime(String time){
				time = time.replaceAll(" ", "");
				time = time + ":00";
				Time myTime = Time.valueOf(time);
				return myTime;
			} 
			//------------------------------------------------------------------
			static void getStart(String aFileName, Graph myGraph) throws NumberFormatException, IOException{
				
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(aFileName));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
							try{
								myNode.addTime(_time);
							}catch(NullPointerException e){
								System.err.print("Nullpointer, error!\n");
								
							}
						}

					
						
					}
				
					
					
			}
			//---------------------------------------
			//------------------------------------------------------------------
			static void getNetwork(String aFileName, Graph myGraph) throws NumberFormatException, IOException{
				
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(aFileName));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String currentLine = null;
					while((currentLine = br.readLine()) != null){
						currentLine = currentLine.replaceAll(" ", "");
						//Fix line from file, format it
						String [] splitString = mySplit(currentLine,",");
						int bussLine = Integer.parseInt(splitString[0]);
						String fromName =splitString[1];
						String toName = splitString[2];
						int time = Integer.parseInt(splitString[3]);
						//Search for from and to node
						Node fromNode = myGraph.getNode(fromName);
						Node toNode = myGraph.getNode(toName);
						//add path to graph
						Path myPath = setPathData(bussLine,fromName,toName,time,fromNode, toNode);
						myGraph.addlink(fromNode,toNode, myPath);
						
					}
				
					
					
			}
		
	
	
	//---------------------------------------------------------------
	public static void main(String [] arg) throws NumberFormatException, IOException{
		Graph mygraph = new Graph();
		getStart("H:\\Users\\Max\\workspace\\reseplanerare\\src\\reseplanerare\\start.txt", mygraph);
		getNetwork("H:\\Users\\Max\\workspace\\reseplanerare\\src\\reseplanerare\\network.txt", mygraph);
		mygraph.printNodes();
		
		
		
	}
	
}
