package Clustering;

import Dijkstra.Dijkstra;
import Dijkstra.WeightedEdge;
import Dijkstra.WeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;

public class ClusteringEdges {
    WeightedGraph graphCopy;

    public WeightedGraph findClusters(WeightedGraph g, int c) {
        iniGraphCopy(g);
        while(countClusters(g) < c) {
            int maxBetweenness = 0;
            WeightedEdge maxEdge = null;
            for (String s : g.getAdjacencyList().keySet()) {
                for (WeightedEdge edge : g.getAdjacencyList().get(s)) {
                    if (edge.getBetweenness() > maxBetweenness) {
                        maxEdge = edge;
                        maxBetweenness = edge.getBetweenness();
                    }
                }
            }
            g.getAdjacencyList().get(maxEdge.getOrigin()).remove(maxEdge);
            for (WeightedEdge edge2 : g.getAdjacencyList().get(maxEdge.getDestination())) {
                if (edge2.getDestination() == maxEdge.getOrigin()) {
                    g.getAdjacencyList().get(maxEdge.getDestination()).remove(edge2);
                    break;
                }
            }
        }
        setClusters(g);
        return graphCopy;
    }

    private void setClusters(WeightedGraph g){
        int clusterCount = 0;
        Boolean complete = false;
        Dijkstra dijkstra = new Dijkstra();
        HashMap<String, Boolean> completionTable = new HashMap<String, Boolean>();
        for(String s : g.getAdjacencyList().keySet()){
            completionTable.put(s, false);
        }
        while(!complete){
            for(String s : completionTable.keySet()) {
                if(!completionTable.get(s)) {
                    dijkstra.dijkstraSP(g, s);
                    clusterCount++;
                    break;
                }
            }
            for(String s : dijkstra.getMarked().keySet()){
                if(dijkstra.getMarked().get(s)){
                    completionTable.put(s,true);
                    for(WeightedEdge edge : g.getAdjacencyList().get(s)){
                        edge.setCluster(clusterCount);
                    }
                }
            }
            complete = true;
            for(String s : completionTable.keySet()){
                if(!completionTable.get(s)){
                    complete = false;
                }
            }
        }
    }

    private int countClusters(WeightedGraph g){
        int clusterCount = 0;
        Boolean complete = false;
        Dijkstra dijkstra = new Dijkstra();
        HashMap<String, Boolean> completionTable = new HashMap<String, Boolean>();
        for(String s : g.getAdjacencyList().keySet()){
            completionTable.put(s, false);
        }
        while(!complete){
            for(String s : completionTable.keySet()) {
                if(!completionTable.get(s)) {
                    dijkstra.dijkstraSP(g, s);
                    break;
                }
            }
            for(String s : dijkstra.getMarked().keySet()){
                if(dijkstra.getMarked().get(s)){
                    completionTable.put(s,true);
                }
            }
            complete = true;
            for(String s : completionTable.keySet()){
                if(!completionTable.get(s)){
                    complete = false;
                }
            }
            clusterCount++;
        }
        return clusterCount;
    }

    private void iniGraphCopy(WeightedGraph g){
        WeightedGraph gCopy = new WeightedGraph();
        gCopy.setAdjacencyList(new HashMap<String, ArrayList<WeightedEdge>>(g.getAdjacencyList()));
        gCopy.setNumEdges(g.numberOfEdges());
        gCopy.setNodes(new ArrayList<String>(g.getNodes()));
        graphCopy = gCopy;
    }

    public void printClusters(WeightedGraph g){
        for(String s : g.getAdjacencyList().keySet()){
            System.out.print(s);
            for(WeightedEdge edge : g.getAdjacencyList().get(s)){
                System.out.print(" -> " + edge.getDestination() + "("+ edge.getCluster() +")" );
            }
            System.out.println();
        }
    }
}
