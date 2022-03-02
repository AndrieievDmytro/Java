import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;


public class Circle extends JComponent {
    private static final long serialVersionUID = 1;
    private Random random = new Random();

    public Circle ( int width, int hight ) {
        Dimension d = new Dimension(width, hight); 
        setPreferredSize(d);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = getWidth();  
        int hight = getHeight();
        for (int i = 0 ; i < 10 ; i++){
            g.drawOval(random.nextInt(width-30), random.nextInt(hight/12), width/15, width/15);
        }            
    }
}