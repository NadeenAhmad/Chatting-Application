package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {

	DataInputStream in;
	PrintStream out;
	Socket clientSocket;
	boolean closed = false;
	static ArrayList<Connection> connections = new ArrayList();
	static ArrayList<String> members = new ArrayList();
	String client = "";
String name="";
String line="";
boolean bool;
	public Connection(Socket clientSocket, ArrayList<Connection> connections) {
		
		
		
		this.clientSocket = clientSocket;
		this.connections = connections;

	}

	public void  run() {
		
		try {
		
			in = new DataInputStream(clientSocket.getInputStream());
		
		out = new PrintStream(clientSocket.getOutputStream());
		out.println("Type a user name to register in our app.");
	 name = in.readLine().trim();
		boolean x;
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).equalsIgnoreCase(name)) {
				out.println("User name is already taken Type a another user name");
				name = in.readLine().trim();

			}
		}
		if (members.contains(name)) {
			x = true;

		} else {
			x = false;
			out.println("Username is accepted ");

		}

		while (x) {
			out.println("Username is already taken Type a another username");
			name = in.readLine().trim();

			if (members.contains(name)) {
				x = true;

			} else {
				x = false;
				out.println("username is accepted ");

				break;
			}
		}

		client = name;
		members.add(client);
		out.println("Welcome " + name);
		out.println("Guidelines for using our app :");
		out.println("1.To send your message to all participants please type BROADCAST");
		out.println("2.To send your message to particular user type his NAME");
		out.println("3. To know all active members IDs type MEMBERS ");
		out.println("4. To leave our chatroom type BROADCAST  -> BYE ");
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i) != null && connections.get(i) != this) {
				((Connection) connections.get(i)).out
						.println("A new member " + name
								+ " has just registered in our app!!");
			}
		}
		
		while (!closed) {

		out.println("To :");
		 line = in.readLine();
			out.println("Enter your message:");

			String line2 = in.readLine();
			if (line2.equalsIgnoreCase("bye")) {
break;

			}
			else
			
			{
				send(name,line, line2);
		//		if (!send(name,line,line2)){
//					Socket other = new Socket ("localhost",1234);
//					Connection2 test = new Connection2(other , Server2.clients); 
//					Server2.clients.add(test);
//				    test.start();
//               
////			    	test.send("name","broadcast", "test");
////					
//					//other.close();
				// ttl-=1;
//				}
//				else if (){
//				//	System.out.println("love");
//				}
			}
		
}
		
		
		for (int i = 0; i < connections.size(); i++) {
		if (connections.get(i) != null && connections.get(i) != this) {
			((Connection) connections.get(i)).out.println(name
					+ " has just left out chatroom");
		}
	}

	out.println("Bye " + name + " we will miss you !!");
	in.close();
	out.close();
	clientSocket.close();
	for (int i = 0; i < connections.size(); i++) {
		if (connections.get(i) != null && connections.get(i) == this) {

			connections.remove(i);
			members.remove(i);
		}
	}} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getClient() {
		return client;
	}
	
	


public boolean send ( String sender ,String line  , String line2){
	sender = name ;
	

	boolean result = true ;
			
			if (line.equalsIgnoreCase("Broadcast")) {

				for (int i = 0; i < connections.size(); i++) {
					if (connections.get(i) != null
							&& connections.get(i) != this) {
						((Connection) connections.get(i)).out.println(
								sender+ " said: " + line2);
					}
				}

				for (int i = 0; i < connections.size(); i++) {
					if (connections.get(i) != null
							&& connections.get(i) == this) {
						((Connection) connections.get(i)).out
								.println("You said: " + line2);
					}
				}

			}

			else if (line.equalsIgnoreCase("Members")) {
				for (int i = 0; i < connections.size(); i++) {
					if (connections.get(i) != null
							&& connections.get(i) == this) {
						((Connection) connections.get(i)).out
								.println("The registered members on our app are : ");

						for (int j = 0; j < connections.size(); j++) {
							((Connection) connections.get(i)).out
									.println(members.get(j));

						}
					}
				}

			}

			else {
				if (members.contains(line)) {
		result = true ;

					for (int i = 0; i < connections.size(); i++) {

						if (connections.get(i) != null
								&& line.equals(members.get(i))) {
							((Connection) connections.get(i)).out
									.println(sender + " said : " + line2);
						}
					}

					for (int i = 0; i < connections.size(); i++) {
						if (connections.get(i) != null
								&& connections.get(i) == this) {
						 	((Connection) connections.get(i)).out
									.println("You said : " + line2 + " to "
											+ line);
						}
					}
				}
				else {
				result = false ;
					
					for (int i = 0; i < connections.size(); i++) {
						if (connections.get(i) != null
								&& connections.get(i) == this) {
							((Connection) connections.get(i)).out
									.println("this user doesn't exist");
						}
					}
					
					
					
					
					
					
				}

			}


	
	
			return result ;
	
	
	
	
}
}