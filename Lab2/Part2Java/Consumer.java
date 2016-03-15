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
              }
              catch(InterruptedException e){
              	System.out.println("Exception at Consumer dequeue");
              }
        	}
    	}
}
