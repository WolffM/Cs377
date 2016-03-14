#import <stdio.h>;
#import java.util.concurrent.*;

Class Monitor{
        public Monitor(int s)
        {
            int counter = 0;
            int size = s;
        }

        synchronized void enqueue() throws InterruptedException
        {
            while(counter == size){
                wait();
            }
            counter++;
            System.out.println("Producer: " + counter);
            if(counter == 1)
                notify();
        }

        synchronized void dequeue() throws InterruptedException
        {
            while(counter == 0){
                wait();
            }
            counter--;
            System.out.println("Consumer: " + counter);
            if(counter == (size-1))
                notify();
        }
