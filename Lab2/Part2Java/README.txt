Part 2 Java:

My Implementation uses a main method to instantitate a monitor a producer and N number of consumers. The consumers and producers make calls to the monitor whenever they want to modify the queue. Three parameters are taken on input to decide number of consumers(n), max request length(l), and producer sleep time(s).

wait() and notify() are used to communicated between the monitor and the threads. Producers will wait if the queue is full, and notify if the queue is no longer emtpy. Consumers will wait if the queue is empty and will notify if the queue is no longer full.

The requests are int[] that hold a randomized ID and randomized length between 1 and max length. These are held in a Queue of int[]. When a request is made, the producer must sleep for "s" seconds before it will try to produce another.

