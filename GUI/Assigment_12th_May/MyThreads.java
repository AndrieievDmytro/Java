//////////////////Andrieiev Dmytro s21353 group 18c//////////////////////////
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class MyThreads  {

    public static void main(String[] args) {

        Letters letters = new Letters ("ABCD");
        for (Thread t : letters)
            System.out.println(t.getName() + " starting");
        letters.start();
        try { Thread.sleep(5000); }
        catch(InterruptedException ignore) { }
        letters.stop();
        System.out.println("\nProgram completed.");
    }

}

class Letters  implements Iterable<Thread> {

    private List <Thread> la;

    Letters(String letters){
        la = new ArrayList<Thread>();
        for (int i = 0 ; i < letters.length(); i++){
            String letter = Character.toString(letters.charAt(i));
            la.add( new Thread( () -> { 
                while( true ){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }
                        System.out.print( Thread.currentThread().getName() );
                    }
                }, letter ) 
            );
        }
    }

    public Iterator <Thread> iterator(){
        return la.iterator();
    }
 
    public synchronized void start (){
        for ( Thread t : la ){
            t.start();
        }
    }

    public synchronized void stop(){
        for ( Thread t : la ){
            t.interrupt();
        }
    }
}
