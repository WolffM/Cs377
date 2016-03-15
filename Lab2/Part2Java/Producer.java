#include java.lang.*

public class Producer extends Thread{
    int s, l;
    
    public Producer(int sleeptime, int maxlength){
    s = sleeptime;
    l = maxlength;
    }
    public void run() {
        while(1)
        {
        int reqLength = Math.floor(Math.random()*l);
        int ID = Math.floor(Math.random()*100);
          int[] request = (ID, reqLength);
          Main.locker.enqueue(request);
          System.out.println("Producer: sleeping for "+s+" seconds");
          sleep(s);
        }
    }
}
