package Clustering;

import Dijkstra.Dijkstra;
import Dijkstra.WeightedEdge;
import Dijkstra.WeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;

public class ClusteringVertices {
    WeightedGraph graphCopy;
    HashMap<String, Integer> bTable;
    ArrayList<String> removed = new ArrayList<String>();

    public WeightedGraph findClusters(WeightedGraph g, HashMap<String, Integer> bTable, int c) {
        this.bTable = new HashMap<String, Integer>(bTable);
        iniGraphCopy(g);
        while(countClusters(g) < c) {
            int maxBetweenness = 0;
            String maxV = null;
            for (String s : this.bTable.keySet()) {
                if (bTable.get(s) > maxBetweenness) {
                    maxV = s;
                    maxBetweenness = this.bTable.get(s);
                }
            }
            for (WeightedEdge edge : g.getAdjacencyList().get(maxV)) {
                for(WeightedEdge edge2 : g.getAdjacencyList().get(edge.getDestination())){
                    if(edge2.getDestination().equals(maxV)){
                        g.getAdjacencyList().get(edge.getDestination()).remove(edge2);
                        break;
                    }
                }
            }
            g.getAdjacencyList().remove(maxV);
            this.bTable.remove(maxV);
            removed.add(maxV);
        }
        setClusters(g);
        return graphCopy;
    }

    public void printRemoved(){
        System.out.print("Removed: ");
        for(String s : removed){
            System.out.print(" " + s + " ");
        }
        System.out.println();
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
