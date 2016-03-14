Class Monitor{
        public Monitor(int s)
        {
            int counter = 0;
            int size = s;
        }

        synchronized void insert()
        {
            while(counter == size){
                wait();
            }
            counter++;
            System.out.println("Producer: " + counter);
            if(counter == 1)
                notify();
        }

        synchronized void remove()
        {
            while(counter == 0){
                wait();
            }
            counter--;
            System.out.println("Consumer: " + counter);
            if(counter == (size-1))
                notify();
        }
