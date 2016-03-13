from __future__ import division
import random
import math
from multiprocessing import Pool
import copy

def fcfs(data):
	jobs = data.pop(0)
	sim_time = data.pop(0)
	max_len = data.pop(0)
	ct = data[0][1]
	data[0].append(data[0][1])
	for i in data[1:]:
		# print "ct is:"
		# print ct
		ct += i[1]
		i.append(ct)
	for i in data:
		i.append(i[2]-i[0])
	# print '###########################'
	# print data
	for i in data:
		i.append(i[3]-i[1])
	# print '###########################'
	# print data

	print 'avg WT is:'
	awt = 0
	for i in data:
		awt += i[4]
	print awt/jobs[0]
	return data

def rr(data):
	t = 0
	g_chart = []
	ready_q = []
	jobs = data.pop(0)
	sim_time = data.pop(0)
	max_len = data.pop(0)
	job_id = 1
	for i in data: #adding job ID
		i.insert(-2, job_id)
		job_id += 1
	temp = copy.deepcopy(data)
	ready_q.append(temp[0])
	
	while len(ready_q) > 0:
		curr = ready_q.pop(0)
		g_chart.append(curr)
		curr[2] -= 1
		if(curr[2] > 0):
			ready_q.append(curr)
		t += 1
		for i in temp:
			i[1] -= 1
			if i[1] == 0:
				ready_q.append(i)

	for i in data:
		curr = 0
		for j in xrange(len(g_chart)):
			if i[0] == g_chart[j][0]:
				curr = j
		i.append(curr+1)
	for i in data:
		i.append(i[3]-i[1])
	for i in data:
		i.append(i[4]-i[2])
	# print '##########################'
	# print g_chart
	# print '##########################'
	# print ready_q
	print '##########################'
	print data
	print 'avg WT is:'
	awt = 0
	for i in data:
		awt += i[4]
	print awt/jobs[0]

	return data



def main():
    with open('5-100-6.txt', 'r') as f:
       data = []
       for line in f:
       		data.append([int(v) for v in line.split()])
    print data
    with open('5-100-6.txt', 'r') as f:
    	data1 = []
    	for line in f:
    		data1.append([int(v) for v in line.split()])
    fcfs(data)
    rr(data1)


main()