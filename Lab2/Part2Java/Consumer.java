#include <stdio.h>
#include java.lang.*

public class Consumer extends Thread{
    Monitor locker;
    
    public Consumer(Monitor m){
    locker = m;
    }
        @Override
        public void run() {
            while(1)
            {
              System.out.println("Consumer i: assigned request ID "+request[0]+", processing request for the next "+request[1]+" seconds, current time is "+LocalDateTime.now());
              locker.dequeue();
            }
        }
    }
