package demo;

import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.picking.MultiPickedState;
import model.Edge;
import model.Graph;
import model.Vertex;
import utility.AlgorithmGraphToGuiGraphTransformer;

import java.util.ArrayList;

/**
 * Created by Abris on 2015. 11. 14..
 */
public class GraphCreator {
    MultiPickedState<String> multiPickedState;
    AlgorithmGraphToGuiGraphTransformer algorithmGraphToGuiGraphTransformer;

    public GraphCreator() {
        multiPickedState = new MultiPickedState<>();
        algorithmGraphToGuiGraphTransformer = new AlgorithmGraphToGuiGraphTransformer();
    }

    public SparseGraph createGraph (int graphNumber){
        SparseGraph<String, String> graph;
        switch(graphNumber){
            case 1:
                graph = getFirstGraph();
                break;
            case 2:
                graph = getSecondGraph();
                break;
            case 3:
                graph = getFirstGraph();
                break;
            case 4:
                graph = getSecondGraph();
                break;
            case 5:
                graph = getFirstGraph();
                break;
            default:
                graph = new SparseGraph<>();
        }
        return graph;
    }

    public SparseGraph getFirstGraph() {
        SparseGraph<String, String> g = new SparseGraph<>();

        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addVertex("6");

        multiPickedState.pick("1", true);
        multiPickedState.pick("2", true);
        multiPickedState.pick("3", false);
        multiPickedState.pick("4", false);
        multiPickedState.pick("5", false);

        g.addEdge("Edge-A", "1", "2", EdgeType.DIRECTED);
        g.addEdge("Edge-B", "2", "3", EdgeType.DIRECTED);
        g.addEdge("Edge-C", "3", "4", EdgeType.DIRECTED);
        g.addEdge("Edge-D", "4", "5", EdgeType.DIRECTED);
        g.addEdge("Edge-E", "5", "6", EdgeType.DIRECTED);
        return g;
    }

    public SparseGraph getSecondGraph() {
        ArrayList<Vertex> vertexList = new ArrayList<>();
        vertexList.add(new Vertex("1", "1"));
        vertexList.add(new Vertex("2", "2"));
        vertexList.add(new Vertex("3", "3"));
        vertexList.add(new Vertex("4", "4"));
        ArrayList<Edge> edgeList = new ArrayList<>();
        edgeList.add(new Edge("edge1", vertexList.get(0), vertexList.get(1), 20));
        edgeList.add(new Edge("edge2", vertexList.get(1), vertexList.get(2), 20));
        edgeList.add(new Edge("edge3", vertexList.get(2), vertexList.get(3), 20));
        multiPickedState.pick("1", false);
        multiPickedState.pick("2", false);
        multiPickedState.pick("3", false);
        multiPickedState.pick("4", true);

        Graph graph = new Graph(vertexList, edgeList);


        return algorithmGraphToGuiGraphTransformer.transform(graph);
    }

    public MultiPickedState<String> getMultiPickedState() {
        return multiPickedState;
    }
}
