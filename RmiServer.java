import java.net.ServerSocket;
import java.net.InetAddress;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.server.RMISocketFactory;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

class RmiServer
{
   public static void main(String argv[]) throws Exception
    {
		System.out.println("Starting server...");
		ServerSocket welcomeSocket = null;
		String serverRmiSeviceHost = "localhost";
		String serverRmiSevicePort = "7777";
		String serverRmiSeviceName = "RmiTest";
		if (argv.length==2){
			serverRmiSeviceHost = argv[0];
			serverRmiSevicePort = argv[1];
		}else{
			serverRmiSevicePort = argv[0];
		}
		
		System.out.println("Starting RMI Service on [" + "rmi://" + serverRmiSeviceHost + ":" + serverRmiSevicePort + "/" + serverRmiSeviceName + "]...");
		RmiService rmiService = new RmiService();
		Registry rmiRegistry = null;
		if (argv.length==2){
			System.out.println("Assembling LocateRegistry");
			final String ssfHost = serverRmiSeviceHost;
			RMIClientSocketFactory socketFactory = RMISocketFactory.getDefaultSocketFactory();
			RMIServerSocketFactory serverSocketFactory = new RMIServerSocketFactory(){
				public ServerSocket createServerSocket(int port) throws java.io.IOException
				{
					java.net.InetAddress hostAddr = java.net.InetAddress.getByName(ssfHost);
					return new java.net.ServerSocket(port, 1, hostAddr);
				}
			};
			
			rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(serverRmiSevicePort), socketFactory, serverSocketFactory);
		} else {
			System.out.println("Using LocateRegistry");
			rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(serverRmiSevicePort));
		}
		rmiRegistry.rebind(serverRmiSeviceName, rmiService);
		System.out.println("RMI Service is ready.");
    }
}