Part one is written in Python (2.7)

The implementation is straight forward.
Program parses the data from trace files,
trims the header (jobs, sim_time, and max_len)
and puts the processes into an array. (process pool)

After that the program does the simulation by
simply creating a loop with a ready_queue that
checks the process pool for "arriving" processes.
If the sim_clock corresponds to the arrival time,
the program pulls the process from the pool
and adds it to the ready_queue.

After that, depending on which algorithm we are using,
(FCFS is non preemptive, so we move through the loop until
 the head of the queue is done,
or in RR do the process for 1 time slice, pop it,
 and move it back to the queue. Wait times are incremented accordingly)

There are commented print statements left out that are very useful 
for debugging and working with out put. 

~Alexey S.