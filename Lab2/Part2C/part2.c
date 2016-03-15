#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

pthread_mutex_t mutex; //mutex to access the queue
pthread_mutex_t timeMutex; //mutex to access the global time variable
int CURRENT_TIME = 0; // current time global variable incremented only by the master thread

struct Node //struct to represents nodes in a queue
{
	struct Request * data;
	struct Node * next;
	
};

struct MasterInstructions //a struct representing a set of instructions
{
	int maxJobNumber;
	int maxJobLength;
	int timeBetweenJobs;

};

struct Node* head = NULL; //global head and tail for managing the queue

struct Node* tail = NULL;

struct Request //struct representing a request
{
	int jobTime;
	int id;

};

void add(struct Request * x) //queue add method adds node to end of queue
{
	struct Node * newNode = (struct Node*)malloc(sizeof(struct Node));
	newNode->data = x;
	newNode->next = NULL;

	if(!head && !tail)
	{
		head = tail = newNode;
		return;
	}
	tail->next = newNode;
	tail = newNode;
}

struct Node* poll() //queue poll method (removes head of queue if the head is null returns null)
{
	if(head == NULL)
	{
		return NULL;
	}

	struct Node * temp = head;

	if(head == tail)
	{
		head = tail = NULL;
		return temp;
	}

	else
		head = head->next;
	return temp;
}

void * mastermethod(void * instructions) //function for the master thread to execute
{
	struct MasterInstructions * instructionSet = (struct MasterInstructions *)instructions; //cast the instruction set to the right pointer type
	int value = instructionSet->maxJobNumber; //get the max job number value
	int x = 0;
	while(x<value) // while x < maxJob Number
	{
		struct Request * newRequest = (struct Request*)malloc(sizeof(struct Request));//generate a new request
		pthread_mutex_lock(&mutex); //lock the queue
		newRequest->jobTime = rand() % (instructionSet->maxJobLength + 1 - 1) + 1;
		newRequest->id = x;
		add(newRequest); //add the new request to the queue
		pthread_mutex_unlock(&mutex);// unlock to prevent deadlock!
		printf("Producer: produced request ID %d, job length %d seconds, current time is %d\n" , newRequest->id+1,newRequest->jobTime,CURRENT_TIME); //print some useful info
		printf("Producer: sleeping for %d seconds...\n", instructionSet->timeBetweenJobs);
		sleep(instructionSet->timeBetweenJobs); //sleep for the designated time between jobs
		pthread_mutex_lock(&timeMutex); //lock time
		CURRENT_TIME = CURRENT_TIME + instructionSet->timeBetweenJobs; //increment time
		pthread_mutex_unlock(&timeMutex); //unlock time
		
		x = x+1;
	}
	return NULL;


}

void * slavemethod(void * threadID)
{

	int * idPointer = (int *)threadID; // grab the id of this thread
	
	int id = *idPointer; 
	
	struct Node * job = NULL; //initiate a node pointer
	

	while(!job) //while there is no job
	{
	pthread_mutex_lock(&mutex); //lock the queue
	 job = poll(); //try and get a job
	pthread_mutex_unlock(&mutex); //unlock the queue
	}
	pthread_mutex_lock(&timeMutex); //lock time
	int slaveTime = CURRENT_TIME; //set time you get the job
	pthread_mutex_unlock(&timeMutex);//unlock time		
	printf("Consumer %d: assigned request ID %d, processing request for the next %d seconds, current time is %d\n",id, job->data->id+1,job->data->jobTime,slaveTime); //print some useful info!
	sleep(job->data->jobTime); //run the job
	printf("Consumer %d: completed request ID %d at time %d\n",id,job->data->id+1,slaveTime+job->data->jobTime); // print more info!
	return NULL;
}


int main()
{
int maxJobNumber; 
int maxJobLength;
int timeBetweenJobs;
//take in parameters for maxjobNumber maxJobLength and timeBetweenJobs designated by the user
printf("Enter Number of Slaves\n");
scanf("%d",&maxJobNumber);

printf("Enter Max Job Length\n");
scanf("%d",&maxJobLength);

printf("Enter Time Between Job Creation\n");
scanf("%d",&timeBetweenJobs);


// create a new set of master instructions
struct MasterInstructions * instructions = (struct MasterInstructions*)malloc(sizeof(struct MasterInstructions));
instructions->maxJobLength = maxJobLength;
instructions->maxJobNumber = maxJobNumber;
instructions->timeBetweenJobs =timeBetweenJobs;
//create the master thread
pthread_t threads[instructions->maxJobNumber +1];
pthread_create(&threads[0],NULL,mastermethod, (void *)instructions);
printf("CREATED MASTER THREAD \n");

	for(int i = 1; i < instructions->maxJobNumber+1 ; i++)
	{
		//create a new thread id for each thread
	int * x = (int *)malloc(sizeof(int));
	*x = i;
	//create the slave thread
	pthread_create(&threads[i], NULL,slavemethod, (void *)x);
	printf("CREATED SLAVE THREAD  %d\n",i);
	}
	pthread_exit(NULL);
}