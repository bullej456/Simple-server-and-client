package uk.co.diegesis.edward.GT2;

import java.net.Socket;
import java.util.ArrayList;

public class ServerManager {
	
	private ArrayList<Socket> clientSockets = null;
	
	public void manageServer() {
		
		//Start server and listen for clients
		clientSockets = new ArrayList<Socket>();
		ServerWorker sw = new ServerWorker(DataClass.PORT);
		sw.startServer();
		clientSockets.add(sw.listen());
		
		//Wait for messages and respond
		//Only exit when the shutdown message is received
		while(!sw.getExit()) {
			for(Socket s: clientSockets) sw.recieveAndRespond(s);
			if(sw.getExit())
				try {
					Thread.sleep(DataClass.SLEEPTIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
		//Server has heard shutdown and waited so now it closes
		sw.closeServer();
		
		return;
	}

}
