package part3.client.app;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import part3.client.view.CustomerViewer;
import part3.model.Customer;

public class TCPCustomerClientApp2 {
public static void main (String[] args) {
		
		try {
		
			// Server information
			int serverPortNo = 8088;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			
			// Create stream to receive response from the server
			InputStream isInput = socket.getInputStream();
			ObjectInputStream oisInput = new ObjectInputStream(isInput);
			
			// 3. Read respond from the server - cast the object
			List<Customer> customers = (List<Customer>) oisInput.readObject();
			
			// 4. Process response - display the object
			CustomerViewer customerView = new CustomerViewer();
			customerView.displayCustomers(customers);
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		

		
	}

}
