#include <stdio.h>
#include java.lang.*

public class Consumer extends Thread{
    
    public Consumer(){
    }
        @Override
        public void run() {
            while(1)
            {
              Queue.locker.dequeue();
            }
        }
    }
