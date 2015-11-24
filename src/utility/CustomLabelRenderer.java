package utility;

import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;

import java.awt.*;

/**
 * Created by Abraham on 2015. 11. 24..
 */
public class CustomLabelRenderer extends DefaultVertexLabelRenderer {
    public CustomLabelRenderer(Color pickedVertexLabelColor) {
        super(pickedVertexLabelColor);
        this.setFont(new Font("Verdana", Font.BOLD, 23));
        this.setOpaque(false);
    }

}
