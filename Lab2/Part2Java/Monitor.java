#import <stdio.h>;
#import java.util.concurrent.*;

Class Monitor{
        public Monitor(int s)
        {
            int counter = 0;
            int size = s;
        }

        synchronized void enqueue(int[] request) throws InterruptedException
        {
            while(counter == size){
                wait();
            }
            counter++;
            System.out.println("Producer: produced request "+request[0]+", length "request[1]" seconds at time "+LocalDateTime.now());
            if(counter == 1)
                notify();
        }

        synchronized void dequeue(int[] request) throws InterruptedException
        {
            while(counter == 0){
                wait();
            }
            counter--;
            System.out.println("Consumer: completed request ID"+request[0]+" at time "+LocalDateTime.now());
            if(counter == (size-1))
                notify();
        }
