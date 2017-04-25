TcpRmiTools
======
[![Read in English](http://www.printableworldflags.com/icon-flags/24/United%20Kingdom.png)](https://github.com/SquareGearsLogic/TcpRmiTools) [![Read in Russian](http://www.printableworldflags.com/icon-flags/24/Russian%20Federation.png)](https://github.com/SquareGearsLogic/TcpRmiTools/blob/master/README.ru.md)  
![](https://travis-ci.org/SquareGearsLogic/TcpRmiTools.svg?branch=master)

Basic Java TCP and RMI tools to test ports and firewall-like issues.
Also could be used for beginners tutorials...

Building (JDK6+ tested)
-----------
```bash
javac *.java
```

Using TCP Tools
-----------
listen on all network interfaces.
```bash
java TCPServer [PORT]
```
listen on specific network interface only.
```bash
java TCPServer [HOST] [PORT]
```

connect Client on specific network interface.
```bash
java TCPClient [HOST] [PORT]
```

Using RMI Tools
-----------
listen on all network interfaces.
Remote Object will be returned on a random port between 0 and 65535.
```bash
java -cp . RmiServer [PORT]
```
listen on specific network interface only.
All communication goes through a single port.
```bash
java -cp . RmiServer [HOST] [PORT]
```

connect Client on specific network interface.
```bash
java -cp . RmiClient [HOST] [PORT]
```


License
-----------
Apache License Version 2.0, January 2004
http://www.apache.org/licenses/