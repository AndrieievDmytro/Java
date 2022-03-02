// //Project 4
// // import javafx.animation.RotateTransition;
// // import javafx.animation.Animation;
// // import javafx.animation.KeyFrame;
// // import javafx.animation.KeyValue;
// // import javafx.animation.Timeline;
// // import javafx.application.Platform;
// // import javafx.beans.binding.DoubleBinding;
// // import javafx.beans.property.DoubleProperty;
// // import javafx.beans.property.SimpleDoubleProperty;
// // import javafx.scene.control.Button;
// // import javafx.scene.layout.VBox;
// // import javafx.scene.shape.Line;
// // import javafx.util.Duration;

// import javafx.animation.AnimationTimer;
// import javafx.application.Application;
// import javafx.scene.Group;
// import javafx.scene.Scene;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.stage.Stage;
// import javafx.scene.control.Slider;

// public class JavaFx extends Application {
//     public static double HEIGHT = 728;
//     public static double WIDTH = 1024;
//     public static double basicCenterY = HEIGHT/2;
//     public static double basicCenterX = WIDTH/2;  
//     final static double radiusOC = 200;
//     final static double radiusIC = 100;
//     final static double distantC = 50;
//     final static double closerC = 25;
    
//     public static void main(String[] args) {
//         launch(args);
//     }

//     @Override
//     public void start(Stage stage){
    
//     Timer timer = new Timer();
//     timer.init(stage);
//     timer.start();
               
//     }

//      class Timer extends AnimationTimer{
//         Slider sliderVar = getSlider();
//         long prevTime = System.nanoTime();
//         double prevAngle = 0;
//         double prevAngleBack = 0;
//         Circle distantFromCore;
//         Circle closeToCore;
//         Circle inwardCircle;
//         Circle outerCircle;
//         @Override
//         public void handle(long now) {
//             doHandle((now - prevTime)/1000000000.0);
//             // System.out.println((now - prevTime)/1000000000.0);
//             prevTime = now;
//         }

//         private void doHandle(double diff){
//             double currAngle = prevAngle + 2 * Math.PI * diff * (1-sliderVar.valueProperty().get());
//             double currAngleBack = prevAngleBack + 2.0 * Math.PI * diff * (sliderVar.valueProperty().get());
//             prevAngle = currAngle;
//             prevAngleBack = currAngleBack;
//             currAngleBack = -currAngleBack;
//             distantFromCore.setCenterX(Math.cos( currAngle )*radiusOC+basicCenterX);
//             distantFromCore.setCenterY(Math.sin( currAngle )*radiusOC+basicCenterY);
//             closeToCore.setCenterY((Math.sin( currAngleBack )*radiusIC+basicCenterY));
//             closeToCore.setCenterX((Math.cos( currAngleBack )*radiusIC+basicCenterX) );
//             // System.out.println((double)sliderVar.valueProperty().get());
//             // System.out.println( currAngle + "  " + currAngleBack );
            
//         } 


//         public void init(Stage stage) {
//             // outerCircle = new Circle();
//             // outerCircle.setCenterX(basicCenterX);
//             // outerCircle.setCenterY(basicCenterY);
//             // outerCircle.setRadius(radiusOC);
//             // outerCircle.setFill(Color.YELLOW);
//             // outerCircle.setStroke(Color.GREEN);
//             // outerCircle.setStrokeWidth(5);
//             outerCircle = getCircle( basicCenterX, basicCenterY, radiusOC, Color.YELLOW, Color.GREEN );

//             // inwardCircle = new Circle();
//             // inwardCircle.setCenterX(basicCenterX);
//             // inwardCircle.setCenterY(basicCenterY);
//             // inwardCircle.setRadius(radiusIC);
//             // inwardCircle.setFill(Color.WHITESMOKE);
//             // inwardCircle.setStroke(Color.RED);
//             // inwardCircle.setStrokeWidth(5);
//             inwardCircle = getCircle( basicCenterX, basicCenterY, radiusIC, Color.WHITESMOKE, Color.RED );
        

//             // distantFromCore = new Circle();
//             // distantFromCore.setCenterX(330);
//             // distantFromCore.setCenterY(220);
//             // distantFromCore.setRadius(distantC);
//             // distantFromCore.setFill(Color.RED);
//             // distantFromCore.setStroke(Color.GREEN);
//             // distantFromCore.setStrokeWidth(5);
//             distantFromCore = getCircle(radiusOC+basicCenterX, 0, distantC, Color.RED, Color.GREEN );
            
//             // closeToCore = new Circle();
//             // closeToCore.setCenterX(400);
//             // closeToCore.setCenterY(300);
//             // closeToCore.setRadius(closerC);
//             // closeToCore.setFill(Color.GREEN);
//             // closeToCore.setStroke(Color.RED);
//             // closeToCore.setStrokeWidth(5);
//             // closeToCore = getCircle( 0, radiusIC+basicCenterY, closerC, Color.GREEN, Color.RED );
//             closeToCore = getCircle( radiusIC+basicCenterX, 0, closerC, Color.GREEN, Color.RED );


//             Group group = new Group(outerCircle, inwardCircle,distantFromCore, closeToCore,sliderVar);
        
//             stage.setTitle("Spinning circles");
//             stage.setScene( new Scene(group));
//             stage.setFullScreen(false);
//             stage.setMaxWidth( WIDTH );
//             stage.setMaxHeight( HEIGHT );
//             stage.setMinWidth( WIDTH );
//             stage.setMinHeight( HEIGHT );
//             stage.show();

//         }
   
//         private Circle getCircle( double cX, double cY, double r, Color f, Color s ){
//             Circle circle = new Circle();
//             circle.setCenterX(cX);
//             circle.setCenterY(cY);
//             circle.setRadius(r);
//             circle.setFill(f);
//             circle.setStroke(s);
//             circle.setStrokeWidth(5);
//             return circle; 
//         }
   
//         private Slider getSlider(){
//             Slider slider = new Slider(0.0, 1.0, 0.0);
//             slider.setShowTickMarks(true);
//             slider.setShowTickLabels(true);
//             slider.setMajorTickUnit(0.25);
//             slider.setMinorTickCount(4);
//             slider.setBlockIncrement(0.25);
//             slider.setMinWidth(1000);
//             return slider;
//         }    
//     }

// }


 
