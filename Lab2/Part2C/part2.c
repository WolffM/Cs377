#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_mutex_t timeMutex;
pthread_mutex_t timeMutex2;
int CURRENT_TIME = 0;
int CURRENT_TIME_SLAVES = 0;

struct Node
{
	struct Request * data;
	struct Node * next;
	
};

struct MasterInstructions
{
	int maxJobNumber;
	int maxJobLength;
	int timeBetweenJobs;

};

struct Node* head = NULL; //global head and tail for managing the queue

struct Node* tail = NULL;

struct Request
{
	int jobTime;
	int id;

};

void add(struct Request * x)
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

struct Node* poll()
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

void * mastermethod(void * instructions)
{
	printf("IN MASTER METHOD\n");
	struct MasterInstructions * instructionSet = (struct MasterInstructions *)instructions;
	int value = instructionSet->maxJobNumber;
	printf("NUMBER OF JOBS TO MAKE %d\n",value);
	int x = 0;
	while(x<=value-1)
	{
		struct Request * newRequest = (struct Request*)malloc(sizeof(struct Request));
		pthread_mutex_lock(&mutex);
		newRequest->jobTime = rand() % (instructionSet->maxJobLength + 1 - 1) + 1;
		newRequest->id = x;
		add(newRequest);
		pthread_mutex_unlock(&mutex);
		printf("Producer: produced request ID %d, job length %d seconds, current time is %d\n" , newRequest->id+1,newRequest->jobTime,CURRENT_TIME);
		printf("Producer: sleeping for %d seconds...\n", instructionSet->timeBetweenJobs);
		sleep(instructionSet->timeBetweenJobs);
		pthread_mutex_lock(&timeMutex);
		CURRENT_TIME = CURRENT_TIME + instructionSet->timeBetweenJobs;
		pthread_mutex_unlock(&timeMutex);
		
		x = x+1;
	}
	return NULL;


}

void * slavemethod(void * threadID)
{

	int * idPointer = (int *)threadID;
	
	int id = *idPointer;
	
	struct Node * job = NULL;
	

	while(!job)
	{
	pthread_mutex_lock(&mutex);
	 job = poll();
	pthread_mutex_unlock(&mutex);
	}
	pthread_mutex_lock(&timeMutex);
	int slaveTime = CURRENT_TIME;
	pthread_mutex_unlock(&timeMutex);		
	printf("Consumer %d: assigned request ID %d, processing request for the next %d seconds, current time is %d\n",id, job->data->id+1,job->data->jobTime,slaveTime);
	sleep(job->data->jobTime);
	printf("Consumer %d: completed request ID %d at time %d\n",id,job->data->id+1,slaveTime+job->data->jobTime);
	return NULL;
}


int main()
{
int t = 1;
int N =5;
int maxJobNumber;
int maxJobLength;
int timeBetweenJobs;

printf("Enter Number of Slaves\n");
scanf("%d",&maxJobNumber);

printf("Enter Max Job Length\n");
scanf("%d",&maxJobLength);

printf("Enter Time Between Job Creation\n");
scanf("%d",&timeBetweenJobs);



struct MasterInstructions * instructions = (struct MasterInstructions*)malloc(sizeof(struct MasterInstructions));
instructions->maxJobLength = maxJobLength;
instructions->maxJobNumber = maxJobNumber;
instructions->timeBetweenJobs =timeBetweenJobs;

pthread_t threads[instructions->maxJobNumber +1];
pthread_create(&threads[0],NULL,mastermethod, (void *)instructions);
printf("CREATED MASTER THREAD \n");

	for(int i = 1; i < instructions->maxJobNumber+1 ; i++)
	{
	int * x = (int *)malloc(sizeof(int));
	*x = i;
	pthread_create(&threads[i], NULL,slavemethod, (void *)x);
	printf("CREATED SLAVE THREAD  %d\n",i);
	}
	pthread_exit(NULL);
}