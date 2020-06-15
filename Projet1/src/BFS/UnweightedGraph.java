package BFS;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnweightedGraph {
	 	private Map<String, ArrayList<String>> adj;	
		private int numEdges;
		private ArrayList<String> nodes = new ArrayList<String>();
		
	   // Initializes the number of nodes from given file.
	    public void initialize(String pathToFile) {
	        List<String> lines = new ArrayList<>();
	        
	        // Read file given by its path and add each line to list
	        try (BufferedReader br = Files.newBufferedReader(Paths.get(pathToFile))) {
	            lines = br.lines().collect(Collectors.toList());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        // Add the node and neighbors
	        for (String line : lines) {
	            String[] currentLine = line.split(", ");
	            String origin = currentLine[0];
	            String destination = currentLine[1];
	            addNode(origin);
	            addNode(destination);
	            addNeighbor(origin, destination);
	            
	        }
	        // The number of edges is exactly the number of lines in file
	        this.numEdges = lines.size();

	        

	    }
	    
	    public UnweightedGraph() {
	        adj = new HashMap<String, ArrayList<String>>();
	    }

	   
		public int numberOfVertices()
	   	{
			System.out.print(adj.keySet().size());
	       	return adj.keySet().size();
	    }

	    public int numberOfEdges()
	    {
			System.out.print(numEdges);
	       	return numEdges;
	    }
	    
		

	    public void addNode(String node) 
	    {
	        adj.putIfAbsent(node, new ArrayList<String>());
	    }
	    

	    
	    // Display all the nodes and their neighbours
	    public void getAll()
	    {
	        Iterator iterator = adj.keySet().iterator();

	        while (iterator.hasNext()) {
	           String key = iterator.next().toString();
	           ArrayList<String> value = adj.get(key); 
	           System.out.println(key +" " + value);
	        }
	    }
		
	       
	    public void addNeighbor(String vertex1,String vertex2) {
	        adj.get(vertex1).add(vertex2);
	        adj.get(vertex2).add(vertex1);
	    }

	     public ArrayList<String> getNeighbors(String vertex) {
	        return adj.get(vertex);
	     }


	     

	   	    	    
	    public static void main(String[] args) {
	    	String filename = "Projet1/unweighted_graph.txt";
	    	UnweightedGraph graph = new UnweightedGraph();
	        graph.initialize(filename);
//	        graph.getAll();
//	    	graph.getNeighbors("MONTMORENCY");
//	        graph.numberOfVertices();
	        BFS.breadthFirstSearch(filename,"MONTMORENCY", "PARC");
	    }
	}