import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRmi extends Remote
{
	Object execute(String nmsg) throws RemoteException;	
}
