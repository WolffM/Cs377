#import <stdio.h>;
#import java.util.*;

Class Monitor{
        public Monitor(int s)
        {
            Queue<int[]> MasterQueue = new LinkedList<int[]>();
            int counter = 0;
            int size = s;
        }

        synchronized void enqueue(int[] request) throws InterruptedException
        {
            while(counter == size){
                wait();
            }
            MasterQueue.add(request);
            counter++;
            System.out.println("Producer: produced request "+request[0]+", length "request[1]" seconds at time "+LocalDateTime.now());
            if(counter == 1)
                notify();
        }

        synchronized void dequeue() throws InterruptedException
        {
            System.out.println("Consumer: assigned request ID "+MasterQueue.peek()[0]+", processing request for the next "+MasterQueue.peek()[0]" seconds, current time is "+LocalDateTime.now());
            while(counter == 0){
                wait();
            }
            MasterQueue.remove();
            counter--;
            System.out.println("Consumer: completed request ID"+MasterQueue.peek()[0]+" at time "+LocalDateTime.now());
            if(counter == (size-1))
                notify();
        }
