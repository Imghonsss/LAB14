package part3.client.app;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import part3.client.view.ProductViewer;
import part3.model.Product;

public class TCPProductClientApp2 {
public static void main (String[] args) {
		
		try {
		
			// Server information
			int serverPortNo = 8088;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 1. Connect to remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			
			// Create stream to receive response from the server
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			// 3. Read respond from the server - cast the object
			List<Product> products = (List<Product>) ois.readObject();
			
			// 4. Process response - display the object
			ProductViewer productViewer = new ProductViewer();
			productViewer.displayProducts(products);
			
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		

	}

}
