package uk.co.diegesis.edward.GT2;

public class ClientManager {
	
	public void managerClient(){
		
		//Start client
		ClientWorker cw = new ClientWorker(DataClass.IP, DataClass.PORT);
		cw.startClient();

		//Send all messages awaiting each response
		for(String message: DataClass.MESSAGES) {
			cw.sendMessageWithResponse(message);
		}
		
		//Attempt to communicate "shutdown"
		cw.sendMessageWithResponse(DataClass.SHUTDOWN_STRING);
		if(cw.getResponse().equals(DataClass.SHUTDOWN_RESPONSE_STRING)) {
			cw.closeClient();
		}
		else {
			System.out.println("Error exiting.");
		}
		return;
	}

}
