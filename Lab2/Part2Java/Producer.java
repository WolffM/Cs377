#include <stdio.h>
#include java.lang.*

public class Producer extends Thread{
    Monitor locker;
    int s, l;
    
    public Producer(Monitor m, int sleeptime, int maxlength){
    locker = m;
    s = sleeptime;
    l = maxlength;
    }
    public void run() {
        while(1)
        {
        int reqLength = Math.floor(Math.random()*l);
        int ID = Math.floor(Math.random()*100);
          int[] request = (ID, reqLength);
          locker.enqueue(request);
          System.out.println("Producer: sleeping for "+s+" seconds");
          sleep(s);
        }
    }
}
