package part3.server.app;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import part3.model.Customer;
import part3.server.controller.CustomerDataManager;

public class TCPCustomerServerApp implements Serializable{
public static void main(String[] args) {
		
		int portNo = 8088;
		
		CustomerDataManager manager = new CustomerDataManager();
		
		System.out.println("\n\tExecuting TCP Customer Server App");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
				
				// 4. Process request - create input stream to read request
				// Request - customer id:int
				InputStream isInput = clientSocket.getInputStream();
				DataInputStream disInput = new DataInputStream(isInput);
				
				// Read Customer name from client
				String customerName = disInput.readUTF();
				
				// Get customer by name
				Customer customers = manager.getCustomerByName(customerName);
				
				// 4. Respond to client
				OutputStream osOutput  = clientSocket.getOutputStream();
				ObjectOutputStream oosOutput = new ObjectOutputStream(osOutput);
				oosOutput.writeObject(customers);
				System.out.print("\tSending customer: " + customers.getCustomerId()
				+ " " + customers.getName());
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
}
