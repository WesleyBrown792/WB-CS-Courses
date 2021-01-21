****************
* Project DLL
* CS 221 3
* 4/18/20
* Wesley Brown
**************** 

OVERVIEW:

This program is made to create a double linked list.  It
uses nodes to store data and also stores the location of 
previous and next nodes.  This data structure would be used
with a list iterator and a normal iterator as well.

INCLUDED FILES:

 List the files required for the project with a brief
 explanation of why each is included.

 e.g.
 * IndexedUnsortedList.java - source file
 * IUDoubleLinkedList.java - source file
 * Node.java - source file
 * ListTester.java - test file
 * README - this file


COMPILING AND RUNNING:

 In order to use the IUDoubleLinkedList file to hold data
 actively then you would need to create a driver method.
 But the file can be tested by running a javac *.java
 then running java ListTester.java to test the program.

 This would be done from a directory containing all of the
 files and then would be compiled with the command:
 $ javac *.java

 To print the output and run the program use the command:
 $ java ListTester.java
 
 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 All of the methods within the DLL class are Override methods
 and already had written java-doc comments.  The Node class
 has in its methods for accessing the value of the node as well
 as the last and next node within the list.  The Iterator class
 was also designed based on the java-doc comments given in the 
 interface, like how the DLL was written around the 
 IndexedUnsortedList interface.
 
 The DLL does have some important advantages over its SLL and
 array list counterparts.  Most importantly because the DLL allows
 its nodes to have pointers in both directions list traversal is 
 faster and memory of the node location is more easily managed. 
 This does have some downsides for the systems memory usage but
 compared to an SLL the DLL is much faster or the same speed for more methods.
 The DLL is also very useful compared to our array list as we can add an
 infinite number of nodes to the DLL whereas we will need to constantly keep
 expanding the array list when we reach the size limit which could either take
 extra time or use far more memory then needed.

TESTING:

 All of my testing for this program was done through the ListTester.java
 class.  This class was designed with thousands of test cases in order to
 test as many test cases as possible.


DISCUSSION:
 
 There were three areas of major issue that I had throughout this project.
 The first was in the add at index method in which I had issues with getting
 the nodes to correctly interact with each other.  I had a similar issue with
 nodes returning null values because of not realizing when they needed to 
 forget the place of other nodes.  Finally, I had an issue adding in the ListIterator
 tests to the ListTester Class.

 While working with the tutor we were able to slowly walk through my classes
 and get an understanding of what was wrong with them.  We then were able to
 fix and test these solutions.
