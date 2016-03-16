import java.lang.*;

public class Producer extends Thread{
    int s, l, ID, reqLength;
    
    //Parameters taken from args[] in Main
    public Producer(int sleeptime, int maxlength){
      s = sleeptime;
      l = maxlength;
    }
  	@Override
    public void run(){
        while(true)
        {
          /*
          Requests are made with random ID between 1 and 100
          length of the reuqest is between 1 and maxlength
          */
          reqLength = (int)Math.floor((Math.random()*(double)l)+1);
          ID = (int)Math.floor((Math.random()*100.00)+1);
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
