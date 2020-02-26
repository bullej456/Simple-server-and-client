package uk.co.diegesis.edward.GT2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWorker {
	
	private ServerSocket ss = null;
	private InputStreamReader in = null;
	private BufferedReader bf = null;
	private boolean exit = false;
	
	private int port = DataClass.PORT;
	
	public ServerWorker(int port) {
		this.port = port;
	}
	
	public void startServer() {

		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Socket listen() {
		
		Socket s = null;
		try {
			s = ss.accept();
			System.out.println("Client connected.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public void recieveAndRespond(Socket s) {

		
		try {
			
			in = new InputStreamReader(s.getInputStream());
			bf = new BufferedReader(in);
			
			String str = bf.readLine();
			System.out.println("Client message : " + str);

			String response = "(placeholder)";
			if(str.equals(DataClass.SHUTDOWN_STRING)) {
				response = DataClass.SHUTDOWN_RESPONSE_STRING;
				exit = true;
			}
			else {
				response = str.substring(0, 15).toUpperCase().concat("...received");
			}
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			pr.println(response);
			pr.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void closeServer() {
		
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getExit() {
		return exit;
	}

}
