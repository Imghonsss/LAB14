package part3.server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import part3.model.Customer;
import part3.server.controller.CustomerDataManager;

public class TCPCustomerByIdServerApp {
public static void main(String[] args) {
		
		// declare a port number
		int portNo = 8088;
		
		// declare an object for controller class
		CustomerDataManager manager = new CustomerDataManager();
		
		System.out.println("\n\tExecuting TCP Customer By ID Server App");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
				
				// 4. Process request - create input stream to read request
				// Request - customer id: int
				InputStream isInput = clientSocket.getInputStream();
				DataInputStream disInput = new DataInputStream(isInput);
				
				// Read customer id from client
				int customerId = disInput.readInt();
				System.out.println("\tRequest for Customer Id: " + customerId);
				
				// Get product
				Customer customer = manager.getCustomerById(customerId);
				
				// 5. Respond to client
				OutputStream osOutput  = clientSocket.getOutputStream();
				ObjectOutputStream oosOutput = new ObjectOutputStream(osOutput);
				oosOutput.writeObject(customer);
				System.out.print("\tSending customer: " + customer.getCustomerId()
						+ " " + customer.getName());
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
}
