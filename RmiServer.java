import java.net.ServerSocket;
import java.net.InetAddress;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

class RmiServer
{
	private interface MyServerSocketFactory extends RMIServerSocketFactory{InetAddress getHost();}

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
		}else if (argv.length==1){
			serverRmiSevicePort = argv[0];
		}
		final InetAddress host = InetAddress.getByName(serverRmiSeviceHost);
		final Integer port = Integer.parseInt(serverRmiSevicePort);

		System.out.println("Starting RMI Service on [" + "rmi://" + serverRmiSeviceHost + ":" + serverRmiSevicePort + "/" + serverRmiSeviceName + "]...");
		RmiService rmiService = null;
		Registry rmiRegistry = null;
		if (argv.length==2){
			System.out.println("Assembling LocateRegistry to run all packets through port " + serverRmiSevicePort);
			// Client socket, is usually configured on client side.
			RMIClientSocketFactory clientSocketFactory = null;//RMISocketFactory.getDefaultSocketFactory();
			RMIServerSocketFactory serverSocketFactory = new MyServerSocketFactory(){
				private InetAddress thisHost = host;
				public InetAddress getHost(){return thisHost;}
				@Override
				public ServerSocket createServerSocket(int port) throws java.io.IOException
				{
					return new java.net.ServerSocket(port, 0, thisHost);
				}
				@Override
				public boolean equals(Object obj) {
					return (getClass() == obj.getClass()
					&& ((MyServerSocketFactory)obj).getHost().equals(thisHost));
				}
			};
			rmiRegistry = LocateRegistry.createRegistry(port, clientSocketFactory, serverSocketFactory);
			rmiService = new RmiService(port, clientSocketFactory, serverSocketFactory);
			UnicastRemoteObject.unexportObject(rmiService, true);
			// For non-UnicastRemoteObject use
			//rmiService = (RmiService)UnicastRemoteObject.exportObject(new RmiService(), port, clientSocketFactory, serverSocketFactory);
		} else {
			System.out.println("Remote Object will be returned on a random port between 0 and 65535.");
			rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(serverRmiSevicePort));
			rmiService = new RmiService();
		}
		rmiRegistry.rebind(serverRmiSeviceName, rmiService);
		System.out.println("RMI Service is ready.");
	}
}