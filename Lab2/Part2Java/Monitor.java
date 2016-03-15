import java.util.*;

public class Monitor{
  int counter;
  int size;
  Queue<int[]> MasterQueue;
        public Monitor(int s)
        {
            MasterQueue = new LinkedList<int[]>();
            counter = 0;
            size = s;
        }

        synchronized void enqueue(int[] request) throws InterruptedException
        {
            while(counter == size){
                wait();
            }
            MasterQueue.add(request);
            counter++;
            System.out.println("Producer: produced request "+request[0]+", length "+request[1]+" seconds at time "+System.currentTimeMillis());
            if(counter == 1)
                notify();
        }

        synchronized void dequeue() throws InterruptedException
        {
            int[] temp = MasterQueue.peek();
            System.out.println("Consumer: assigned request ID "+temp[0]+", processing request for the next "+temp[1]+" seconds, current time is "+System.currentTimeMillis());
            while(counter == 0){
                wait();
            }
            MasterQueue.remove();
            counter--;
            System.out.println("Consumer: completed request ID"+temp[0]+" at time "+System.currentTimeMillis());
            if(counter == (size-1))
                notify();
        }
}
