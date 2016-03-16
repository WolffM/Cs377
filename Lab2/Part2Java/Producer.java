import java.lang.*;

public class Producer extends Thread{
    int s, l, ID, reqLength;
    
    public Producer(int sleeptime, int maxlength){
      s = sleeptime;
      l = maxlength;
    }
  	@Override
    public void run(){
        while(true)
        {
          reqLength = (int)Math.floor((Math.random()*(double)l)+1);
          ID = (int)Math.floor(Math.random()*100.00);
          int[] request = new int[]{ID, reqLength};
              try{
                Main.locker.enqueue(request);
                System.out.println("Producer: sleeping for "+s+" seconds");
                sleep(s);
              }
              catch(InterruptedException e){
              	System.out.println("Exception at Producer enqueue");
              }
        }
    }
}
