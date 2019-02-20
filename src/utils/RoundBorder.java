package utils;

import javax.swing.border.Border;
import java.awt.*;

public class RoundBorder implements Border {
    private Color color;

    public RoundBorder(Color color) {
        this.color = color;
    }

    public RoundBorder() {
        this.color = Color.BLACK;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRoundRect(0,0,c.getWidth()-1,c.getHeight()-1,15,15);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
