 // @Yash Avlani

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;

public class graphUtil {
	static int node_counter ;
	
	// UCS Algorithm Implementation
	static void UCSMethod(graph.tree_node sourceNode, graph.tree_node destination) {
        // Set path cost of the initial state, i.e.,
        // the source node, to 0.
        sourceNode.costForPath = 0;
        node_counter = 0;

        //PriorityQueue Declaration
		
        PriorityQueue<graph.tree_node> fringe = new PriorityQueue<>(25, sourceNode);
       
	   // Add the source to the frontier
        fringe.add(sourceNode);
        graph.tree_node current = null;
        if(!sourceNode.equals(destination)) {
        	node_counter++;
        }

        // Visited Node Store
        HashSet<graph.tree_node> closed = new HashSet<>();

        // loop
        do {
            // sourceNode=current node
            current = fringe.poll();
            
            //node added to sourceNode set
            closed.add(current);
          //  node_counter++;
            // every edge corresponding to origin node
            for (graph.graph_data e : current.toCity) {
                // set child node as destination and set current cost to it's cost
                graph.tree_node child = e.destination;
                int cost = e.cost;
                if (fringe.contains(child) == false) {
                    child.costForPath = current.costForPath + cost;
                    if (closed.contains(child) == false) {
                        child.parentNode = current;
                        fringe.add(child);
                         node_counter++;
                    }
                } else if ((fringe.contains(child) == true) && (child.costForPath > current.costForPath + cost)) {
                    child.parentNode = current;
                    fringe.remove(child);
                  //  node_counter--;
                    fringe.add(child);
                    // node_counter++;
                }
            }
        } while (fringe.isEmpty() == false);
    }

	 // Reads the input file , one line at a time
    static ArrayList<graph.tree_node> createGraph(String filename) {
        ArrayList<graph.tree_node> map = new ArrayList<graph.tree_node>();
        File file = new File(filename);
        try {
            InputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                // Check If the Input file Completed. 
                if (line.equals("END OF INPUT")) {
                    break;
                }
                // Splits line and assign the values 
                String[] values = line.toLowerCase().split(" ");
                String origin_city = values[0].toString();
                String destination_city = values[1].toString();
                int cost = Integer.parseInt(values[2]);

                // Initialization of origin and destination
                graph.tree_node startCity = new graph.tree_node(origin_city);
                graph.tree_node destination = new graph.tree_node(destination_city);
                int index = -1;
                
                if (map.contains(startCity) == true) {
                    index = graph.getIndex(startCity, map);
                    startCity = map.get(index);
                }

                if (map.contains(destination) == true) {
                    index = graph.getIndex(destination, map);
                    destination = map.get(index);
                    destination.addCity(startCity, cost);
                    index = graph.getIndex(destination, map);
                    map.set(index, destination);
                } else {
                    destination.addCity(startCity, cost);
                    map.add(destination);
                }

                // Add graph data from origin to destination.
                startCity.addCity(destination, cost);

                
                if (map.contains(startCity) == true) {
                    index = graph.getIndex(startCity, map);
                    map.set(index, startCity);
                } else {
                    map.add(startCity);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Reached End of Line");
        } catch (IOException e) 
        {
            System.out.println(e);
        }
        return map;
    }

}