/*
	Part 3
	Matthaus Wolff: 27581944
	Steven Lambrou: 26241499

*/
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <string.h>

int main(){

	char buffer[1000];
	char final[1000];


	while (1) {
		printf("Enter your program names seperated by spaces\n");
		printf("This program will then execute them in the order you typed their names in\n");
		printf("batch-shell> ");
		fgets(buffer,1000,stdin);
		buffer[strlen(buffer)-1] = 0;

			if(strcmp(buffer,"q") == 0){
				printf("EXITING BATCH-SHELL\n");
				exit(1);
			}
			
			char* reduce;
			int finish;
			char* clone[1000];
		    int pid;
			reduce = strtok(buffer," ");

    			while (reduce != NULL) {
			       pid = fork();

				if(pid==0){
						printf("MY PROCESS ID = %d\n",getpid());
				        int finish = execv(reduce,NULL);
				        exit(1);
					}

					else{
						int status = 0;
				        reduce = strtok(NULL," ");
					    wait(&status);
						}
					
			    }

  }
	return 0;
}
