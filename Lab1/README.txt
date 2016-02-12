Part 1:
For this part we used a simple set of nested If/Else If Loops to determine if a forked result is a child or a parent and print out it's pid. We used waitpid to make sure the children exit before their parent does.

Part 2:
We took a user friendly approach to this part and gave a couple extra functions. The program starts by asking you how many processes you want to termiante, and then asks for their pid's. Then it loops over and kills the process and prints the possible error messages. We use the errno.h include and checked the possible messages from: http://linux.die.net/man/3/kill

Part 3:
