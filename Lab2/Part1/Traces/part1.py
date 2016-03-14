from __future__ import division
import random
import math
from multiprocessing import Pool
import copy

def fcfs(data):
	curr_q = []
	ready_q = []
	job_id = 1
	sim_clock = -1
	jobs = data.pop(0)
	sim_time = data.pop(0)
	max_len = data.pop(0)
	total_wait = 0
	for i in data: #adding job ID
		i.insert(-2, job_id)
		job_id += 1
	for i in data:
		i.append(0)
		i.append(0)
	# print "________________________________________________________________"
	# print "FCFS"
	# print "sim_time is: ",sim_time[0]
	while (sim_clock <= (sim_time[0] + max_len[0]) or len(curr_q) > 0):
		sim_clock += 1
		# print "================"
		# print "sim_clock is:", sim_clock
		# print "================"
		if len(curr_q) > 0 and curr_q[0][2] == 0 :
			# print "proccess is done, ID:", curr_q[0][0]
			total_wait += curr_q[0][4]
			curr_q.pop(0)
		for i in data:
			if sim_clock == i[1]:
				# print "arrival time is:", i[1]
				ready_q.append(i)
		# print "ready_q is:", ready_q
		# print "curr_q is:", curr_q
		if len(curr_q) == 0:
			
			# print "total_wait is:", total_wait
			if len(ready_q) > 0:
				curr_q.append(ready_q.pop(0))
			# else:
			# 	# print "IDLE"

		if len(curr_q) > 0:
			curr_q[0][2] -= 1
			for i in ready_q:
				i[4] += 1
	# print "total_wait is:", total_wait
	# print "AWT is :", total_wait/jobs[0]
	# print "________________________________________________________________"
	return total_wait/jobs[0]



def rr(data):
	curr_q1 = []
	ready_q1 = []
	job_id = 1
	sim_clock = -1
	jobs = data.pop(0)
	sim_time = data.pop(0)
	max_len = data.pop(0)
	total_wait = 0
	for i in data: #adding job ID
		i.insert(-2, job_id)
		job_id += 1
	for i in data:
		i.append(0)
		i.append(0)
	# print "________________________________________________________________"
	# # print "sim_time is: ",sim_time[0]
	# print "RR"
	while (sim_clock <= (sim_time[0] + max_len[0]) or len(ready_q1) > 0):
		sim_clock += 1
		# print "================"
		# print "sim_clock is:", sim_clock
		# print "================"			
		for i in data:
			if sim_clock == i[1]:
				# print "arrival time is:", i[1]
				ready_q1.append(i)
		# print "ready_q1 is:", ready_q1
		if len(ready_q1) > 0:
			ready_q1[0][2] -= 1
			for i in ready_q1[1:]:
				i[4] += 1
			if ready_q1[0][2] == 0:
				# print "process is done, ID: ", ready_q1[0][0]
				total_wait += ready_q1[0][4]
				ready_q1.pop(0)
			else:
				ready_q1.append(ready_q1.pop(0))
		# else:
		# 	print "IDLE"
	# print "total_wait is:", total_wait
	# print "AWT is :", total_wait/jobs[0]
	# print "________________________________________________________________"
	return total_wait/jobs[0]




def main():

	traces = ['100-100-11.txt', '100-100-6.txt', '100-100-8.txt', '100-200-11.txt', '100-200-6.txt', '100-200-8.txt', '100-600-11.txt', '100-600-6.txt', '100-600-8.txt']

	for i in traces:

		with open(i, 'r') as f:
		   data = []
		   for line in f:
		   		data.append([int(v) for v in line.split()])
		print "FCFS ", i[:-4]+":", fcfs(data)
		del data[:]


	for i in traces:
		with open(i, 'r') as f:
			data1 = []
			for line in f:
				data1.append([int(v) for v in line.split()])
		
		print "RR ", i[:-4]+":", rr(data1)
		del data1[:]


main()