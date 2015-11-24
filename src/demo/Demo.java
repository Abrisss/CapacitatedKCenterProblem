package demo;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.util.Animator;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ChainedTransformer;
import utility.CustomLabelRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo extends JApplet {
    GraphCreator graphCreator = new GraphCreator();
    Graph graph;
    int actualGraph = 1;
    private final int lastGraph = 5;


    public JPanel getGraphPanel() {
        graph = graphCreator.createGraph(actualGraph);

        final VisualizationViewer vv = constructVisualizationViewer();

        //eger
        DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
        vv.setGraphMouse(graphMouse);

        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(((DefaultModalGraphMouse) vv.getGraphMouse()).getModeListener());


        //reset
        JButton reset = new JButton("reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Layout layout = vv.getGraphLayout();
                layout.initialize();
                Relaxer relaxer = vv.getModel().getRelaxer();
                if (relaxer != null) {
                    relaxer.stop();
                    relaxer.prerelax();
                    relaxer.relax();
                }

            }
        });

        //a felso jpanel
        JPanel upperJPanel = new JPanel();
        upperJPanel.setBackground(Color.WHITE);
        upperJPanel.setLayout(new BorderLayout());
        upperJPanel.add(vv, "Center");

        JButton nextStep = new JButton("NextStep");
        nextStep.addActionListener(new StepListener(vv));

        JButton previousStep = new JButton("PreviousStep");
        previousStep.addActionListener(new StepListener(vv));


        JPanel control_panel = new JPanel(new GridLayout(2, 1));
        JPanel topControls = new JPanel();
        JPanel bottomControls = new JPanel();
        control_panel.add(topControls);
        control_panel.add(bottomControls);
        upperJPanel.add(control_panel, "North");
        topControls.add(previousStep);
        topControls.add(nextStep);
        bottomControls.add(modeBox);
        bottomControls.add(reset);
        return upperJPanel;
    }

    private VisualizationViewer constructVisualizationViewer() {
        CircleLayout circleLayout = new CircleLayout<>(graph);
        circleLayout.setSize(new Dimension(1366, 580));

        final VisualizationViewer vv = new VisualizationViewer(circleLayout);
        vv.setPreferredSize(new Dimension(1366, 580));

        //A csucsok beallitasa

        vv.getRenderContext().setVertexFillPaintTransformer(new PickableVertexPaintTransformer(graphCreator.getMultiPickedState(), Color.red, Color.yellow));

        //Az elek beallítasa
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(4.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);

        //A feliratok beallitasa
        vv.getRenderContext().setVertexLabelRenderer(new CustomLabelRenderer(Color.red));

        Transformer labelTransformer = new ChainedTransformer<String,String>(new Transformer[]{
                new ToStringLabeller<String>(),
                new Transformer<String,String>() {
                    public String transform(String input) {
                        return "<html><font size=\"6\" color=\"red\">"+input;
                    }}});

        vv.getRenderContext().setVertexLabelTransformer(labelTransformer);
        vv.getRenderContext().setEdgeLabelTransformer(labelTransformer);
        return vv;
    }

    private final class StepListener implements ActionListener {
        private final VisualizationViewer<Integer, Number> vv;

        private StepListener(VisualizationViewer<Integer, Number> vv) {
            this.vv = vv;
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("NextStep") && actualGraph < lastGraph) {
                    actualGraph++;
            } else if (e.getActionCommand().equals("PreviousStep") && actualGraph > 1){
                actualGraph--;
            }
            System.out.println(graphCreator.createGraph(actualGraph));
            try {
                CircleLayout l = new CircleLayout(graphCreator.createGraph(actualGraph));
                l.setInitializer(this.vv.getGraphLayout());
                l.setSize(this.vv.getSize());
                LayoutTransition lt = new LayoutTransition(this.vv, this.vv.getGraphLayout(), l);
                Animator animator = new Animator(lt);
                animator.start();
                this.vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
                this.vv.repaint();
            } catch (Exception var9) {
                var9.printStackTrace();
            }
        }
    }
}
