///////////////////Andrieiev Dmytro s21353////////////////////
import java.util.Iterator;
import java.util.Random;

public class RollDice {

    public static void main(String[] args) {
        for (int j = 0; j < 10; ++j) {
            for (Integer i : new Roll())
                System.out.print(i + " ");
            System.out.println();
        }    
    }
}

class Roll implements Iterable<Integer>, Iterator<Integer>{
    private int curr;
    private int prevElementValue;
    private int elementNumber;
    private int sumPrevTwo;
    private Random r = new Random();

    Roll(){
        this.prevElementValue = 0;
        this.sumPrevTwo = 0;
        this.elementNumber = 1;
    }
    
    public Iterator <Integer> iterator(){
       return this;
    }

    @Override
        public boolean hasNext() {
            if ( ( this.elementNumber > 2 ) && ( this.sumPrevTwo + this.curr == 11 ) ){
                return false;
            }
            return true;
        }

    @Override
        public Integer next() {
            this.sumPrevTwo = this.prevElementValue + this.curr;
            this.prevElementValue = this.curr;
            this.elementNumber++;
            return curr = this.r.nextInt(6)+1;
        }
}
    


