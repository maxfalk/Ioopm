package reseplanerare;

import java.sql.Time;
import java.util.ArrayList;

//--------------NODE------------------------
public class Node{

	private int bussline;
	private String name;
	private ArrayList<Time> time;

	public Node(int _bussLine,String _name, Time _time){
		bussline = _bussLine;
		name = _name;
		time = new ArrayList<Time>(30);
		time.add(_time);
	}

	public Node(){
		bussline = 0;
		name = "";
		time = new ArrayList<Time>(30);
	}

	public void setNode(int _bussLine,String _name, Time _time){
		bussline = _bussLine;
		name = _name;
		time.clear();
		time.add(_time);

	}

	public String getName(){return this.name;}
	public int getBussline(){return this.bussline;}
	public ArrayList<Time> getTime(){return this.time;}
	public void addTime(Time _time){ time.add(_time);}

}
//------------END NODE-------------------------------
