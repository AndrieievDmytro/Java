////////////Andrieiev Dmytro s21353//////////////////

public class AndrieievDmytroS21353{
    public static void main(String[] args) {
        SimpleArrayList a =
        new SimpleArrayList()
            .append(new int[]{ 1, 3 }).insert( 1, 2 )
            .append(6).insert( 3, new int[]{ 4, 5 } );
        SimpleArrayList b = new SimpleArrayList(a);
        for ( int i = 0; i < a.size(); ++i ){
            a.set( i, a.get( i ) + 6 );
        }
        SimpleArrayList c = new SimpleArrayList( b );
        b.append(a).append(13).trim();
        System.out.println( "a -> " + a );
        System.out.println( "b -> " + b );
        System.out.println( "c -> " + c );
        c.clear();
        System.out.println( "c -> " + c );
    }

}


class SimpleArrayList{
    private final static int INITIAL_CAPACITY = 5;
    private int cap = INITIAL_CAPACITY;
    private int size = 0;
    private int [] arr = new int [cap];


    SimpleArrayList(){
        // Already initialized
    }

    SimpleArrayList(int a){
        this.size = 1;
        this.arr[0] = a;
    }

    SimpleArrayList(int [] a){
        this.cap = 2 * a.length;
        this.arr = new int [this.cap];
        System.arraycopy( a, 0, this.arr, 0, a.length );
        this.size = a.length;
    }

    SimpleArrayList(SimpleArrayList a){
        this.cap = 2 * a.size;
        this.arr = new int [this.cap];
        System.arraycopy( a.arr, 0, this.arr, 0, a.size );
        this.size = a.size;
    }

    public int size(){
        return this.size;
    }

    public void clear(){
        this.cap = INITIAL_CAPACITY;
        this.size = 0;
        this.arr = new int [cap];
    }

    public void trim(){
        this.cap = this.size;
        int [] anewarr = new int [ this.cap ];
        System.arraycopy( this.arr, 0, anewarr, 0, this.size);
        this.arr = anewarr;
    }

    public SimpleArrayList insert (int ind, int [] other){
        if ( ind > this.size && ind < 0 ){
            throw new IndexOutOfBoundsException();
        }
        if ( this.cap < ( other.length + this.size ) ){
            this.cap = 2 * ( other.length + this.size );
            int [] anewarr = new int [ this.cap ];
            System.arraycopy( this.arr, 0, anewarr, 0, ind );
            System.arraycopy( other, 0, anewarr, ind, other.length );
            System.arraycopy( this.arr, ind, anewarr, ind + other.length, this.size - ind );
            this.arr = anewarr;
        }else{
            System.arraycopy( this.arr, ind, this.arr, ind + other.length, this.size - ind );
            System.arraycopy( other, 0, this.arr, ind, other.length );
        }
        this.size += other.length;
        return this;
    }

    public SimpleArrayList insert(int ind, int e){
        return this.insert( ind, new int [] { e } );
    }

    public SimpleArrayList append( int e ){
        return this.insert( this.size, new int [] { e } );
    }

    public SimpleArrayList append( int [] a ){
        return this.insert( this.size, a );
    }

    public SimpleArrayList append(SimpleArrayList a){
        int [] tmp = new int [a.size];
        System.arraycopy(a.arr, 0, tmp, 0, a.size);
        return this.insert( this.size, tmp );
    }

    public int get(int ind){
        if ( ind > this.size){
            throw new IndexOutOfBoundsException();
        }
        return this.arr[ind];
    }

    public SimpleArrayList set(int ind, int val){
        if ( ind > this.size){
            throw new IndexOutOfBoundsException();
        }
        this.arr[ind] = val;
        return this;
    }

    public String toString(){
        String res = "";
        String delim = "";
        for ( int i = 0; i < this.size; ++i ){
            res += delim + this.arr[i];
            delim = ", ";
        }
        return "Cap="+ this.cap + ", size=" + this.size + ": [ " + res + " ]";
    }

}