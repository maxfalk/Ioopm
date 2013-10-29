package reseplanerare;

import java.sql.Time;

//-------------PATH------------------------------
public class Path{
	private int bussline;
	private String fromname;
	private String toname;
	private Time time;
	private Node from;
	private Node to;

	public Path(int _bussLine, String _fromName, String _toName, Time _time, Node _from, Node _to){
		bussline = _bussLine;
		fromname = _fromName;
		toname = _toName;
		time =_time;
		from = _from;
		to = _to;
	}

	public int getBussline(){return bussline;}
	public String getFromName(){return fromname;}
	public String getToName(){return toname;}
	public Time getTime(){return time;}
	public Node getFrom(){return from;}
	public Node getTo(){return to;}

}
//----------------END PATH--------------------------------