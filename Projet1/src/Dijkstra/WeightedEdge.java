package Dijkstra;

public class WeightedEdge {
    String origin;
    String destination;
    Double weight;
    int betweenness;
    int cluster = 0;

    public WeightedEdge(String origin, String destination, Double weight){
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public String getOrigin(){
        return origin;
    }

    public String getDestination(){
        return destination;
    }

    public Double getWeight(){
        return weight;
    }

    public int getCluster(){
        return cluster;
    }

    public int getBetweenness(){
        return betweenness;
    }

    public void setBetweenness(int b){
        betweenness = b;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
}
