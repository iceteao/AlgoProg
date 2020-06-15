package Dijkstra;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedGraph {
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
	        
	        // Calculates the number of nodes to create
	        for (String line : lines) {
	            String[] currentLine = line.split("; ");
	            String origin = currentLine[0];
	            String destination = currentLine[1];
	            String weight = currentLine[2];
	            addNode(origin);
	            addNode(destination);
	            addNeighbor(origin, destination, weight);
	            
	            storeNodes(origin, destination);
	        }
	        // The number of edges is exactly the number of lines in file
	        this.numEdges = lines.size();	        

	    }
	    
	    public WeightedGraph() {
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
	    
	    private void storeNodes(String source, String destination) {
	        if (!source.equals(destination)) {
	            if (!nodes.contains(destination)) {
	                nodes.add(destination);
	            }
	        }
	        if (!nodes.contains(source)) {
	            nodes.add(source);
	        }
	    }
	    
	    
	    public void getAll()
	    {
	        Iterator<String> iterator = adj.keySet().iterator();

	        while (iterator.hasNext()) {
	           String key = iterator.next().toString();
	           ArrayList<String> value = adj.get(key); 
	           System.out.println(key + " " + value);
	        }
	    }
		
	       
	    public void addNeighbor(String vertex1, String vertex2, String weight) {
	        adj.get(vertex1).add(0, vertex2);
	        adj.get(vertex1).add(1, weight);
	        adj.get(vertex2).add(0, vertex1);
	        adj.get(vertex2).add(1, weight);
	    }

	     public ArrayList<String> getNeighbors(String vertex) {
	    	//System.out.print(adj.get(vertex));
	        return adj.get(vertex);
	     }	     

	   	    	    
	    public static void main(String[] args) {
	    	String filename = "Projet1/weighted_graph.txt";
	    	WeightedGraph graph = new WeightedGraph();
	        graph.initialize(filename);
//	        graph.getAll();
//	    	graph.getNeighbors("CRÉMAZIE");
	        Dijkstra.Dijkstra(filename,"MONTMORENCY", "VERDUN");
	    }
	}