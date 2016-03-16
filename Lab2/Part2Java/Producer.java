import java.lang.*;

public class Producer extends Thread{
    int s, l, ID, reqLength, count;
    
    //Parameters taken from args[] in Main
    public Producer(int sleeptime, int maxlength, int numprod){
      s = sleeptime;
      l = maxlength;
      count = numprod*3;
    }
  	@Override
    public void run(){
        while(count != 0)
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
              count--;
        }
    }
}
