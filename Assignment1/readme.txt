NAME   :  Yash Avlani
UTA ID :  1001670008
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Programming Language Used : Java 1.8

Code Structure :

Classes name : find_route, graph and graphUtil

Map is solved considering it Graph. Each node taken as city and it's connecting cities as graph_data.

graph.java : this java file consist of two classes : "graph_data" and "tree_node". Method declared in this file is getIndex

graphUtil.java : this java file involves the declaration and defination of main method to calculate the Search Cost for the Source to destination.

find_route.java reads the command line arguments. It takes in four arguments namely, flag for uninf/inf, input filename, origin city and destination city. 
 > The UCS method is called from this class.
 > finds and assigns the origin and destination Node.
 > Prints out distance and route, if exists



How to run :

The program takes arguments like this.

Example,
	find_route uninf/inf input_filename source_city destination_city

To compile the program,
	javac find_route.java

To run the program,
	java find_route uninf input123.txt Bremen Frankfurt

  Provide the full path for input123.txt in case input file is not at the project location.
  
  The program will calculate the shortest path between two nodes and display the node coming on the way.

Example, for input, java find_route uninf input123.txt Bremen Kassel,

Output will be,
nodes expanded: 12
distance: 297 km
route: 
Bremen to Hannover, 132 km 
Hannover to Kassel, 165 km 

  If the two cities are provided with no connection betweeen them than the output will be infinity.

Example, for input, java find_route uninf input123.txt London Frankfurt,

Output will be,
distance: infinity
route:
none
