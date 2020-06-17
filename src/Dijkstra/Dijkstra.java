package Dijkstra;

import java.util.*;

public class Dijkstra {
    Map<String, Boolean> marked;
    Map<String, String> previous;
    Map<String, Double> distance;

    public void dijkstraSP(WeightedGraph G, String s){
        marked = new HashMap<String, Boolean>();
        previous = new HashMap<String, String>();
        distance = new HashMap<String, Double>();
        for(String vname : G.getAdjacencyList().keySet()){
            marked.put(vname, false);
            previous.put(vname, null);
            distance.put(vname, Double.MAX_VALUE);
        }
        distance.replace(s, 0.0);
        dijk(G, s);
        /*for(String a : marked.keySet()){
            System.out.println(a + ":" + marked.get(a));
        }
        for(String d : previous.values())
            System.out.println(d);*/
    }

    public Map<String, Boolean> getMarked(){
        return marked;
    }

    private void dijk(WeightedGraph G, String s){
        marked.replace(s, true);
        ArrayList<WeightedEdge> currentNeighbours = G.getAdjacencyList().get(s);
        for(WeightedEdge we : currentNeighbours){
            if((we.getWeight() + distance.get(we.getOrigin()) < distance.get(we.getDestination()))){
                distance.replace(we.getDestination(), we.getWeight() + distance.get(we.getOrigin()));
                previous.replace(we.getDestination(), we.getOrigin());
            }
        }
        String next = nextIndex();
        if(next != null){
            dijk(G, next);
        }
    }

    private String nextIndex(){
        double min = Integer.MAX_VALUE;
        String minVName = null;
        for(String s : distance.keySet()){
            if(distance.get(s) <= min && !marked.get(s)){
                min = distance.get(s);
                minVName = s;
            }
        }
        return minVName;
    }

    public void printSp(String v){
        ArrayList<String> path = new ArrayList<String>();
        while(v != null){
            path.add(v);
            v = previous.get(v);
        }
        for(String s : path){
            System.out.print(" -> " + s);
        }
        System.out.println();
    }

    public ArrayList<String> getSp(String v){
        ArrayList<String> path = new ArrayList<String>();
        while(v != null){
            path.add(v);
            v = previous.get(v);
        }
        return path;
    }
}
