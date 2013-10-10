package reseplanerare;

//-------------PATH------------------------------
public class Path{
	private int bussline;
	private String fromname;
	private String toname;
	private int time;
	private Node from;
	private Node to;

	public Path(int _bussLine, String _fromName, String _toName, int _time, Node _from, Node _to){
		bussline = _bussLine;
		fromname = _fromName;
		toname = _toName;
		time = _time;
		from = _from;
		to = _to;
	}

	public int getBussline(){return bussline;}
	public String getFromName(){return fromname;}
	public String getToName(){return toname;}
	public int getTime(){return time;}
	public Node getFrom(){return from;}
	public Node getTo(){return to;}

}
//----------------END PATH--------------------------------