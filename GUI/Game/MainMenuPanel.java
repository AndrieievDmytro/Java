import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



// import javax.swing.*;
// import javax.swing.BoxLayout;
// import javax.swing.ButtonModel;


public class MainMenuPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static enum StatesofGame { MENU, PLAY };
    public static StatesofGame state = StatesofGame.MENU;  //Game start from menu
    
    public static int WIDTH = 1024;
    public static int HIGHT = 768;
    

    public static int mouseX;
    public static int mouseY;
    
    Timer mainTimer = new Timer (30,this); //First parametr ms

    public MainMenuPanel() {
        super();
        setFocusable(true);
        requestFocus();
        mainTimer.start();        
    }

    
    public void actionPerformed( ActionEvent e) {

        if ( state.equals(StatesofGame.MENU)  ) {

        }


        if ( state.equals(StatesofGame.PLAY) ){

            MainWindow.game();

        } 


    }


    public void gameRender() {

    }   
    
    public void gameDraw() {

    }

}