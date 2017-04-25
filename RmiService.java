import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiService extends UnicastRemoteObject implements IRmi {
	private static final long serialVersionUID = 1L;

	public RmiService() throws RemoteException { super(); }

	@Override
	public Object execute(String msg) throws RemoteException {
		System.out.println("Received: \"" + msg + "\"\nSending reply...");
		return "Hi, rmi client!";
	}
}
