     // @Yash Avlani

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class find_route {
    public static void main(String[] arrArgument) {

        if (arrArgument.length < 3) {
             System.out.println("check the arguments");
             System.exit(0);
		}
         
      
        ArrayList<graph.tree_node> graph = graphUtil.createGraph(arrArgument[1]);
        //Provide full path of the input1.txt file;
        graph.tree_node origin = null, destination = null;

        for (graph.tree_node node : graph) {
            if (!node.toString().equals(arrArgument[2].toLowerCase()))
            {

                if (!node.toString().equals(arrArgument[3].toLowerCase()))
                {
                } else {
                    destination = node;
                }
            } else {

                origin = node;
            }
        }

        int distance = 0;

        // Uniform Cost Search
       
        if (!arrArgument[0].equals("inf")) {
        	graphUtil.UCSMethod(origin, destination);

            // Get the route
            List<graph.tree_node> route = new ArrayList<>();
            for (graph.tree_node node = destination; node != null; node = node.parentNode) {
                route.add(node);
            }
            Collections.reverse(route);

            // Distance calculations
            distance = route.stream().filter((node) -> (node.parentNode != null)).map((node) -> node.calculateCost()).reduce(distance, Integer::sum);

      
            if (0 >= distance) {
                System.out.println("distance: infinity");
                System.out.println("Number of nodes explored: "+graphUtil.node_counter);
                System.out.println("route: ");
                System.out.print("none");
            } else {
                System.out.println("distance: " + distance + " km");
                System.out.println("Number of nodes explored: "+graphUtil.node_counter);
                System.out.println("route: ");
                route.stream().filter((node) -> (node.parentNode != null)).forEachOrdered((node) -> {
                    System.out.println(node.parentNode + "   to   " + node + ", " + node.calculateCost() + " km");
                });
            }
        }
    }
}
