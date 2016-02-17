#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <errno.h>

int main( int argc, char *argv[]){
    //Input for number of process to kill via scanf
    printf("Type in number of process ids to terminate\n");
    int killNum;
    fflush(stdin);
    scanf("%d", &killNum); 
    printf("Number to kill = %d\n",killNum);
    printf("Enter Processes to Terminate\n");
    printf("Enter -1 to end stop sending\n");


    int pids[killNum];
    int pid;
    int i = 0;
        //Loop to add what Process ID's to kill, with a check of -1 at the end
    	while(pid != -1 && i < sizeof(pids)/sizeof(int)){
        	fflush(stdin);
        	scanf("%d",&pid);
        	printf("PROCESS ID TO KILL PID = %d ADDED \n",pid);
        	pids[i] = pid;
        	i++;
        }
        int currentPid;
        //Loop for killing and then catching the errno
        for(int j = 0; j < sizeof(pids)/sizeof(int); j++){
        	currentPid = pids[j];
            //Finish loop at -1
        	if(currentPid == -1){
        		printf("OUT OF PROCESSES EXITING...\n");
        		exit(1);
        	}
        	kill(currentPid,0);

            //Output from error catch
        	if(errno != ESRCH){
        	kill(currentPid,SIGKILL);
        	if(errno == EPERM )
        		printf("YOU DO NOT HAVE PERMISSION TO TERMINATE PROCESS = %d MOVING ON TO NEXT PROCESS", currentPid);
        	if(errno == EINVAL)
        		printf("INCORRECT SIGNAL SENT DEVELOPER ONLY \n");
    		}
    		else {
    			printf("THIS PROCESS ID IS NOT VALID PROCESS ID PROCESS COULD NOT BE FOUND, PROCESS ID = %d", currentPid);}
        }
        return 0;
}