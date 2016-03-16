import java.lang.*;
public class Consumer extends Thread{
    
    public Consumer(){
    }
        @Override
        public void run(){
            while(true)
            {
              try{
                int[] temp = Main.MasterQueue.peek();
                Main.locker.dequeue();
                sleep(temp[1]);
                System.out.println("Consumer: completed request ID"+temp[0]+" at time "+System.currentTimeMillis());
              }
              catch(InterruptedException e){
              	System.out.println("Exception at Consumer dequeue");
              }
        	}
    	}
}
