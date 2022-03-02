 
 class Car{
    private  String make;
    private  int price;
    private  Car next;

    public Car(String m, int p, Car n){
        this.make = m;
        this.price = p;
        this.next = n;
    }

    public Car(String make, int price){
        this.make = make;
        this.price = price;
        this.next = null;
    }

    public String getMake(){
        return make;
    }

    public int getPrice(){
        return this.price;
    }


    public Car getNext(){
        return this.next;
    }

    public void showCars(){
        System.out.print( this );
        if ( this.next != null ){
            this.next.showCars();
        }
    }

    public void showCarRev(){
        if ( this.next != null ){
            this.next.showCarRev();
        }
        System.out.print( this );
    }

    @Override
    public String toString() {
        return this.make + " (" + this.price + ") ";
    }

}

