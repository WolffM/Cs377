
def n_mask(n,m): #creating mask to extract n bits. Params are n and m
	mask = ''
	for i in xrange(0, m):
		mask += '0'

	for i in xrange(0, n):
		mask += '1'

	return int(mask,2) #converting base-2 to base-10, returning the mask as base-10

def m_mask(n,m): #creating mask to extract m bits. Params are n and m
	mask = ''
	for i in xrange(0, m): #
		mask += '1'

	for i in xrange(0, n):
		mask += '0'

	return int(mask,2) #converting base-2 to base-10, returning the mask as base-10



# virtual address v1 is in page number p and offset d 
# virtual address v2 is in page number p and offset d




def main():

	traces = ['sample1.txt']

	for i in traces: #reading the input file, creating array of arrays.
		with open(i, 'r') as f:
		   data = []
		   for line in f:
		   		data.append([int(v) for v in line.split()])
	n = data.pop(0).pop(0) #getting n 
	m = data.pop(0).pop(0) #getting m
	maskn = n_mask(n,m) 
	maskm = m_mask(n,m)
	counter = 0 #counter for virtual address 

	for i in data: #extracting the offset 

		curr = i.pop(0)
		offset = curr & maskn #apply n_mask to get the offset
		page_number = (curr & maskm) >> n # apply m_mask and shift right by n bits to get the page number 
		counter += 1

		print 'virtual address v%d is in page number %d and offset %d'%(counter, page_number, offset)


main()