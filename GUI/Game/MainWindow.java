//Andrieiev Dmytro s21353//
/*
    Rules
The game goes on until the player scores 50 points 
or one of the squares reaches the lower limit. 
Depending on the points scored, the game accelerates
*/
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.event.MouseInputListener;
import java.util.Random;
import java.lang.Thread;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;


public class MainWindow {

    public static Boolean terminateGame = false;
    public static int Score = 0;
    public static int Speed = 1;
    public static List<Rectangle> rectangles = new ArrayList<Rectangle>();
    public static Rectangle rectangle = new Rectangle();
    public static JFrame mainWnd = new JFrame("Catch the Square");
    public static Random random = new Random();

    public static void main(String[] args) {
        game();
    }

    public static void game() {
        int width = 1024;
        int hight = 768;
        JLabel scoreboard = new JLabel();
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout( new BorderLayout());
        
        scoreboard.setForeground(Color.RED);
        scoreboard.setPreferredSize(new Dimension(100, 100));
        scorePanel.setPreferredSize(new Dimension(100, 100));
        scorePanel.add(scoreboard,BorderLayout.WEST);
        mainWnd.add(scorePanel, BorderLayout.EAST);
        mainWnd.setSize( width, hight );
        mainWnd.setBackground(Color.black);
        for (int i = 0; i < 5; i++){
            rectangles.add( new Rectangle());
        }
        int k = 0;
        for ( Rectangle rectangle : rectangles ){
            mainWnd.add(rectangle );
            rectangle.setX(60 * k);
            k++;
        }
        mainWnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainWnd.setLocationRelativeTo(null);
        mainWnd.setResizable(false);
        mainWnd.addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt) {
                terminateGame = true;
                super.windowClosing(evt);
            }
        });
        mainWnd.addMouseListener( new MouseInputListener(){
            @Override
            public void mouseMoved(MouseEvent e) {}
            @Override
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                Dimension actualSize = mainWnd.getContentPane().getSize();
                for ( Rectangle rectangle : rectangles ){
                    if ( rectangle.getX()  < e.getX()
                    && (rectangle.getY() - actualSize.getHeight() + mainWnd.getHeight() -100 ) < e.getY()
                    && (rectangle.getX() + 50) > e.getX() 
                    && (rectangle.getY() - actualSize.getHeight() + mainWnd.getHeight() + 50) > e.getY() ){
                        rectangle.setIsRect(!rectangle.getIsRect());
                        Score++;
                        rectangle.setDirectionX(50);
                        rectangle.setDirectionY(20);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {}
        } );
        mainWnd.setVisible(true);
        for ( Rectangle rectangle : rectangles ){
            rectangle.setDirectionY(2);
        }
        while ( !terminateGame ){
            boolean gameFailed = false;
            for ( Rectangle rectangle : rectangles ){

                if ((41 + 50 + rectangle.getY()) > mainWnd.getHeight()){
                    rectangle.setDirectionY(0);
                    if ( !rectangle.getIsRect() ){
                        rectangle.setDirectionX(50);
                    }else{
                        for ( Rectangle r : rectangles ){
                            r.setDirectionY(0);
                        }
                        gameFailed = true;
                    }
                }
                if ((50 + rectangle.getX()) > mainWnd.getWidth()){
                    rectangle.setDirectionX(0);
                    rectangle.setX(random.nextInt(600));
                    checkFigCross( rectangle );
                    rectangle.setY(10);
                    rectangle.setDirectionY(Speed);
                    rectangle.setIsRect(true);
                    Speed = 1;
                }
                rectangle.move();
            }
            checkScore( gameFailed );
            Graphics g = mainWnd.getGraphics();
            g.clipRect(0, 0, 900, 768);
            scoreboard.setText("Score: " + String.valueOf(Score) );
            mainWnd.repaint();
            try { Thread.sleep( 15 ); } catch ( InterruptedException ie ){
                return;
            }
        }
        
     }

     public static void checkFigCross( Rectangle rectangle ){
        boolean cross = true;
        while( cross ){
            cross = false;
            for ( Rectangle r : rectangles ){
                if ( (r.getX() <= rectangle.getX() 
                && (r.getX() + 51) >= rectangle.getX() 
                || r.getX() <= (rectangle.getX() + 50) 
                && (r.getX() + 51) >= (rectangle.getX() + 50))
                && r != rectangle ){
                    rectangle.setX(rectangle.getX()+1);
                    cross = true;
                }    
            }   
        }
    }

    public static void checkScore( boolean gameFailed ){
        boolean stopGame = gameFailed;
        if ( Score > 5 ){
            Speed = 2;
        }
        if (Score > 10 ){
            Speed = 3;
        }
        if (Score > 15 ){
            Speed = 4;
        }
        if (Score > 49 ){
            for ( Rectangle r : rectangles ){
                r.setDirectionY(0);
            }
           stopGame = true;
        }
        if ( stopGame){
            int result = JOptionPane.showConfirmDialog(mainWnd, "You " + (gameFailed ? "lose" : "won") + "\n Your score : "+ Score +"\n Do you want to start a new game?", "You lose",JOptionPane.YES_NO_OPTION );
            if (result == JOptionPane.YES_OPTION){
                for ( Rectangle rectangle : rectangles ){
                    rectangle.setIsRect(true);
                    rectangle.setX(random.nextInt(600));
                    checkFigCross( rectangle );
                    rectangle.setY(10);
                    for ( Rectangle r : rectangles ){
                        r.setDirectionY(2);
                        r.setDirectionX(0);
                    }
                }
            }else{
                mainWnd.dispatchEvent(new WindowEvent(mainWnd, WindowEvent.WINDOW_CLOSING));
            }
            Score = 0;
            Speed = 1;
        }
    }

}
    class Rectangle extends JComponent {
    private static final long serialVersionUID = 1;
    private int xPos;
    private int yPos;
    private Boolean isRect = true;
    private int directionX = 0;
    private int directionY = 0;

    public Rectangle ( ) {
    }

    public void setDirectionX( int i ){
        this.directionX = i;
    }

    public int getDirectionX(  ){
        return this.directionX;
    }

    public void setDirectionY( int i ){
        this.directionY = i;
    }

    public int getDirectionY(  ){
        return this.directionY;
    }

    public void setIsRect( Boolean b ){
        this.isRect = b;
    }

    public Boolean getIsRect(  ){
        return this.isRect;
    }

    public void setX( int x ){
        this.xPos = x;
    }

    public int getX(  ){
        return this.xPos;
    }

    public void setY( int y ){
        this.yPos = y;
    }

    public int getY(  ){
        return this.yPos;
    }

    public int getHeight(  ){
        return 51;
    }
    public int getWidth(  ){
        return 51;
    }
    public void move(){
        yPos += this.directionY;
        xPos += this.directionX;
    }

    public void paintRect(Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(getX(), getY(), 50 , 50);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
         if ( isRect ){
            g.setColor(Color.RED);
            g.fillRect(0, 0, 50 , 50);
            g.drawRect(0, 0, 50, 50);            
        }else{
            g.setColor(Color.GREEN);
            g.fillOval(0, 0, 50 , 50);
            g.drawOval(0, 0, 50, 50);
        }
      }
}


