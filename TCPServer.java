import java.io.*;
import java.net.*;

class TCPServer
{
	public static void main(String argv[]) throws Exception
	{
		String reply = "Hi, client!";
		System.out.println("Starting server...");
		ServerSocket welcomeSocket = null;
		if (argv.length==2){
			InetAddress host = InetAddress.getByName(argv[0]);
			welcomeSocket = new ServerSocket(Integer.parseInt(argv[1]), 1, host);
		}else{
			welcomeSocket = new ServerSocket(Integer.parseInt(argv[0]));
		}
		//welcomeSocket.setSoTimeout(5000);

		while(true)
		{
			System.out.println("Ready.");
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

			System.out.println("Reading incoming message...");
			String request = "";
			try{
				request = inFromClient.readLine();
				System.out.println("Received: \"" + request + "\"\nSending reply...");
				outToClient.println(reply);
				// For other writers:
				//outToClient.write(reply + "\n");
				//outToClient.newLine();
				//outToClient.flush();
				System.out.println("Replied.");
			} catch (Throwable t){
				System.out.println("[ERROR] request = " + request + "\n" + t);
			}
		}
	}
}