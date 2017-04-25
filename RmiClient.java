import java.rmi.Naming;

class RmiClient
{
	public static void main(String argv[]) throws Exception
	{
		String sentence = "Hi, server!";
		System.out.println("Starting client...");
		String rmiUri = "rmi://" + argv[0] + ":" + argv[1] + "/RmiTest";

		System.out.println("Connecting to Rmi Service on " + rmiUri + "...");
		Object o = Naming.lookup(rmiUri);
		System.out.println("Rmi Service object is " + o.getClass().getName() + " [" + o + "]");
		java.rmi.Remote someService = Naming.lookup(rmiUri);
		IRmi rmiService = (IRmi) someService;
		Object res = rmiService.execute("Hi, server!");
		System.out.println("Reply: " + res);
	}
}