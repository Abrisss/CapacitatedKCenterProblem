//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import edu.uci.ics.jung.algorithms.generators.random.MixedRandomGraphGenerator;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.TestGraphs;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.util.Animator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import org.apache.commons.collections15.Factory;

class ShowLayouts extends JApplet {
    protected static Graph<? extends Object, ? extends Object>[] g_array;
    protected static int graph_index;
    protected static String[] graph_names = new String[]{"Two component graph",
            "Random mixed-mode graph", "Miscellaneous multicomponent graph",
            "Random directed acyclic graph", "One component graph",
            "Chain+isolate graph", "Trivial (disconnected) graph"};

    public ShowLayouts() {
    }

    private static JPanel getGraphPanel() {
        g_array = (Graph[])(new Graph[graph_names.length]);
        Factory graphFactory = new Factory() {
            public Graph<Integer, Number> create() {
                return new SparseMultigraph();
            }
        };
        Factory vertexFactory = new Factory() {
            int count;

            public Integer create() {
                return Integer.valueOf(this.count++);
            }
        };
        Factory edgeFactory = new Factory() {
            int count;

            public Number create() {
                return Integer.valueOf(this.count++);
            }
        };
        g_array[0] = TestGraphs.createTestGraph(false);
        g_array[1] = MixedRandomGraphGenerator.generateMixedRandomGraph(graphFactory, vertexFactory, edgeFactory, new HashMap(), 20, true, new HashSet());
        g_array[2] = TestGraphs.getDemoGraph();
        g_array[3] = TestGraphs.createDirectedAcyclicGraph(4, 4, 0.3D);
        g_array[4] = TestGraphs.getOneComponentGraph();
        g_array[5] = TestGraphs.createChainPlusIsolates(18, 5);
        g_array[6] = TestGraphs.createChainPlusIsolates(0, 20);
        Graph g = g_array[4];
        final VisualizationViewer vv = new VisualizationViewer(new FRLayout(g));
        vv.getRenderContext().setVertexFillPaintTransformer(new PickableVertexPaintTransformer(vv.getPickedVertexState(), Color.red, Color.yellow));
        DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
        vv.setGraphMouse(graphMouse);
        final CrossoverScalingControl scaler = new CrossoverScalingControl();
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1F, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 0.9090909F, vv.getCenter());
            }
        });
        JButton reset = new JButton("reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Layout layout = vv.getGraphLayout();
                layout.initialize();
                Relaxer relaxer = vv.getModel().getRelaxer();
                if(relaxer != null) {
                    relaxer.stop();
                    relaxer.prerelax();
                    relaxer.relax();
                }

            }
        });
        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(((DefaultModalGraphMouse)vv.getGraphMouse()).getModeListener());
        JPanel jp = new JPanel();
        jp.setBackground(Color.WHITE);
        jp.setLayout(new BorderLayout());
        jp.add(vv, "Center");
        Class[] combos = getCombos();
        JComboBox jcb = new JComboBox(combos);
        jcb.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                String valueString = value.toString();
                valueString = valueString.substring(valueString.lastIndexOf(46) + 1);
                return super.getListCellRendererComponent(list, valueString, index, isSelected, cellHasFocus);
            }
        });
        jcb.addActionListener(new ShowLayouts.LayoutChooser(jcb, vv));
        jcb.setSelectedItem(FRLayout.class);
        JPanel control_panel = new JPanel(new GridLayout(2, 1));
        JPanel topControls = new JPanel();
        JPanel bottomControls = new JPanel();
        control_panel.add(topControls);
        control_panel.add(bottomControls);
        jp.add(control_panel, "North");
        JComboBox graph_chooser = new JComboBox(graph_names);
        graph_chooser.addActionListener(new ShowLayouts.GraphChooser(jcb));
        topControls.add(jcb);
        topControls.add(graph_chooser);
        bottomControls.add(plus);
        bottomControls.add(minus);
        bottomControls.add(modeBox);
        bottomControls.add(reset);
        return jp;
    }

    public void start() {
        this.getContentPane().add(getGraphPanel());
    }

    private static Class<? extends Layout>[] getCombos() {
        ArrayList layouts = new ArrayList();
        layouts.add(KKLayout.class);
        layouts.add(FRLayout.class);
        layouts.add(CircleLayout.class);
        layouts.add(SpringLayout.class);
        layouts.add(SpringLayout2.class);
        layouts.add(ISOMLayout.class);
        return (Class[])layouts.toArray(new Class[0]);
    }

    public static void main(String[] args) {
        JPanel jp = getGraphPanel();
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.setDefaultCloseOperation(3);
        jf.pack();
        jf.setVisible(true);
    }

    private static final class LayoutChooser implements ActionListener {
        private final JComboBox jcb;
        private final VisualizationViewer<Integer, Number> vv;

        private LayoutChooser(JComboBox jcb, VisualizationViewer<Integer, Number> vv) {
            this.jcb = jcb;
            this.vv = vv;
        }



        public void actionPerformed(ActionEvent arg0) {
            Object[] constructorArgs = new Object[]{ShowLayouts.g_array[ShowLayouts.graph_index]};
            Class layoutC = (Class)this.jcb.getSelectedItem();

            try {
                Constructor e = layoutC.getConstructor(new Class[]{Graph.class});
                Object o = e.newInstance(constructorArgs);
                Layout l = (Layout)o;
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

    public static class GraphChooser implements ActionListener {
        private JComboBox layout_combo;

        public GraphChooser(JComboBox layout_combo) {
            this.layout_combo = layout_combo;
        }

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            ShowLayouts.graph_index = cb.getSelectedIndex();
            this.layout_combo.setSelectedIndex(this.layout_combo.getSelectedIndex());
        }
    }
}
