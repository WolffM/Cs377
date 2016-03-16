import java.lang.*;
public class Consumer extends Thread{
    
    public Consumer(){
    }
    int count = 3;
        @Override
        public void run(){
            while(count != 0)
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
              count--;
        	}
    	}
}
