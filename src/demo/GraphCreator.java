package demo;

import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.picking.MultiPickedState;

/**
 * Created by Abris on 2015. 11. 14..
 */
public class GraphCreator {
    MultiPickedState<Integer> multiPickedState;

    public GraphCreator() {
        multiPickedState = new MultiPickedState<>();
    }

    public DelegateTree getFirstGraph() {
        DelegateTree<Integer, String> g = new DelegateTree<>();

        g.setRoot(1);
        g.addChild("Edge-A",1,3,EdgeType.DIRECTED);
        g.addChild("Edge-B",1,4,EdgeType.DIRECTED);
        g.addChild("Edge-C",1,5,EdgeType.DIRECTED);
        g.addChild("Edge-D",3,6,EdgeType.DIRECTED);
        multiPickedState.pick(1,true);
//        multiPickedState.pick(2,true);
        multiPickedState.pick(4,true);
        multiPickedState.pick(4,false);
//        multiPickedState.pick(3,true);
//        graphCreator.addVertex(2);
//        graphCreator.addVertex(3);
//        graphCreator.addVertex(4);
//
//        graphCreator.addEdge(, 1, 2, EdgeType.DIRECTED);
//        graphCreator.addEdge("Edge-B", 2, 3, EdgeType.DIRECTED);
//        graphCreator.addEdge("Edge-C", 3, 4, EdgeType.DIRECTED);
        return g;
    }

    public SparseGraph getSecondGraph() {
        SparseGraph<Integer, String> g = new SparseGraph<>();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);

        multiPickedState.pick(1,false);
        multiPickedState.pick(2,false);
        multiPickedState.pick(4,true);
        multiPickedState.pick(3,false);
//        multiPickedState.pick(5,true);

        g.addEdge("Edge-A", 1, 2, EdgeType.DIRECTED);
        g.addEdge("Edge-B", 2, 3, EdgeType.DIRECTED);
        g.addEdge("Edge-C", 3, 4, EdgeType.DIRECTED);
        g.addEdge("Edge-D", 4, 5, EdgeType.DIRECTED);
        g.addEdge("Edge-E", 5, 6, EdgeType.DIRECTED);
        return g;
    }

    public MultiPickedState<Integer> getMultiPickedState() {
        return multiPickedState;
    }
}
