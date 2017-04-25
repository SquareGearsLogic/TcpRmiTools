import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

public class RmiService extends UnicastRemoteObject implements IRmi {
	
	public RmiService() throws RemoteException { super(); }
	
	public RmiService(int port) throws RemoteException { super(port); }
	
	public RmiService(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
	throws RemoteException
	{
		super(port, csf, ssf);
	}

	@Override
	public Object execute(String msg) throws RemoteException {
		System.out.println("Received: \"" + msg + "\"\nSending reply...");
		return "Hi, rmi client!";
	}
}
