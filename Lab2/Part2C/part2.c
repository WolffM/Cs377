#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

pthread_mutex_t mutex;


struct Node
{
	struct Request * data;
	struct Node * next;
	
};

struct masterInstructions
{
	int maxJobNumber;
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

void * mastermethod(void * N)
{
	printf("IN MASTER METHOD\n");
	int x = 0;
	int * value = (int*)N;
	int trueValue = *value;
	printf("NUMBER OF JOBS TO MAKE %d\n",trueValue);
	while(x<=trueValue)
	{
		struct Request * newRequest = (struct Request*)malloc(sizeof(struct Request));
		pthread_mutex_lock(&mutex);
		newRequest->jobTime = 3;
		newRequest->id = x;
		add(newRequest);
		pthread_mutex_unlock(&mutex);
		sleep(1);
		printf("CREATED JOB UNLOCKING\n");
		x = x+1;
	}
	return NULL;


}

void * slavemethod(void * threadID)
{

	int * idPointer = (int *)threadID;
	
	int id = *idPointer;
	printf("THREAD ID%d\n",id);
	struct Node * job = NULL;
	

	while(!job)
	{
	pthread_mutex_lock(&mutex);
	 job = poll();
	pthread_mutex_unlock(&mutex);
	}		
	printf("THREAD: %d, ASSIGNED JOB %d\n",id, job->data->id+1);
	sleep(job->data->jobTime);
	printf("JOB COMPLETED RUNTIME : %d BY THREAD %d\n",job->data->jobTime,id);
	printf("JOB ID : %d\n", job->data->id +1);
	return NULL;
}


int main()
{
int t = 1;
int N = 5;
pthread_t threads[N+1];
pthread_create(&threads[0],NULL,mastermethod, (void *)&N);
printf("CREATED MASTER THREAD\n");
	for(int i = 1; i < N ; i++)
	{
	int * x = (int *)malloc(sizeof(int));
	*x = i;
	pthread_create(&threads[i], NULL,slavemethod, (void *)x);
	printf("CREATED SLAVE THREAD  %d\n",i);
	}
	pthread_exit(NULL);
}