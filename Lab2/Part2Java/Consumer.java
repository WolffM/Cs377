#include <stdio.h>
#include java.lang.*

public class Consumer extends Thread{
    Monitor locker;
    
    public Consumer(Monitor m){
    locker = m;
    }
        @Override
        public void run() {
            while(true)
            {
              locker.remove();
            }
        }
    }
