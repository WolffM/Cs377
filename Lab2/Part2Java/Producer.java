#include <stdio.h>
#include java.lang.*

public class Producer extends Thread{
    Monitor locker;
    
    public Producer(Monitor m){
    locker = m;
    }
        @Override
        public void run() {
            while(true)
            {
              locker.insert();
            }
        }
    }
