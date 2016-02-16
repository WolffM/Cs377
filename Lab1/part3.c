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

	char frontbuff[1000];
	char final[1000];


	while (1) {
		printf("batch-shell> ");
		fgets(buffer,1000,stdin);

	char* reduce;
	int finish;
	char* clone[1000];

	reduce = strtok(buffer,tmp);
    while (reduce!=NULL) {
		temp[c] = reduce;
		c++;
    }
	for(int x=0; x < c; x++){
		if(fork()==0){
	        strcat(final,clone[x]);
	        strtok(final,"\n");

	        finish = execl(bin,buffer,NULL);
		}
		waitpid(-1,NULL,0);
    }
  }
	return 0;
}
