package utility;

import edu.uci.ics.jung.graph.SparseGraph;
import model.Edge;
import model.Graph;
import model.Vertex;

/**
 * Created by Abraham on 2015. 11. 24..
 */
public class AlgorithmGraphToGuiGraphTransformer {

    public SparseGraph<String, String> transform(Graph algorithmGraph) {
        SparseGraph<String, String> guiGraph = new SparseGraph<>();
        for (Vertex algorithmVertex : algorithmGraph.getVertexes()) {
            guiGraph.addVertex(algorithmVertex.getName());
        }

        for (Edge algorithmEdge : algorithmGraph.getEdges()) {
            guiGraph.addEdge(Integer.toString(algorithmEdge.getWeight()), algorithmEdge.getSource().getName(), algorithmEdge.getDestination().getName());
        }

        return guiGraph;
    }
}
