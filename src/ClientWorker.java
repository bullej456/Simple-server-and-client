package uk.co.diegesis.edward.GT2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWorker {
	
	private Socket s = null;
	private InputStreamReader in = null;
	private BufferedReader bf = null;
	private String response = null;
	
	private String ipAddress = DataClass.IP;
	private int port = DataClass.PORT;
	
	public ClientWorker(String ipAddress, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public void startClient() {

		try {
			s = new Socket(ipAddress, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageWithResponse(String message) {
		
		try {
			PrintWriter pr;
			pr = new PrintWriter(s.getOutputStream());
			pr.println(message);
			pr.flush();
			
			in = new InputStreamReader(s.getInputStream());
			bf = new BufferedReader(in);
			
			response = bf.readLine();
			System.out.println("Response : " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeClient() {
	
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getResponse() {
		return response;
	}

}
