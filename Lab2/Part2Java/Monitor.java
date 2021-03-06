import java.util.*;

public class Monitor{
  int counter;
  int size;
  //Queue to hold the requests
  public static Queue<int[]> MasterQueue;
        //Size parameter from args[] and counter start at 0;
        public Monitor(int s)
        {
            MasterQueue = new LinkedList<int[]>();
            counter = 0;
            size = s;
        }

        synchronized void enqueue(int[] request) throws InterruptedException
        {
          //Wait condition if the queue is full
            while(counter == size){
                wait();
            }
            MasterQueue.add(request);
            counter++;
            System.out.println("Producer: produced request "+request[0]+", length "+request[1]+" seconds at time "+System.currentTimeMillis());
            //Once the queue is no longer empty we notify a thread
            if(counter == 1)
                notify();
        }

        synchronized int[] dequeue() throws InterruptedException
        {
            //Wait if the queue is empty
            while(counter == 0){
                wait();
            }
            //Retrieve the end of the queue for it's ID and length
            int[] temp = MasterQueue.peek();
            System.out.println("Consumer: assigned request ID "+temp[0]+", processing request for the next "+temp[1]+" seconds, current time is "+System.currentTimeMillis());
            MasterQueue.remove();
            counter--;
            //Let the producer know if the queue is no longer full
            if(counter == (size-1))
                notify();
            return temp;
        }
}
