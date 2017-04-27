TcpRmiTools
======
[![Read in English](http://www.printableworldflags.com/icon-flags/24/United%20Kingdom.png)](https://github.com/SquareGearsLogic/TcpRmiTools) [![Read in Russian](http://www.printableworldflags.com/icon-flags/24/Russian%20Federation.png)](https://github.com/SquareGearsLogic/TcpRmiTools/blob/master/README.ru.md)  
![](https://travis-ci.org/SquareGearsLogic/TcpRmiTools.svg?branch=master)

Набор простых Java TCP и RMI утилит для теста портов и всяких проблем с фаерволами.
Также может быть использовано в качестве простого примера...

Сборка (тестировалось под JDK6+)
-----------
```bash
javac *.java
```

Использование TCP утилит
-----------
Слушать на всех сетевых интерфейсах.
```bash
java TCPServer [PORT]
```
Слушать на конкретном сетевом интерфейсе.
```bash
java TCPServer [HOST] [PORT]
```

Подключиться клиентом.
```bash
java TCPClient [HOST] [PORT]
```

Использование RMI утилит
-----------
Слушать на всех сетевых интерфейсах.
Remote Object будет отправлен на случайный порт между 0 и 65535
```bash
java -cp . RmiServer [PORT]
```
Если [HOST] не 'localhost', слушает на конкретном сетевом интерфейсе.
Всё общение идёт через единственный порт.
```bash
java -cp . RmiServer [HOST] [PORT]
```

Подключиться клиентом.
```bash
java -cp . RmiClient [HOST] [PORT]
```


Лицензия
-----------
Apache License Version 2.0, January 2004
http://www.apache.org/licenses/