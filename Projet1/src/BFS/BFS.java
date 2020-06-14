package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {

    private static ArrayList<String> shortestPath = new ArrayList<String>();
    private static UnweightedGraph graph = new UnweightedGraph();

    public static ArrayList<String> breadthFirstSearch(String filename, String source, String destination) {
        shortestPath.clear();
        graph.initialize(filename);
        // A list that stores the path.
        ArrayList<String> path = new ArrayList<String>();

        // If the source is the same as destination, I'm done.
        if (source.equals(destination)) {
            path.add(source);
            return path;
        }

        // A queue to store the visited nodes.
        ArrayDeque<String> queue = new ArrayDeque<String>();
        ArrayDeque<String> visited = new ArrayDeque<String>();

        queue.offer(source);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            visited.offer(vertex);

            ArrayList<String> neighboursList = graph.getNeighbors(vertex);
            for (String neighbour : neighboursList) {

                path.add(neighbour);
                path.add(vertex);

                if (neighbour.equals(destination)) {
                	System.out.print(processPath(source, destination, path));
                    return processPath(source, destination, path);
                } else {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                    }
                }
            }
        }
        return null;
    }


    private static ArrayList<String> processPath(String src, String destination,
                                                 ArrayList<String> path) {

        // Finds out where the destination node directly comes from.
        int index = path.indexOf(destination);
        String source = path.get(index + 1);

        // Adds the destination node to the shortestPath.
        shortestPath.add(0, destination);

        if (source.equals(src)) {
            // The original source node is found.
            shortestPath.add(0, src);
            return shortestPath;
        } else {
            // We find where the source node of the destination node
            // comes from.
            // We then set the source node to be the destination node.
            return processPath(src, source, path);
        }
    }
}