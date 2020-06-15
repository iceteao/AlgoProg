package Dijkstra;

import java.util.ArrayList;

public class Dijkstra {
	
	private static ArrayList<String> neighborsList = new ArrayList<String>();
	private static ArrayList<String> visitedStationList = new ArrayList<String>();
	private static ArrayList<String> workStationsList = new ArrayList<String>();
	private static WeightedGraph graph = new WeightedGraph();
	private static Double pathCost = 0.0;
	
	public static ArrayList<String> Dijkstra(String filename, String source, String destination) {
        graph.initialize(filename);
		visitedStationList.add(source);
		
		if (source == destination) {
			workStationsList.add(0, source);
			System.out.println(workStationsList);
			return workStationsList;
		}
		
		neighborsList = graph.getNeighbors(source);
		
		for (int i = 0; i < neighborsList.size(); i = i + 2) {
			if (visitedStationList.contains(neighborsList.get(i)) == false) {
				workStationsList.add(0, source);
				workStationsList.add(1, neighborsList.get(i));
				Double newCost = Double.valueOf(neighborsList.get(i + 1)) + pathCost;
				workStationsList.add(2, newCost.toString());
			}
		}
		
		Double nearestDistanceNeighbor = 1000.1;
		String nearestStation = "";
		
		for (int i = 2; i < workStationsList.size(); i = i + 1) {
			if (Double.valueOf(workStationsList.get(i)) + pathCost < nearestDistanceNeighbor && visitedStationList.contains(workStationsList.get(i - 1)) == false) {
				nearestDistanceNeighbor = Double.valueOf(workStationsList.get(i)) + pathCost;
				nearestStation = workStationsList.get(i - 1);
			}
		}
		
		pathCost = nearestDistanceNeighbor;
		
		return Dijkstra(filename, nearestStation, destination);
	}		
}
