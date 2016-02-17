/*
	Part 1
	Matthaus Wolff: 27581944
	Steven Lambrou: 26241499

*/
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(){
	int p1;
	//Get the pid for current process
	p1 = fork();

	//If it's a child
	if(p1 == 0){
		int p2;
		p2 = fork();
		//If it's a grandchild
		if(p2 == 0){
			sleep(10);
			printf("\nGrandchild process with process id %d has complete. \n", getpid());
			exit(1);
		}
		else if (p2 != 0){
			//Wait for grandchildren to finish
			waitpid(-1,NULL,0);
			printf("\nChild process with process id %d has complete. \n", getpid());
			exit(1);
			
		}
	}
	else if(p1 != 0){
		//Wait for all to finish
		waitpid(-1,NULL,0);
		printf("\nParent process with process id %d has complete. \n", getpid());
		exit(1);
	}
	return 0;
}