// This primitive set of tools have very basic implementation of TCP and RMI.
// They should be used to check if ports between two machines are
// blocked by firewalls, antiviruses, routings, etc...
// If you have multiple network interfaces, like LAN1, LAN2, WIFI, etc, 
// You can try to listen on all of them or only on one of them.

// ----- Building (JDK6+ tested):
javac *.java

// ----- Using TCP Tools:
// listen on all network interfaces
java TCPServer 7777
// listen on specific network interface only
java TCPServer localhost 7777

// connect on specific network interface
java TCPClient localhost 7777

// ----- Using RMI Tools:
// listen on all network interfaces
java -cp . RmiServer 7777
// listen on specific network interface only
java -cp . RmiServer ip 7777

// connect on specific interface
java -cp . RmiClient ip 7777
