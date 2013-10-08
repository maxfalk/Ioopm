package reseplanerare;

//-------------PATH------------------------------
public class Path{
	int bussline;
	String fromname;
	String toname;
	int time;
	Node from;
	Node to;



public Path(int _bussLine, String _fromName, String _toName, int _time, Node _from, Node _to){
	bussline = _bussLine;
	fromname = _fromName;
	toname = _toName;
	time = _time;
	from = _from;
	to = _to;
}



}
//----------------END PATH--------------------------------