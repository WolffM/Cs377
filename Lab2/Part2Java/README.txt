Part 2 Java:

My Implementation uses a main method to instantitate a monitor a producer and N number of consumers. The consumers and producers make calls to the monitor whenever they want to modify the queue. 

wait() and notify() are used to communicated between the monitor and the threads if the queue is full or empty.

The requests are int[] that hold a randomized ID and randomized length between 1 and max length. These are held in a Queue of int[].

