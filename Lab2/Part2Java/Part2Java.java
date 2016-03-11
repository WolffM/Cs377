#include <stdio.h>

class Queue{
	private data;
}

public void synchronized AQ(some item){
	enqueue(item);
	notify();
}

public Object synchronized RQ(){
	while(queue.peek() == NULL){
		wait();
	}
		item = remove();
		return item;
}
int main(void) {

}
