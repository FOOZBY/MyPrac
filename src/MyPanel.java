import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    Polygon pol_A = new Polygon();
    Polygon pol_B = new Polygon();
    Polygon pol_C = new Polygon();

    MyPanel()
    {

        super.setBackground(Color.BLUE);
        super.setPreferredSize(new Dimension(500,500));
    }

    public void paint(Graphics g)
    {
        g.drawPolygon(pol_A);
        g.drawPolygon(pol_B);
        g.drawPolygon(pol_C);
    }
}
