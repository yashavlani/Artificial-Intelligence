     // @Yash Avlani

import java.util.ArrayList;
import java.util.Comparator;

public class graph {
	static int getIndex(graph.tree_node node, ArrayList<graph.tree_node> graph) {
        for (int i = 0; graph.size() >= i; i++) {
            if (graph.get(i).toString().equals(node.toString())) {
                return i;
            }
        }
        return -1;
    }
	
	static class graph_data {
		//@Yash_Avlani
		
		// Variable list 
		public final tree_node destination;
		public final int cost;
		
		// Constructor
		public graph_data(tree_node destinationNode, int cost_value) {
			cost = cost_value;
			destination = destinationNode;
		}
		
		// Methods
		public double findCost() {
			return cost;
		}
		public tree_node findDestination() {
			return destination;
		}
		
		// Method toString
		public String toString() {
			return destination.toString();
		}
	}
	
	static class tree_node implements Comparator<tree_node> {
		
		// Variable list 
		public final String city;
		public ArrayList<graph_data> toCity;
		public tree_node parentNode;
		public int costForPath;
		
		// Constructor
		public tree_node(String cityName) {
			city = cityName;
			toCity = new ArrayList<graph_data>();
		}
		
		// method
		public String toString() {
			return city;
		}
		
		// Add new city to the list
		public void addCity(tree_node destination, int cost) {
			graph_data objGraphData = new graph_data(destination, cost);
			toCity.add(objGraphData);
		}
		
		// Get cost from source to destination
		public int calculateCost() {
			for(graph_data e : toCity) {
				if(e.destination.toString().equals(parentNode.toString())) 
					return e.cost;
			}
			return 0;
		}
		
		// Pathcost comparision
		@Override
		public int compare(tree_node node1, tree_node node2){
			if(node1.costForPath < node2.costForPath)	
				return -1;
			else if (node1.costForPath > node2.costForPath)	
				return 1;
			else	
				return 0;
		}
		
		// Equals method for object support
		@Override
		public boolean equals(Object obj){
			if((obj instanceof tree_node) && city.equals(obj.toString()))
				return true;
			return false;
		}
	}
}

