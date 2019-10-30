import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

/**
* The Client class takes input from the user and proceed to output it to the connected Server.
*/
public class Client {

  private Socket clientSocket; // The socket belonging to the Client
  private InputStreamReader input; // Input from the Client
  private BufferedReader bufferedInput; // Buffered input from the Client
  private DataOutputStream output; // Output from the Client to the Server

  /**
  * This method creates a Client.
  * It creates a connection to a Server and sends the user's message to the Server.
  */
  public void client() {

    try {
      clientSocket = new Socket("localhost", 1785); 
	  // Port number is the same for both Client and Server
	  // Since connection is local, IP address is set as localhost

      input = new InputStreamReader(System.in);
      bufferedInput = new BufferedReader(input);
      output = new DataOutputStream(clientSocket.getOutputStream());

    } catch (UnknownHostException he) {
      System.out.println(he);
    } catch (IOException io) {
      System.out.println(io);
    }

    try {
      String currentLine = "";
      while (!currentLine.equalsIgnoreCase("bye")) {
		// Reads input from Client until Client exits  
		  
        currentLine = bufferedInput.readLine();
        output.writeUTF("[Client] " + currentLine + "\n"); // Outputs input to server
      }
      clientSocket.close();
    } catch (IOException io) {
      System.out.println(io);
    }
  }

  public static void main(String args[]) {

    Client clientCall = new Client();
    clientCall.client(); // Creates new Client
  }
}

