public class Dates{

    public static void main(String[] args) {

        // int period = pack (2000 , 2, 3, 2127, 11, 29);
        int period = pack (
            Integer.parseInt( args[0] ) , 
            Integer.parseInt( args[1] ), 
            Integer.parseInt( args[2] ), 
            Integer.parseInt( args[3] ), 
            Integer.parseInt( args[4] ), 
            Integer.parseInt( args[5] )
        );
        // System.out.println( Integer.toBinaryString(period >> 15) );
        showPeriod(period);
    }



    private static int pack(int fromy, int fromm,int fromd,
     int toy,int tom, int tod){
        int t = ( fromy - 2000 ) & 127 |
                ( fromm & 15 ) << 7 | 
                ( fromd & 31 ) << 11;
        
        t = t << 16;

        t |= ( toy - 2000) & 127 | 
             ( tom & 15 ) << 7 | 
             ( tod & 31 ) << 11;
    
        return t;       
    }


    private static void showPeriod(int period){
        System.out.println(
            ( 2000 + ( (period >> 16 ) & 127 ) ) + "/" +
            ( (period >> 23) & 15 )  + "/" +
            ( (period >> 27) & 31 )  + "-" +
            ( 2000 + ( period  & 127 ) ) + "/" +
            ( (period >> 7) & 15 )  + "/" +
            ( (period >> 11) & 31 )
        );


    }

}