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
	char* tmp;
	strcpy(tmp,"./");
	
	strcat(tmp,reduce);
	printf(" %s\n",tmp);
    while (reduce != NULL) {
       pid = fork();
	if(pid==0){
	        int finish = system(tmp);
	        printf("MY PROCESS ID = %d\n",getpid());
	        exit(1);
		}
		else{int status = 0;
		    
	        reduce = strtok(NULL," ");
	        if(reduce){
	        memset(tmp,0,sizeof(tmp));
	        printf(" %s\n",tmp);
	        strcpy(tmp,"./");
	        strcat(tmp,reduce);
	        printf(" %s\n",tmp);
	        }
		    wait(&status);}
		
    }

  }
	return 0;
}
