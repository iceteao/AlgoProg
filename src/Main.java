import Clustering.Betweenness;
import Clustering.ClusteringEdges;
import Clustering.ClusteringVertices;
import Dijkstra.Dijkstra;
import Dijkstra.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        String filename = "weighted_graph.txt";
        WeightedGraph graph = new WeightedGraph();
        graph.initialize(filename);
        graph.getKeyValuePairs();
        graph.getNeighbors("MONK");
        Dijkstra myDijkstra = new Dijkstra();
        myDijkstra.dijkstraSP(graph,"MONTMORENCY");
        myDijkstra.getSp("LANGELIER");
        Betweenness b = new Betweenness(graph);
        b.printCentralityTable();
        b.printEdgeCentrality(graph);
        ClusteringEdges myCluster = new ClusteringEdges();
        WeightedGraph btest = myCluster.findClusters(graph, 6);
        System.out.println("Clusters Edge:");
        myCluster.printClusters(btest);
        ClusteringVertices cv = new ClusteringVertices();
        WeightedGraph cvtest = cv.findClusters(graph, b.getCentralityTable(),4);
        System.out.println("Clusters Vertices:");
        cv.printClusters(cvtest);
        cv.printRemoved();
    }
}
