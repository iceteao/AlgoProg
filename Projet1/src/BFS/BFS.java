package BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BFS {
    boolean[] marked;
    int[] previous;
    int[] distance;
    int iteration;

    public void bfs(UnweightedGraph g, String v) {
        iteration = 0;
        marked = new boolean[g.numberOfVertices()];
        previous = new int[g.numberOfVertices()];
        distance = new int[g.numberOfVertices()];
        Arrays.fill(marked, false);
        Arrays.fill(previous, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);
        ArrayList<Vertex> ini = new ArrayList<Vertex>();
        marked[v.getNo()] = true;
        distance[v.getNo()] = 0;
        ini.add(v);
        fs(ini, g);
        /*for(int i : distance)
            System.out.println(i);*/
    }

    private void fs(ArrayList<Vertex> lv, Graph g) {
        iteration++;
        ArrayList<Vertex> nextStep = new ArrayList<Vertex>();
        for(int i = 0; i < lv.size(); i++) {
            for(Object v : g.getAdjacencylist()[lv.get(i).getNo()]) {
                if (!marked[((Vertex) v).getNo()]) {
                    nextStep.add((Vertex) v);
                    marked[((Vertex) v).getNo()] = true;
                    distance[((Vertex) v).getNo()] = iteration;
                    previous[((Vertex) v).getNo()] = lv.get(i).getNo();
                }
            }
        }
        if(nextStep.size()>0)
            fs(nextStep, g);
        return;
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public int distTo(int v){
        return distance[v];
    }

    public void printSp(int v){
        List<Integer> path = new ArrayList<Integer>();
        while(v != -1){
            path.add(v);
            v = previous[v];
        }
        for(int i : path){
            System.out.print(" ->" + i);
        }
        System.out.println();
    }
}