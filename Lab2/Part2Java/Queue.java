#include <stdio.h>

class Queue{
	Monitor locker;
}

/*
Args should come in the order:

0 = number of consumers
1 = max duration of a request
2 = sleep time for producer
*/
public static void main(int[] args) {
	Producer prod1 = new Producer(locker, args[2], args[1]);
	prod1.start();
	
	locker = new Monitor(10);
	
	for(int x = args[0]; x>0; x--){
	new Consumer(locker).start();
	}
}
