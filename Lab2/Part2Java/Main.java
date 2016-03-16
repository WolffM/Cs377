public class Main{
	public static Monitor locker;
/*
Args should come in the order:
0 = number of consumers
1 = max duration of a request
2 = sleep time for producer
*/
  public static void main(String[] args) {
    locker = new Monitor(10);

    Producer prod1 = new Producer(Integer.parseInt(args[2]), Integer.parseInt(args[1]), Integer.parseInt(args[0]));
    prod1.start();

    for(int x = Integer.parseInt(args[0]); x>0; x--){
      new Consumer().start();
    }
  }
}
