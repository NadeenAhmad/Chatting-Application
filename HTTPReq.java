package chat;



import java.io.Serializable;

public class HTTPReq implements Serializable  {
	String Method;
	String URL;
	String Version ;
	String Host ;
	 String Accepted_format ;
	String Connection_Status ;
	
	
	public HTTPReq(String Method , String URL, String version , String Host , String Accepted_format,String Connection_Status) {
	this.Method = Method ;
	this.URL= URL ;
	this.Version = version ;
	this.Host = Host ;
	this.Accepted_format = Accepted_format;
	this.Connection_Status = Connection_Status ;
	
	}
	public void printReq(){
		System.out.println("Method " +Method + " URL "+ URL+" Version " +Version);
		System.out.println("Host " + Host);
		System.out.println("Accepted format "+ Accepted_format);
		System.out.println("Connection "+Connection_Status);
	}
}
