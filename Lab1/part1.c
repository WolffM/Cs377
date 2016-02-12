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
	p1 = fork();

	if(p1 == 0){
		int p2;
		p2 = fork();

		if(p2 == 0){
			sleep(10);
			printf("\nGrandchild process with process id %d has complete. \n", getpid());
			exit(1);
		}
		else if (p2 != 0){
			waitpid(-1,NULL,0);
			printf("\nChild process with process id %d has complete. \n", getpid());
			exit(1);
			
		}
	}
	else if(p1 != 0){
		waitpid(-1,NULL,0);
		printf("\nParent process with process id %d has complete. \n", getpid());
		exit(1);

	}
	return 0;
}