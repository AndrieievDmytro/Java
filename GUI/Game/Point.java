// import java.util.Random;


public class Point  {
    
    // private double min;
    // private double max;
    // private double diff = max- min;
    // private Random r = new Random();
    // private MainWindow WIDTH = new MainWindow(); 
    // private int height;
    private int x;
    private int y = 0;


    public Point(){
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = 0;
    }

    public int getX() {
        return this.x ;
    }

    public int getY() {
        return this.y;
    }

}