import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
* The Server class takes input from a Client connected to it and proceeds to output it to System.out
*/
public class Server {

  private ServerSocket serverSocket; // The socket belonging to the Server
  private Socket clientSocket; // The socket belonging to the Client connected to the Server
  private InputStreamReader input; // Input from the Client
  private BufferedReader bufferedInput; // Buffered input from the Client

  /**
  * This method creates a Server.
  * It creates a connection to a Client and outputs the input received from the Client.
  */
  public void server() {

    try {
      serverSocket = new ServerSocket(1785);
      clientSocket = serverSocket.accept(); // Accepts Client connection
      input = new InputStreamReader(clientSocket.getInputStream());
      bufferedInput = new BufferedReader(input);

      String inputLine = "";
      while (!clientSocket.isClosed()) { 
	  // Reads and outputs Client input until connection is closed

        inputLine = bufferedInput.readLine();
        if(inputLine == null) {
          clientSocket.close();
          break;
        }
        System.out.println(inputLine);
      }

      input.close();
      bufferedInput.close();
      serverSocket.close();

    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public static void main(String args[]) {

    Server serverCall = new Server();
    serverCall.server(); // Creates a new Server
  }
}
