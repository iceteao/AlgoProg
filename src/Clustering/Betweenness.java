package Clustering;

import Dijkstra.Dijkstra;
import Dijkstra.WeightedEdge;
import Dijkstra.WeightedGraph;

import java.util.ArrayList;
import java.util.HashMap;

public class Betweenness {
    HashMap<String, Integer> centralityTable = new HashMap<String, Integer>();

    public Betweenness(WeightedGraph g){
        for(String s : g.getAdjacencyList().keySet()){
            centralityTable.put(s, 0);
        }
        fillCentralityTable(g);
        fillCentralityEdges(g);
    }

    public HashMap<String, Integer> getCentralityTable(){
        return centralityTable;
    }

    public void printCentralityTable(){
        for(String s : centralityTable.keySet()){
            System.out.println(s + ":" + centralityTable.get(s));
        }
    }

    public void printEdgeCentrality(WeightedGraph g){
        for(String s : g.getAdjacencyList().keySet()){
            System.out.print(s);
            for(WeightedEdge e : g.getAdjacencyList().get(s)) {
                System.out.print("->" + e.getDestination() + "("+ e.getBetweenness() +")");
            }
            System.out.println();
        }
    }

    private void fillCentralityTable(WeightedGraph g){
        Dijkstra dijkstra = new Dijkstra();
        for(String s : g.getAdjacencyList().keySet()){
            dijkstra.dijkstraSP(g, s);
            for(String t : g.getAdjacencyList().keySet()){
                ArrayList<String> sp = dijkstra.getSp(t);
                if(sp.size()>2) {
                    for (int i = 1; i < (sp.size() - 1); i++){
                        centralityTable.put(sp.get(i), centralityTable.get(sp.get(i)) + 1);
                    }
                }
            }
        }
    }

    private void fillCentralityEdges(WeightedGraph g){
        Dijkstra dijkstra = new Dijkstra();
        for(String s : g.getAdjacencyList().keySet()){
            dijkstra.dijkstraSP(g, s);
            for(String t : g.getAdjacencyList().keySet()){
                ArrayList<String> sp = dijkstra.getSp(t);
                if(sp.size()>4) {
                    for(int i = 1; i < (sp.size() - 1); i++){
                        for(WeightedEdge edge : g.getAdjacencyList().get(sp.get(i))){
                            if(edge.getDestination() == sp.get(i + 1)){
                                edge.setBetweenness(edge.getBetweenness() + 1);
                                for(WeightedEdge edge2 : g.getAdjacencyList().get(edge.getDestination())){
                                    if(edge2.getDestination() == edge.getOrigin()){
                                        edge2.setBetweenness(edge2.getBetweenness() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
