package chat;


import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {
static ServerSocket serverSocket ;
static Socket clientSocket ;
static ArrayList<Connection> clients = new ArrayList();

  public static void main(String args[]) throws UnknownHostException, IOException {

    
    int portNumber = 6789;
 int port2=1234;
  try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    while (true) {
      try {
        clientSocket = serverSocket.accept();
        	  Connection r  = new Connection(clientSocket, clients);
            clients.add(r);
            String x = r.getClient();
          
         r.start();
        
      } catch (IOException e) {
        System.out.println(e);
      }
      

    }
    


    

  }

public static ServerSocket getServerSocket() {
	return serverSocket;
}

public static void setServerSocket(ServerSocket serverSocket) {
	Server.serverSocket = serverSocket;
}


}




