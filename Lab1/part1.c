/*
	Part 1
	Matthaus Wolff: 27581944
	Steven Lambrou: 26241499

*/

#include <stdio.h>
#include <stdlib.h>

int main(){
	int p1;
	p1 = fork();

	if(p1 == 0){
		int p2;
		p2 = fork();

		if(p2 == 0){
			printf("\ngrandchild has pid of: %d \n", getpid());
			sleep(1);
		}
		else if (p2 != 0){
			printf("\nchild has pid of: %d \n", getpid());
			waitpid(-1,NULL,0);
			
		}
	}
	else if(p1 != 0){
		printf("\nparent has pid of: %d \n", getpid());
		waitpid(-1,NULL,0);
	}
	return 0;
}