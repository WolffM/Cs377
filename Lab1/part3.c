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
	       /* strcat(final,clone[x]);
	        strtok(final,"\n");*/
            
	        int finish = system(buffer);
	        reduce = strtok(NULL," ");
	        exit(0);
		}
		else{wait(0);}
		
    }

  }
	return 0;
}
