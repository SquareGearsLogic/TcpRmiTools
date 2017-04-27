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
	private static RMIServerSocketFactory getServerFactory(final InetAddress host)
	{
		return new MyServerSocketFactory(){
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
	}

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
			if ( serverRmiSeviceHost.equals("localhost") ){
				System.out.println("Assembling to run all packets through single port on all interfaces " + serverRmiSevicePort);
				rmiRegistry = LocateRegistry.createRegistry(port);	// Registry Port.
				rmiService = new RmiService(port); 					// Remote Objects Port.
			} else {
				System.out.println("Assembling to run all packets through single port on single interface " + serverRmiSeviceHost + ":" + serverRmiSevicePort);
				// RMIClientSocketFactory is driven by client.
				RMIClientSocketFactory clientSocketFactory = null; //RMISocketFactory.getDefaultSocketFactory();
				System.setProperty("java.rmi.server.hostname", serverRmiSeviceHost);			// Remote Objects network interface.
				RMIServerSocketFactory serverSocketFactory = getServerFactory(host);			// Registry network interface.
				rmiRegistry = LocateRegistry.createRegistry(port, null, serverSocketFactory);	// Registry Port.
				rmiService = new RmiService(port, 												// Remote Objects Port.
						clientSocketFactory,
						serverSocketFactory);
				// For non-UnicastRemoteObject use
				// if (UnicastRemoteObject.unexportObject(rmiService, true))
				// 	rmiService = (RmiService)UnicastRemoteObject.exportObject(new RmiService(), port, clientSocketFactory, serverSocketFactory);
			}
		} else if (argv.length==1){
			System.out.println("Remote Object will be returned on a random port between 0 and 65535 and random interface.");
			rmiRegistry = LocateRegistry.createRegistry(port); // Registry Port is where we connect to.
			rmiService = new RmiService();
		}
		rmiRegistry.rebind(serverRmiSeviceName, rmiService);
		System.out.println("RMI Service is ready.");
	}
}