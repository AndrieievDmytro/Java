
import java.util.Comparator;

class MyComp implements Comparator<Integer> {
    public static final int
                BY_VAL=0, BY_VAL_REV=1,
                BY_NUM_OF_DIVS=2, BY_SUM_OF_DIGS=3;
    
    int way;
    MyComp(int w){
        way = w;
    }

    @Override
    public int compare(Integer a, Integer b){
        switch(way){
            case BY_VAL:
            return a-b;
            
            case BY_VAL_REV:
            return b -a;
            case BY_NUM_OF_DIVS:

            case BY_SUM_OF_DIGS:

            break;
            default: throw new IllegalArgumentException();
        }
        return a-b;
    }
}



