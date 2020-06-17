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
	 	private Map<String, ArrayList<WeightedEdge>> adjacencyList;
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
	            addNeighbor(origin, destination, Double.parseDouble(weight));
	            
	            storeNodes(origin, destination);
	        }
	        // The number of edges is exactly the number of lines in file
	        this.numEdges = lines.size();	        

	    }
	    
	    public WeightedGraph() {
	        adjacencyList = new HashMap<String, ArrayList<WeightedEdge>>();
	    }

		public int numberOfVertices()
	   	{
			System.out.print(adjacencyList.keySet().size());
	       	return adjacencyList.keySet().size();
	    }

	    public int numberOfEdges()
	    {
	    	System.out.print(numEdges);
	       	return numEdges;
	    }

	    public void addNode(String node) 
	    {
	        adjacencyList.putIfAbsent(node, new ArrayList<WeightedEdge>());
	    }

	    public void setAdjacencyList(HashMap<String, ArrayList<WeightedEdge>> adjacencyList){
	    	this.adjacencyList = adjacencyList;
		}

		public void setNumEdges(int numEdges){
			this.numEdges = numEdges;
		}

		public void setNodes(ArrayList<String> nodes){
			this.nodes = nodes;
		}

		public ArrayList<String> getNodes(){
	    	return nodes;
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
	    
	    
	    public void getKeyValuePairs()
	    {
	        Iterator iterator = adjacencyList.keySet().iterator();

	        while (iterator.hasNext()) {
	           String key = iterator.next().toString();
	           ArrayList<WeightedEdge> value = adjacencyList.get(key);
	           System.out.print(key + "->");
	           for(WeightedEdge i : value){
	           		System.out.print(" " + i.origin + " " + i.destination + " " + i.weight);
			   }
	           System.out.println();
	        }
	    }

	    public Map<String, ArrayList<WeightedEdge>> getAdjacencyList(){
	    	return adjacencyList;
		}
	       
	    public void addNeighbor(String vertex1, String vertex2, Double weight) {
	        adjacencyList.get(vertex1).add(new WeightedEdge(vertex1, vertex2, weight));
	        adjacencyList.get(vertex2).add(new WeightedEdge(vertex2, vertex1, weight));
	    }

	     public ArrayList<WeightedEdge> getNeighbors(String vertex) {
	    	//System.out.print(adj.get(vertex));
	        return adjacencyList.get(vertex);
	     }	     

	   	    	    

	}