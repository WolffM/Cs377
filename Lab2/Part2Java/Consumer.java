import java.lang.*;
public class Consumer extends Thread{
    
    public Consumer(){
    }
        @Override
        public void run(){
            while(true)
            {
              try{
                Main.locker.dequeue();
                int[] temp = Monitor.MasterQueue.peek();
                sleep(temp[1]);
                System.out.println("Consumer: completed request ID"+temp[0]+" at time "+System.currentTimeMillis());
              }
              catch(InterruptedException e){
              	System.out.println("Exception at Consumer dequeue");
              }
        	}
    	}
}
