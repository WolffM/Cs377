#include <stdio.h>

class Queue{
	Monitor locker;
}
	public Queue(){
		locker = new Monitor(10);
	}

public static void main(String[] args) {
	Producer prod1 = new Producer(locker.size);
	Consumer cons1 = new Consumer(locker.size);
	Consumer cons2 = new Consumer(locker.size);
	prod1.start();
	cons1.start();
	cons2.start();
}
