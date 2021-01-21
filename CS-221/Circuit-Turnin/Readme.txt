****************
* Circuit Tracer
* CS-221-3
* 5/1/20
* Wesley Brown
**************** 

OVERVIEW:

The circuit Tracer program will take in a file along with a storage 
type and print method via command line arguments.  After this it will
parse the values given and construct a circuit board object from the 
data in the file.  Finally, the circuit tracer will search through the 
board trying to find the quickest possible paths from the start point 
to the end point.


INCLUDED FILES:

*CircuitBoard.java – source file
*CircuitTracer.java – source file
*OccupiedPositionException.java – source file
*InvalidFileFormatException.java - source file
*Storage.java – source file
*TraceState.java – source file
*Readme.txt – this file


COMPILING AND RUNNING:

From the Directory contain the above listed source files compile the 
driver class and all dependencies with the following command:
$javac *.java

Run the compiled classes using Circuit files with this command (-s/-q represent datatypes)
(-c/-g represents the output style):
$java CircuitTracer -s/-q -c/-g <filename>

By using -c the correct output will be printed to the console and using 
-g or an invalid format for running the program will print out usage.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

The program can be broken down into a few major sections.  The first part 
of the program is the CircuitTracer which will take in the command line 
arguments and functions as the driver class of the program.  After parsing 
the given arguments, the CircuitTracer will create a new CircuitBoard object 
which it then uses along with a Storage object, representing either a queue 
or a stack, to find every possible solution on the given circuit board.  These 
solutions are then tested to see which are the fastest and recorded into an array 
list called bestPaths.  This ArrayList is what is printed to the console at the 
end of the program running.

The next important aspect would be what the CircuitBoard class does to correctly 
break down a file and turn it into a CircuitBoard object.  The class begins by 
taking in the given file from the CircuitTracer class and parsing it into a 2D char array.
Then the CircuitBoard class will check through the char array for any errors 
that existed in the file.

The last and arguably most important part of this program would be the search
method being used in the CircuitTracer class. This method uses either stack or
queue implementation along with the TraceState class to run through each possible
location that wire could be placed into the circuit until a route to the end is found.
This is done by checking if the places above, below, to the left, and to the right are
capable of holding a new T value or if the current location is next to the endpoint of 
the circuit.  If the latter is true, then that solution is compared to the other current
solutions to see which is faster if not then more Trace State objects are created based
on the locations of future T values.

TESTING:

For my testing we were given both a test class as well as test cases.  I used the test
class to help me understand what aspects of the project needed to be worked on as well 
as what was working currently.  I then went on to run my own tests to ensure that my
search algorithm was correct as well as that my print statements would run correctly.
The program should be fully ready for most usage but of course there are always errors 
which slip through the class.

DISCUSSION:
 
Most of the issues which I faced with this project were in relation to correctly 
understanding how each of the classes work with one another as well as understanding how 
to correct errors not expressed in the testing class.  I also had some trouble 
understanding how to correctly format my program, but I do believe that with the help 
of the tutors and rubber ducky coding I was able to work through my issues.

I would say that the most difficult problems regarding this project was communication 
issues towards the end of my project. While I was able to work through these it did not 
make the project any easier.
 
Analysis:
1.	The choice of storage changes how the program will look at longer paths.  This was
seen when looking at the cases when paths were 5 long in the practice layout.  When 
preforming a FIFO we can see that on a longer path the algorithm will swap between 
all of the remaining search states instead of focusing on a singular line of searches to 
reach the end.  The opposite can be seen with the use of a LIFO where the program will focus
on finishing the most recently discovered search paths until an answer is found.

2.	The total number of possible paths will remain the same but the order in which each 
search path is executed will change based on the choice of stack or queue.

3.	When using the stack structure with example layout the total number of states stored 
in memory was 3 at most.  While using the queue structure with the same example layout the 
total number of states stored was 4 at most.

4.	The Big-Oh runtime for the search algorithm would be O(n).  This would not reflect 
the maximum size of the storage as the largest number of possible locations which need to be 
searched would be 4.  Because of this it would not reflect the number of positions or path 
lengths, but it would show the number of paths which could be explored.

5.	It seems as though a stack will have a higher chance at finding a successful path first 
as it will look only at each new portion added to the stack first.  This is faster than the 
queue because the queue is forced to move off of a path which may have a faster result in it.

6.	The queue should always find the shortest path as its first path because it will only 
not enqueue a new board if a path is found with the shortest path being the one which takes 
the least number of runs in the queue.
