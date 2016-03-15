#include <stdio.h>

class Main{
	public Monitor locker;
}

/*
Args should come in the order:

0 = number of consumers
1 = max duration of a request
2 = sleep time for producer
*/
public static void main(int[] args) {
	locker = new Monitor(10);
	
	Producer prod1 = new Producer(args[2], args[1]);
	prod1.start();
	
	for(int x = args[0]; x>0; x--){
	   new Consumer().start();
	}
}
