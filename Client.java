package chat;

	import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Client extends JFrame implements Runnable {

	  static Socket clientSocket ;
	  
	 static PrintStream out;

	 static DataInputStream in;

	 static BufferedReader inputLine ;
	   static boolean closed = false;
	   public Client(){
				   }

	  
	  public static void main(String[] args) {
		  
		
		  
		  if (args.length < 2) {
		      System.out
		          .println("Welcome to WeChat app !!");
		    } else {
		    }

	    try {
	      clientSocket = new Socket("localhost",6789);
	      inputLine = new BufferedReader(new InputStreamReader(System.in));
	      out = new PrintStream(clientSocket.getOutputStream());
	      in = new DataInputStream(clientSocket.getInputStream());
	    } catch (IOException e) {
	        System.out.println(e);
	      }

	    if (clientSocket != null && out != null && in != null) {
	      try {

	    
	        new Thread(new Client()).start();
	        while (!closed) {
	          out.println(inputLine.readLine().trim());
	        }
	        
	        out.close();
	        in.close();
	        clientSocket.close();
	      } catch (IOException e) {
	        System.err.println("IOException:  " + e);
	      }
	    }
	  }

	
	  public void run() {
	   
	    String reply;
	    try {
	      while ((reply = in.readLine()) != null) {
	        System.out.println(reply  );
	       
	      }
	      closed = true;
	    } catch (IOException e) {
	    }
	  }
	}

