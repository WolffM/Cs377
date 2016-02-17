Part 1:
For this part we used a simple set of nested If/Else If Loops to determine if a forked result is a child or a parent and print out it's pid. We used waitpid to make sure the children exit before their parent does.

Part 2:
We took a user friendly approach to this part and gave a couple extra functions. The program starts by asking you how many processes you want to termiante, and then asks for their pid's. Then it loops over and kills the process and prints the possible error messages. We use the errno.h include and checked the possible messages from: http://linux.die.net/man/3/kill

Part 3:
For this part we used the execv() function to execute the programs and the strtok function to tokenize the input string. The user inputs a string of paths to programs seperated by spaces. This is then split up using strtok into another String in order to find the programs and execute them. fork() is used to execute these programs and child processes. The child processes printout their process ids before they execute their programs. To run test this program type test1 test2 to have it run the two test programs. These programs will print output to the console. I have also included a sample.txt output file with sample output.

Compiling the code and running the code.

Part 1:

compiling - gcc -o part1 part1.c
running - ./part1 - This code simply executes and requires no user input.

Part 2:

compiling - gcc -o part2 part2.c -std=c99 (This is for the for loop used.)
running - ./part2 - This code will prompt the user for a number of processes to kill. This can be any number. Then input as many process ids up to the maximum or enter -1 to stop entering processes. The program will then loop and kill all desired processes. Errors are handled automatically so dont worry if you type in the wrong PID! Type 0 in as a PID inorder to make this process kill it self.

Part3 :

compiling - gcc -o part3 part3.c
            gcc -o test1 test1.c
            gcc -o test2 test2.c

running - ./part3 - The code will then prompt the user to input programs to be executed. Note this is the path to the programs. test programs have been included in this package to  be executed for testing. Type test1 test2 to have test1 execute followed by test2. The program will loop until the user types q to quit. Type any combination of test1 and test2 seperated by spaces to have the programs execute in any order or any amount of times. A sample.txt has been included with sample output. 



