import java.io.*;
import java.net.*;

class TCPClient
{
public static void main(String argv[]) throws Exception
{
	String sentence = "Hi, server!";
	System.out.println("Starting client...");
	Socket clientSocket = new Socket(argv[0], Integer.parseInt(argv[1]));

	BufferedWriter outToServer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	String reply = "";

	try{
		System.out.println("Sending...");
		outToServer.write(sentence);
		outToServer.newLine();
		outToServer.flush();
		System.out.println("Waiting for reply...");
		reply = inFromServer.readLine();
		System.out.println("Server says: \"" + reply + "\"");
	} catch (Throwable t){
		System.out.println("[ERROR] server says: \"" + reply + "\".\n" + t);
	}
		clientSocket.close();
	}
}