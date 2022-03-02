// import javax.swing.JComponent;
// import java.awt.Color;
// import java.awt.Graphics;

// public class Rectangle extends JComponent {
//     private static final long serialVersionUID = 1;
//     private int xPos;
//     private int yPos;
//     private Boolean isRect = true;
//     private int directionX = 0;
//     private int directionY = 0;

//     public Rectangle ( ) {
//     }

//     public void setDirectionX( int i ){
//         this.directionX = i;
//     }

//     public int getDirectionX(  ){
//         return this.directionX;
//     }

//     public void setDirectionY( int i ){
//         this.directionY = i;
//     }

//     public int getDirectionY(  ){
//         return this.directionY;
//     }

//     public void setIsRect( Boolean b ){
//         this.isRect = b;
//     }

//     public Boolean getIsRect(  ){
//         return this.isRect;
//     }

//     public void setX( int x ){
//         this.xPos = x;
//     }

//     public int getX(  ){
//         return this.xPos;
//     }

//     public void setY( int y ){
//         this.yPos = y;
//     }

//     public int getY(  ){
//         return this.yPos;
//     }

//     public int getHeight(  ){
//         return 51;
//     }
//     public int getWidth(  ){
//         return 51;
//     }
//     public void move(){
//         yPos += this.directionY;
//         xPos += this.directionX;
//     }

//     public void paintRect(Graphics g){
//         g.setColor(Color.CYAN);
//         g.fillRect(getX(), getY(), 50 , 50);
//     }

//     @Override
//     public void paintComponent(Graphics g){
//         super.paintComponent(g);
//          if ( isRect ){
//             g.setColor(Color.RED);
//             g.fillRect(0, 0, 50 , 50);
//             g.drawRect(0, 0, 50, 50);            
//         }else{
//             g.setColor(Color.GREEN);
//             g.fillOval(0, 0, 50 , 50);
//             g.drawOval(0, 0, 50, 50);
//         }
//       }
// }

