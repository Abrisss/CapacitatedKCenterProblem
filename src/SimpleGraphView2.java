import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class SimpleGraphView2 {
    Graph<Integer, String> g;

    /**
     * Creates a new instance of SimpleGraphView
     */
    public SimpleGraphView2() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        // Note showing the use of a SparseGraph rather than a SparseMultigraph
        g = new SparseGraph<Integer, String>();
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex((Integer) 1);
        g.addVertex((Integer) 2);
        g.addVertex((Integer) 3);
        g.addVertex((Integer) 4);
        // g.addVertex((Integer)1);  // note if you add the same object again nothing changes
        // Add some edges. From above we defined these to be of type String
        // Note that the default is for undirected edges.
        g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives
        g.addEdge("Edge-B", 2, 3);
        g.addEdge("Edge-C", 3, 4);
    }
}