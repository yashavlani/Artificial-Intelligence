Name: Yash Avlani
UTA ID: 1001670008

Programming language: Java 

Code Structure:

Class bnet
This class implements a program that computes and prints out the probability of any combination of events given any other combination of events,given a Bayesian network establishing relations 
between events on the burglary-earthquake-alarm domain together with complete specifications of all probability distributions.
 
public static double callCompute(int bc,int ec,int ac,int jc,int mc,ArrayList<String> arrayProc)
This function calls computeProbability function to compute the probability by considering different combination of values for the number in the numt and dent.

public static double computeProbability(boolean b, boolean e, boolean a, boolean j, boolean m)
This function specifies if the corresponding event (burglary, earthquake, alarm, john-calls, mary-calls) is true or false.

Run Instruction:

Compilation:javac bnet.java
Execution:java bnet Bt Af given Mf

Referred Links:

https://beginnersbook.com/2013/12/java-arraylist-contains-method-example/
https://stackoverflow.com/questions/4885254/string-format-to-format-double-in-java
https://beginnersbook.com/2013/12/java-arraylist-addallcollection-c-method-example/